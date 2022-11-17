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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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
    @PostMapping("/report-refill")
    public boolean reportRefill(@RequestParam("restroom_id") String restroomId) {
        return restroomService.setProductStatus(Integer.parseInt(restroomId), true);
    }

    @ApiOperation("Return all buildings information.")
    @GetMapping("/all-buildings")
    public List<BuildingVO> getAllBuildings() throws IOException {
        return buildingService.findAllBuildings();
    }

    @ApiOperation("Get qrcode.")
    @GetMapping("/qrcode")
    public void getQrCode(HttpServletResponse response,
                          @RequestParam("restroom_id") String restroomId) {
        String content = qrcodeUrl + restroomId;
        try {
            QRCodeUtil.writeQRCode(content, response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
