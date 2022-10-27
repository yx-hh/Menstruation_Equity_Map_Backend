/**
 * Controller for menstrual products management staffs.
 *
 * @author ruiyan ma
 */

package edu.uci.controller;

import edu.uci.objects.ReportForm;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;


@Api(tags = "staff users")
@RestController
public class StaffController {

    @ApiOperation("Report refilling product.")
    @PostMapping("/report-refill")
    public void reportRefill(@RequestBody ReportForm form) {
        // TODO: coding here
        System.out.println(form);
    }

    @ApiOperation("Generate QR code.")
    @GetMapping("/qrcode")
    public String genQRCode(
            @RequestParam("building_id") String buildingId,
            @RequestParam("floor_id") String floorId,
            @RequestParam("restroom_id") String restroomId) {
        // TODO: change code
        return "image";
    }
}
