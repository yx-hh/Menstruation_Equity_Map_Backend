package edu.uci.service;

import edu.uci.entities.VO.RestroomVO;

import java.util.List;
import java.util.Map;

/**
 * Restroom Service class, define service interface.
 *
 * @author ruiyan ma
 */
public interface RestroomService {
    Map<String, List<RestroomVO>> findAllRestrooms(int buildingId);

    Map<String, List<RestroomVO>> findAvailableRestrooms(int buildingId);

    void setProductStatus(int restroomId, boolean status);
}
