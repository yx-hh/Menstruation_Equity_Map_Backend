package edu.uci.service.implementation;

import edu.uci.common.GoogleMapHelper;
import edu.uci.objects.BuildingAddress;
import edu.uci.objects.DO.Building;
import edu.uci.objects.VO.BuildingVO;
import edu.uci.objects.VO.FloorVO;
import edu.uci.objects.VO.RestroomVO;
import edu.uci.repository.BuildingRepository;
import edu.uci.repository.RestroomRepository;
import edu.uci.service.BuildingService;
import edu.uci.service.RestroomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author ruiyan ma
 */
@Service
public class BuildingServiceImplement implements BuildingService {

    @Autowired
    BuildingRepository buildingRepository;

    @Autowired
    RestroomRepository restroomRepository;

    @Autowired
    GoogleMapHelper googleMapHelper;

    @Autowired
    RestroomService restroomService;

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
            double distance = BigDecimal.valueOf(Double.parseDouble(String.valueOf(buildingInfo.get("distance")))).
                    setScale(2, RoundingMode.HALF_UP).doubleValue() * 1000;
            double walkingTime = BigDecimal.valueOf(distance / 100.00).
                    setScale(0, RoundingMode.CEILING).doubleValue();
            buildingVO.setDistance(distance).
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
}
