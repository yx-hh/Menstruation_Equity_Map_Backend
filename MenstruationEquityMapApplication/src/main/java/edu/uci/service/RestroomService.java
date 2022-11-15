package edu.uci.service;

import edu.uci.objects.VO.RestroomVO;

import java.util.List;
import java.util.Map;

/**
 * Restroom Service class, define service interface.
 *
 * @author ruiyan ma
 */
public interface RestroomService {
    Map<String, List<RestroomVO>> findAllRestrooms(int buildingId);

    boolean setProductStatus(int restroomId, boolean status);
}
