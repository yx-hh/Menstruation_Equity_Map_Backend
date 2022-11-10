package edu.uci.service.impl;

import edu.uci.common.GoogleMapHelper;
import edu.uci.entities.BuildingAddress;
import edu.uci.entities.DO.Building;
import edu.uci.entities.VO.BuildingVO;
import edu.uci.entities.VO.FloorVO;
import edu.uci.repository.BuildingRepository;
import edu.uci.repository.RestroomRepository;
import edu.uci.service.BuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
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
    public List<BuildingVO> findNearestBuilding(double userLongitude, double userLatitude, double radius) throws IOException {
        List<Map<String, Object>> buildingInfoList = buildingRepository.findBuildingByRadius(userLongitude, userLatitude, radius);
        List<BuildingVO> buildings = new ArrayList<>();

        for (Map<String, Object> buildingInfo : buildingInfoList) {
            int buildingId = Integer.parseInt(String.valueOf(buildingInfo.get("id")));
            BuildingVO vo = findById(buildingId);

            double distance = BigDecimal.valueOf(Double.parseDouble(String.valueOf(buildingInfo.get("distance")))).
                    setScale(2, RoundingMode.HALF_UP).doubleValue() * 1000;
            double walkingTime = BigDecimal.valueOf(distance / 100.00).
                    setScale(0, RoundingMode.CEILING).doubleValue();
            vo.setDistance(distance).setWalkingTime(walkingTime);

            buildings.add(vo);
        }
        return buildings;
    }

    @Override
    public BuildingVO findById(Integer id) throws IOException {
        // find building info
        Building building = buildingRepository.findById(id).get();

        // if building's latitude or longitude is zero then call api to get the data
        if (building.getLatitude() < 0.000001 && building.getLongitude() < 0.000001) {
            BuildingAddress lagAndLat = googleMapHelper.getLagAndLat(building.getBuildingName());
            // update database
            buildingRepository.save(building.setLatitude(lagAndLat.getLatitude()).setLongitude(lagAndLat.getLongitude()));
        }

        // get building floor
        List<Map<String, Object>> FloorInfoList = restroomRepository.findFloorByBuildingId(building.getId());
        List<FloorVO> floors = new ArrayList<>();

        for (Map<String, Object> FloorInfo : FloorInfoList) {
            FloorVO floor = new FloorVO();
            floor.setName(String.valueOf(FloorInfo.get("floor_name")));
            floor.setValidRoomNum(Integer.parseInt(String.valueOf(FloorInfo.get("validRoomNum"))));
            floors.add(floor);
        }

        // add restroom number for each floor
        for (FloorVO floor : floors) {
            List<Map<String, Object>> restroomInfoList = restroomRepository.findRestroomByBuildingIdAndFloorName(building.getId(), floor.getName());
            floor.setRestroomList(restroomInfoList.stream().map(
                    info -> Integer.parseInt(String.valueOf(info.get("id")))).collect(Collectors.toList()));
        }

        return new BuildingVO().setId(building.getId())
                .setName(building.getBuildingName())
                .setLatitude(building.getLatitude())
                .setLongitude(building.getLongitude())
                .setFloorList(floors);
    }
}
