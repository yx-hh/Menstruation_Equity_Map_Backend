package edu.uci.repository;

import edu.uci.objects.DO.Building;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

/**
 * @author Huang Yuxin
 * @date 2022/11/1
 */
public interface BuildingRepository extends JpaRepository<Building, Integer> {

    /**
     * Calculate distance by latitude and longitude
     * @param userLatitude user latitude
     * @param userLongitude user longitude
     * @param radius the distance range from user location to building
     * @return building id, building name, latitude and longitude
     */
    @Query(value = "SELECT id, building_name, latitude, longitude, " +
            "( 3959 * " +
            "    ACOS( " +
            "        COS( RADIANS( latitude ) ) * " +
            "        COS( RADIANS( ?1 ) ) * " +
            "        COS( RADIANS( ?2 ) - " +
            "        RADIANS( longitude ) ) + " +
            "        SIN( RADIANS( latitude ) ) * " +
            "        SIN( RADIANS( ?1) ) " +
            "    ) " +
            ") AS distance FROM building where deleted = false HAVING distance <= ?3 ORDER BY distance ASC", nativeQuery = true)
    List<Map<String, Object>> findNearestBuildings(double userLatitude, double userLongitude, double radius);
}
