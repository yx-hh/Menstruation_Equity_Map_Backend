package edu.uci.repository;

import edu.uci.entities.DO.Restroom;
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
    @Query(value = "SELECT floor_name, count(*) AS validRoomNum " +
            "FROM restroom " +
            "WHERE building_id = ?1 AND deleted = FALSE AND product_status = TRUE " +
            "GROUP BY floor_name", nativeQuery = true)
    List<Map<String, Object>> findFloorByBuildingId(int buildingId);

    @Transactional(timeout = 10)
    @Query(value = "SELECT id FROM restroom " +
            "WHERE building_id = ?1 AND floor_name = ?2 AND deleted = FALSE AND product_status = TRUE " +
            "ORDER BY id", nativeQuery = true)
    List<Map<String, Object>> findRestroomByBuildingIdAndFloorName(int buildingId, String floorName);
}
