/**
 * Floor information class.
 *
 * @author ruiyan ma
 */

package edu.uci.objects.VO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("Floor")
@Data
public class FloorVO {

    @ApiModelProperty(value = "floor_id", required = true)
    private Integer id;

    @ApiModelProperty(value = "floor_name", required = true)
    private String name;

    @ApiModelProperty(value = "valid_restroom_num", required = true)
    private Integer validRoomNum;
}
