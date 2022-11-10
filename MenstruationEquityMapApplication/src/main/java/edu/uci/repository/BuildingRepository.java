package edu.uci.repository;

import edu.uci.entities.DO.Building;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

/**
 * @author Huang Yuxin
 * @date 2022/11/1
 */
public interface BuildingRepository extends JpaRepository<Building, Integer> {

    @Query(value = "SELECT id, building_name," +
            "( 6371 * " +
            "    ACOS( " +
            "        COS( RADIANS( latitude ) ) * " +
            "        COS( RADIANS( ?1 ) ) * " +
            "        COS( RADIANS( ?2 ) - " +
            "        RADIANS( longitude ) ) + " +
            "        SIN( RADIANS( latitude ) ) * " +
            "        SIN( RADIANS( ?1) ) " +
            "    ) " +
            ") AS distance FROM building HAVING distance <= ?3 ORDER BY distance ASC", nativeQuery = true)
    List<Map<String, Object>> findBuildingByRadius(double userLatitude, double userLongitude, double radius);
}
