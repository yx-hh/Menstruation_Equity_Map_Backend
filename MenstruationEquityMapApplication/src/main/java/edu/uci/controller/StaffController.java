/**
 * Controller for menstrual products management staffs.
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
import java.util.NoSuchElementException;


@Api(tags = "staff users")
@RestController
public class StaffController {
    @Autowired
    private BuildingService buildingService;

    @Autowired
    private RestroomService restroomService;

    @ApiOperation("Report refilling product.")
    @PostMapping("/report-refill")
    public boolean reportRefill(@RequestParam("restroom_id") String restroomId) {
        return restroomService.setProductStatus(Integer.parseInt(restroomId), true);
    }

    @ApiOperation("Return all buildings information.")
    @GetMapping("/qrcode")
    public List<BuildingVO> getAllBuildings() throws IOException {
        // TODO: thinking about moving qr code generator to the frontend side
        return buildingService.findAllBuildings();
    }
}
