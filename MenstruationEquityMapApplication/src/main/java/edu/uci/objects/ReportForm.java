/**
 * Report form class.
 *
 * @author ruiyan ma
 */

package edu.uci.objects;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "Report form")
public class ReportForm {

    public String getBuildingId() {
        return buildingId;
    }

    public String getFloorId() {
        return floorId;
    }

    public String getRestroomId() {
        return restroomId;
    }

    public String getProductId() {
        return productId;
    }

    @Override
    public String toString() {
        return "ReportForm{" +
                "buildingId='" + buildingId + '\'' +
                ", floorId='" + floorId + '\'' +
                ", restroomId='" + restroomId + '\'' +
                ", productId='" + productId + '\'' +
                '}';
    }

    @ApiModelProperty(value = "building_id", required = true)
    private String buildingId;

    @ApiModelProperty(value = "floor_id", required = true)
    private String floorId;

    @ApiModelProperty(value = "restroom_id", required = true)
    private String restroomId;

    @ApiModelProperty(value = "product_id", required = true)
    private String productId;
}
