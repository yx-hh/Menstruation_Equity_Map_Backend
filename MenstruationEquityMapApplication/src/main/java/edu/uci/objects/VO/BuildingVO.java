/**
 * Building information class.
 *
 * @author ruiyan ma
 */

package edu.uci.objects.VO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

@ApiModel("Building information")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class BuildingVO {

    @ApiModelProperty(value = "building_id", required = true)
    private Integer id;

    @ApiModelProperty(value = "building_name", required = true)
    private String name;

    @ApiModelProperty(value = "latitude", required = true)
    private Double latitude;

    @ApiModelProperty(value = "longitude", required = true)
    private Double longitude;

    @ApiModelProperty(value = "distance")
    private String distance;

    @ApiModelProperty(value = "walking_time")
    private Double walkingTime;

    @ApiModelProperty(value = "floor_list")
    private List<FloorVO> floors;
}
