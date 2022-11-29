/**
 * Controller for menstrual products management staffs.
 *
 * @author ruiyan ma
 */

package edu.uci.controller;

import edu.uci.common.QRCodeUtil;
import edu.uci.objects.VO.BuildingVO;
import edu.uci.service.BuildingService;
import edu.uci.service.RestroomService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;


@Api(tags = "staff users")
@RestController
public class StaffController {
    @Autowired
    private BuildingService buildingService;

    @Autowired
    private RestroomService restroomService;

    @Value("${qrcode.url}")
    private String qrcodeUrl;

    @ApiOperation("Report refilling product.")
    @GetMapping("/report-refill")
    public String reportRefill(@RequestParam("restroom_id") Integer restroomId) {
        boolean flag = false;
        try {
            flag = restroomService.setProductStatus(restroomId, true);
            return flag ? "Update Success!" : "Update Fail! Please try later!";
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return "Update Fail! Please try later!";
        }
    }

    @ApiOperation("Return all buildings information.")
    @GetMapping("/all-buildings")
    public List<BuildingVO> getAllBuildings() throws IOException {
        return buildingService.findAllBuildings();
    }

    @ApiOperation("Get qrcode.")
    @GetMapping("/qrcode")
    public void getQrCode(HttpServletResponse response,
                          @RequestParam("building_name") String buildingName,
                          @RequestParam("floor_name") String floorName,
                          @RequestParam("restroom_num") String restroomNum,
                          @RequestParam("restroom_id") String restroomId) {
        StringBuilder content = new StringBuilder(qrcodeUrl);
        content.append("building_name=").append(buildingName.trim().replace(" ", "_"));
        content.append("&floor_name=").append(floorName.trim().replace(" ", "_"));
        content.append("&restroom_num=").append(restroomNum.trim().replace(" ", "_"));
        content.append("&restroom_id=").append(restroomId);
        try {
            QRCodeUtil.writeQRCode(content.toString(), response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
