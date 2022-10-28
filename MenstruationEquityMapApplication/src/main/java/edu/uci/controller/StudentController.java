/**
 * Controller for students.
 *
 * @author ruiyan ma
 */

package edu.uci.controller;

import edu.uci.objects.Building;
import edu.uci.objects.Floor;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@Api(tags = "student users")
@RestController
public class StudentController {

    @ApiOperation("Search a product to find building information and returns top 5 buildings.")
    @GetMapping("/search")
    public List<Building> searchProduct(
            @RequestParam("longitude") double longitude,
            @RequestParam("latitude") double latitude) {
        // TODO: change code
        List<Building> result = new ArrayList<>(5);
        result.add(new Building("1", "Aldrich Hall", 100, 100, 100, 5));
        result.add(new Building("2", "Langson Library", 110, 120, 200, 10));
        return result;
    }

    @ApiOperation("Click a building to find floor and restroom information.")
    @GetMapping("/building")
    public Building buildingInfo(@RequestParam("building_id") String buildingId) {
        // TODO: change code
        List<Floor> floors = new ArrayList<>();
        floors.add(new Floor("1", "first floor", 2));
        return new Building("1", "Aldrich Hall", 100, 100, 100, 5, floors);
    }

    @ApiOperation("Report missing product.")
    @PostMapping("/report-miss")
    public void reportMissing(@RequestParam("restroom_id") String restroomId) {
        // TODO: coding here
    }
}
