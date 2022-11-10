/**
 * Controller for menstrual products management staffs.
 *
 * @author ruiyan ma
 */

package edu.uci.controller;

import edu.uci.entities.VO.BuildingVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@Api(tags = "staff users")
@RestController
public class StaffController {

    @ApiOperation("Report refilling product.")
    @PostMapping("/report-refill")
    public void reportRefill(@RequestParam("restroom_id") String restroomId) {
        // TODO: coding here
    }

    @ApiOperation("Return all buildings information.")
    @GetMapping("/qrcode")
    public List<BuildingVO> getAllBuildings() {
        // TODO: thinking about moving qr code generator to the frontend side
        return null;
    }
}
