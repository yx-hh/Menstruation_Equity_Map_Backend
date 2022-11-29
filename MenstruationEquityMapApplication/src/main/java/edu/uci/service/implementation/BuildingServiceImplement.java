package edu.uci.service.implementation;

import edu.uci.common.GmailHelper;
import edu.uci.common.GoogleMapHelper;
import edu.uci.objects.BuildingAddress;
import edu.uci.objects.DO.Building;
import edu.uci.objects.DO.Restroom;
import edu.uci.objects.VO.BuildingVO;
import edu.uci.objects.VO.FloorVO;
import edu.uci.objects.VO.RestroomVO;
import edu.uci.repository.BuildingRepository;
import edu.uci.repository.RestroomRepository;
import edu.uci.service.BuildingService;
import edu.uci.service.RestroomService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author ruiyan ma
 */
@Service
@Slf4j
public class BuildingServiceImplement implements BuildingService {

    @Autowired
    BuildingRepository buildingRepository;

    @Autowired
    RestroomRepository restroomRepository;

    @Autowired
    GoogleMapHelper googleMapHelper;

    @Autowired
    RestroomService restroomService;

    @Autowired
    GmailHelper gmailHelper;

    @Value("${refill.url}")
    String refillUrl;

    @Override
    public List<BuildingVO> findAllBuildings() {
        List<Building> buildingList = buildingRepository.findAll();
        List<BuildingVO> buildingVOList = new ArrayList<>();

        for (Building building : buildingList) {
            Map<String, List<RestroomVO>> restroomGroup = restroomService.findAllRestrooms(building.getId());

            // build floorVO
            List<FloorVO> floorVOList = new ArrayList<>();
            for (String floorName : restroomGroup.keySet()) {
                FloorVO floorVO = new FloorVO();
                floorVO.setName(floorName);
                floorVO.setRestrooms(restroomGroup.get(floorName));
                floorVOList.add(floorVO);
            }

            buildingVOList.add(
                    new BuildingVO().setId(building.getId())
                            .setName(building.getBuildingName())
                            .setLatitude(building.getLatitude())
                            .setLongitude(building.getLongitude())
                            .setFloors(floorVOList));
        }
        return buildingVOList;
    }

    @Override
    public List<BuildingVO> findNearestBuilding(double userLongitude, double userLatitude, double radius) throws IOException {
        List<Map<String, Object>> buildingInfoList = buildingRepository.findNearestBuildings(userLongitude, userLatitude, radius);
        List<BuildingVO> buildingVOList = new ArrayList<>();

        for (Map<String, Object> buildingInfo : buildingInfoList) {
            int id = Integer.parseInt(String.valueOf(buildingInfo.get("id")));
            BuildingVO buildingVO = new BuildingVO().setId(id);
            // unit - mile
            double distance = BigDecimal.valueOf(Double.parseDouble(String.valueOf(buildingInfo.get("distance")))).
                    setScale(2, RoundingMode.HALF_UP).doubleValue();
            // unit - minutes, rule: 100 meters' walking time 1 minutes
            double walkingTime = BigDecimal.valueOf(distance * 16.0934).
                    setScale(0, RoundingMode.CEILING).doubleValue();
            String distancePresent;
            if (distance <= 0.1) {
                distancePresent = new BigDecimal(distance * 528).setScale(2, RoundingMode.HALF_UP).doubleValue() + "FT";
            } else {
                distancePresent = distance + "MILE";
            }
            buildingVO.setDistance(distancePresent).
                    setWalkingTime(walkingTime).
                    setName(String.valueOf(buildingInfo.get("building_name"))).
                    setLatitude(Double.parseDouble(String.valueOf(buildingInfo.get("latitude")))).
                    setLongitude(Double.parseDouble(String.valueOf(buildingInfo.get("longitude"))));
            buildingVO = getFloorNum(buildingVO);
            buildingVOList.add(buildingVO);
        }
        return buildingVOList;
    }

    private BuildingVO getFloorNum(BuildingVO buildingVO) throws IOException {
        // if building's latitude or longitude is zero then call api to get the data
        if (buildingVO.getLatitude() < 0.000001 && buildingVO.getLongitude() < 0.000001) {
            BuildingAddress lagAndLat = googleMapHelper.getLagAndLat(buildingVO.getName());
            // update building's latitude and longitude
            buildingRepository.save(new Building().setId(buildingVO.getId()).setLatitude(lagAndLat.getLatitude()).setLongitude(lagAndLat.getLongitude()));
            buildingVO.setLatitude(lagAndLat.getLatitude()).setLongitude(lagAndLat.getLongitude());
        }

        // build floorVO
        List<FloorVO> floorVOList = new ArrayList<>();
        List<Map<String, Object>> floorMaps = restroomRepository.findAvailableFloorByBuildingId(buildingVO.getId());
        for (Map<String, Object> floorInfo : floorMaps) {
            FloorVO floorVO = new FloorVO().setName(String.valueOf(floorInfo.get("name"))).
                    setValidRoomNum(Integer.parseInt(String.valueOf(floorInfo.get("validRoomNum"))));
            floorVOList.add(floorVO);
        }
        return buildingVO.setFloors(floorVOList);
    }

    @Override
    public String reportMiss(int restroomId) {
        // send email to notify facility
        try {
            Restroom restroom = restroomRepository.findById(restroomId).get();
            // check alreay been report or not
            if(restroom.getProductStatus() == false && !isLargerThanOneHour(restroom.getUpdateTime())){
                return "Need refill already been reported within one hour, please waiting for a while!";
            }
            Building building = buildingRepository.findById(restroom.getBuildingId()).get();
            String restroomLocation = building.getBuildingName() + "- Floor " + restroom.getFloorName() + " Restroom " + restroom.getRoomNum();
            String subject = "Need Refilling Notice: " + restroomLocation;
            refillUrl = refillUrl + restroomId;
            String mailMessage = String.format(
                    "Dear facility,\n" +
                            "\n" +
                            "Hope you have a great day. %s needs to replenish menstrual supplies. If you have completed replenishment, please click the following link to update the status: %s\n" +
                            "\n" +
                            "Thank you for your patience and time in advance!\n" +
                            "\n" +
                            "Best,\n" +
                            "Womxn's Center for Success", restroomLocation, refillUrl);
            gmailHelper.sendMail(subject, mailMessage);
            // update database
            restroomService.setProductStatus(restroomId, false);
        } catch (Exception e) {
            log.error("send email to facilities or update restroom status fail!");
            return "Sending email to facilities or update restroom status fail! Please try later!";
        }
        return "Report Success!";
    }

    private boolean isLargerThanOneHour(Date lastUpdateTime){
        long diff = TimeUnit.MILLISECONDS.toHours(Instant.now().toEpochMilli() - lastUpdateTime.getTime());
        return diff >= 1;
    }
}
