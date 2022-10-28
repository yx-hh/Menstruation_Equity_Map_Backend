/**
 * Controller for menstrual products management staffs.
 *
 * @author ruiyan ma
 */

package edu.uci.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;


@Api(tags = "staff users")
@RestController
public class StaffController {

    @ApiOperation("Report refilling product.")
    @PostMapping("/report-refill")
    public void reportRefill(@RequestParam("restroom_id") String restroomId) {
        // TODO: coding here
    }

    @ApiOperation("Generate QR code.")
    @GetMapping("/qrcode")
    public String genQRCode(@RequestParam("restroom_id") String restroomId) {
        // TODO: change code
        return "image";
    }
}
