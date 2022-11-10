package edu.uci.service;

import edu.uci.entities.VO.BuildingVO;

import java.io.IOException;
import java.util.List;

/**
 * @author Huang Yuxin
 * @date 2022/11/1
 */
public interface BuildingService {
    List<BuildingVO> findNearestBuilding(double latitude, double longitude, double radius) throws IOException;

    BuildingVO findById(Integer id) throws IOException, IllegalAccessException, InstantiationException;

//    List<BuildingVO> findAllBuildings();
}
