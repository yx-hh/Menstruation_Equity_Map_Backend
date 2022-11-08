package edu.uci.service.impl;

import edu.uci.common.GoogleMapHelper;
import edu.uci.objects.Baddress;
import edu.uci.objects.DO.Building;
import edu.uci.objects.VO.BuildingVO;
import edu.uci.objects.VO.FloorVO;
import edu.uci.repository.BuildingRepository;
import edu.uci.repository.RestroomRepository;
import edu.uci.service.BuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Huang Yuxin
 * @date 2022/11/1
 */
@Service
public class BuildingServiceImpl implements BuildingService {

    @Autowired
    BuildingRepository buildingRepository;

    @Autowired
    RestroomRepository restroomRepository;

    @Autowired
    GoogleMapHelper googleMapHelper;

    @Override
    public List<BuildingVO> findNearestBuilding(double latitude, double longitude, double radius) throws IOException {
        // calculate data in database and find the nearest building by parameter count
        List<Map<String, Object>> buildingMap = buildingRepository.findBuildingByRadius(latitude, longitude, radius);
        LinkedList<BuildingVO> res = new LinkedList<>();
        for(Map<String, Object> map : buildingMap){
            Integer buildingId = Integer.parseInt(String.valueOf(map.get("id")));
            BuildingVO vo = findById(buildingId);
            Double distance = new BigDecimal(Double.parseDouble(String.valueOf(map.get("distance")))).
                    setScale(2, BigDecimal.ROUND_HALF_UP).
                    doubleValue()*1000;
            Double walkingTime = new BigDecimal(distance/100.00).setScale(0, BigDecimal.ROUND_CEILING).doubleValue();
            vo.setLatitude(latitude).
                    setLongitude(longitude).
                    setDistance(distance).
                    // todo change to real walking time
                    setWalkingTime(walkingTime);

            res.add(vo);

        }
        return res;
    }

    @Override
    public BuildingVO findById(Integer id) throws IOException {
        // find building info
        Building building = buildingRepository.findById(id).get();

        // if building's latitude or longitude is zero then call api to get the data
        if (building.getLatitude()< 0.000001 && building.getLongitude() < 0.000001) {
            Baddress lagAndLat = googleMapHelper.getLagAndLat(building.getBuildingName());
            // update database
            buildingRepository.save(building.setLatitude(lagAndLat.getLatitude()).setLongitude(lagAndLat.getLongitude()));
        }

        // get building floor
        List<Map<String, Object>> maps = restroomRepository.findAvailableFloorByBuildingId(building.getId());
        List<FloorVO> floorVOs = maps.stream().map(m -> {
            FloorVO cd = new FloorVO();
            cd.setId(Integer.parseInt(String.valueOf(m.get("id"))));
            cd.setName(String.valueOf(m.get("name")));
            cd.setValidRoomNum(Integer.parseInt(String.valueOf(m.get("validRoomNum"))));
            return cd;
        }).collect(Collectors.toList());

        BuildingVO vo = new BuildingVO().setId(building.getId())
                .setName(building.getBuildingName())
                .setLatitude(building.getLatitude())
                .setLongitude(building.getLongitude())
                .setFloors(floorVOs);

        return vo;
    }
}
