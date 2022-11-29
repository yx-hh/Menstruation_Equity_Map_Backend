package edu.uci.service;

import edu.uci.objects.VO.BuildingVO;

import java.io.IOException;
import java.util.List;

/**
 * Building service interface.
 *
 * @author Huang Yuxin, ruiyan ma
 */
public interface BuildingService {

    List<BuildingVO> findAllBuildings() throws IOException;

    List<BuildingVO> findNearestBuilding(double latitude, double longitude, double radius) throws IOException;

    String reportMiss(int restroomId);
}
