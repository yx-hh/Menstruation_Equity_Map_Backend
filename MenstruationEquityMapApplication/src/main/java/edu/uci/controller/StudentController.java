/**
 * Controller for students.
 *
 * @author ruiyan ma
 */

package edu.uci.controller;

import edu.uci.objects.VO.BuildingVO;
import edu.uci.service.BuildingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;


@Api(tags = "student users")
@RestController
public class StudentController {
    @Autowired
    private BuildingService buildingService;

    @ApiOperation("Search a product to find building information and returns top 5 buildings.")
    @GetMapping("/search")
    public List<BuildingVO> searchProduct(
            @RequestParam(value = "longitude") double longitude,
            @RequestParam(value = "latitude") double latitude,
    @RequestParam(value = "radius", required = false, defaultValue = "0.5") double radius) throws IOException {
        // TODO: change code
        return buildingService.findNearestBuilding(latitude, longitude, radius);
//        List<BuildingVO> result = new ArrayList<>(5);
//        result.add(new BuildingVO(1, "Aldrich Hall", 100, 100, 100, 5));
//        result.add(new BuildingVO(2, "Langson Library", 110, 120, 200, 10));
//        return result;
//        return null;
    }

    @ApiOperation("Click a building to find floor and restroom information.")
    @GetMapping("/building")
    public BuildingVO buildingInfo(@RequestParam("building_id") Integer buildingId) throws IOException, IllegalAccessException, InstantiationException {
        return buildingService.findById(buildingId);
    }

    @ApiOperation("Report missing product.")
    @PostMapping("/report-miss")
    public void reportMissing(@RequestParam("restroom_id") String restroomId) {
        // TODO: coding here
    }
}
