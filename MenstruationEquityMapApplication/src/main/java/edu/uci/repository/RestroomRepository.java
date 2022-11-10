package edu.uci.repository;

import edu.uci.entities.DO.Restroom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;


/**
 * Restroom repository class, for query restroom table.
 *
 * @author ruiyan ma
 */
public interface RestroomRepository extends JpaRepository<Restroom, Integer> {

    @Transactional(timeout = 10)
    @Query(value = "SELECT id, floor_name, room_num FROM restroom " +
            "WHERE building_id = ?1 AND deleted = FALSE", nativeQuery = true)
    List<Map<String, Object>> findAllRestrooms(int buildingId);

    @Transactional(timeout = 10)
    @Query(value = "SELECT id, floor_name, room_num FROM restroom " +
            "WHERE building_id = ?1 AND deleted = FALSE AND product_status = TRUE", nativeQuery = true)
    List<Map<String, Object>> findAvailableRestrooms(int buildingId);
}
