package edu.uci.repository;

import edu.uci.objects.DO.Building;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author Huang Yuxin
 * @date 2022/11/1
 */
public interface BuildingRepository extends JpaRepository<Building, Integer> {

    @Query(value = "SELECT id, building_name, \n" +
            "( 6371 * \n" +
            "    ACOS( \n" +
            "        COS( RADIANS( latitude ) ) * \n" +
            "        COS( RADIANS( ?1 ) ) * \n" +
            "        COS( RADIANS( ?2 ) - \n" +
            "        RADIANS( longitude ) ) + \n" +
            "        SIN( RADIANS( latitude ) ) * \n" +
            "        SIN( RADIANS( ?1) ) \n" +
            "    ) \n" +
            ") AS distance FROM building HAVING distance <= ?3 ORDER BY distance ASC", nativeQuery = true)
    List<Map<String, Object>> findBuildingByRadius(double latitude, double longitude, double radius);
}
