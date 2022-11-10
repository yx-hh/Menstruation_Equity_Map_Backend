/**
 * Controller for students.
 *
 * @author ruiyan ma
 */

package edu.uci.controller;

import edu.uci.entities.VO.BuildingVO;
import edu.uci.service.BuildingService;
import edu.uci.service.RestroomService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;


@Api(tags = "student users")
@RestController
public class StudentController {
    @Autowired
    private BuildingService buildingService;

    @Autowired
    private RestroomService restroomService;

    @ApiOperation("Find the nearest 5 buildings and them.")
    @GetMapping("/search")
    public List<BuildingVO> searchProduct(
            @RequestParam(value = "user_longitude", defaultValue = "-117.84370671600364") double userLongitude,
            @RequestParam(value = "user_latitude", defaultValue = "33.64325158034576") double userLatitude,
            @RequestParam(value = "radius", defaultValue = "0.5") double radius) throws IOException {
        return buildingService.findNearestBuilding(userLatitude, userLongitude, radius);
    }

    @ApiOperation("Report missing product.")
    @PostMapping("/report-miss")
    public void reportMissing(@RequestParam("restroom_id") String restroomId) {
        restroomService.setProductStatus(Integer.parseInt(restroomId), false);
    }
}
