package edu.uci.repository;

import edu.uci.objects.DO.Restroom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;


/**
 * @author Huang Yuxin
 * @date 2022/11/1
 */
public interface RestroomRepository extends JpaRepository<Restroom, Integer> {

    @Transactional(timeout = 10)
    @Query(value = "select id, floor_name as name, count(*) as validRoomNum from restroom where building_id = ?1 and deleted = false and product_status = true GROUP BY floor_name", nativeQuery = true)
    List<Map<String, Object>> findAvailableFloorByBuildingId(Integer buildingId);
}
