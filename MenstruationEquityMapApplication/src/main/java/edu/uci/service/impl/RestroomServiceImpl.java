package edu.uci.service.impl;

import edu.uci.objects.DO.Restroom;
import edu.uci.objects.VO.FloorVO;
import edu.uci.repository.RestroomRepository;
import edu.uci.service.RestroomService;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Huang Yuxin
 * @date 2022/11/1
 */
@Service
public class RestroomServiceImpl implements RestroomService {
//    private RestroomRepository restroomRepository;
//
//    List<FloorVO> findAvailableFloorByBuildingId(Integer buildingId){
//        Restroom restroom = new Restroom().setBuildingId(buildingId);
//        restroom.setProductStatus(true).setDeleted(true);
//        Example<Restroom> example = Example.of(restroom);
//        restroomRepository.findAll(example);
//
//    }
}
