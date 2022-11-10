package edu.uci.service.implementation;

import edu.uci.entities.DO.Restroom;
import edu.uci.entities.VO.RestroomVO;
import edu.uci.repository.RestroomRepository;
import edu.uci.service.RestroomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Restroom service implementation.
 *
 * @author ruiyan ma
 */
@Service
public class RestroomServiceImplement implements RestroomService {
    @Autowired
    private RestroomRepository restroomRepository;

    @Override
    public Map<String, List<RestroomVO>> findAllRestrooms(int buildingId) {
        return groupRestroom(restroomRepository.findAllRestrooms(buildingId));
    }

    @Override
    public Map<String, List<RestroomVO>> findAvailableRestrooms(int buildingId) {
        return groupRestroom(restroomRepository.findAvailableRestrooms(buildingId));
    }

    @Override
    public void setProductStatus(int restroomId, boolean status) {
        try {
            Restroom restroom = restroomRepository.findById(restroomId).get();
            restroomRepository.save(restroom.setProductStatus(status));
        } catch (NoSuchElementException e) {
        }
    }

    private Map<String, List<RestroomVO>> groupRestroom(List<Map<String, Object>> restroomInfoList) {
        List<RestroomVO> restroomVOs = restroomInfoList.stream().map(info -> {
            RestroomVO restroomVO = new RestroomVO();
            restroomVO.setRestroomId(Integer.parseInt(String.valueOf(info.get("id"))));
            restroomVO.setFloorName(String.valueOf(info.get("floor_name")));
            restroomVO.setRoomNum(String.valueOf(info.get("room_num")));
            return restroomVO;
        }).collect(Collectors.toList());

        Map<String, List<RestroomVO>> restroomGroup = new TreeMap<>();
        for (RestroomVO restroomVO : restroomVOs) {
            String floorName = restroomVO.getFloorName();
            restroomGroup.putIfAbsent(floorName, new ArrayList<>());
            restroomGroup.get(floorName).add(restroomVO);
        }

        return restroomGroup;
    }
}
