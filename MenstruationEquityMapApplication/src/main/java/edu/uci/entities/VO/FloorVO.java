/**
 * Floor information class.
 *
 * @author ruiyan ma
 */

package edu.uci.entities.VO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@ApiModel("Floor")
@Data
public class FloorVO {

    @ApiModelProperty(value = "floor_name", required = true)
    private String name;

    @ApiModelProperty(value = "valid_restroom_num", required = true)
    private Integer validRoomNum;

    @ApiModelProperty(value = "valid_restroom_list", required = true)
    private List<Integer> restroomList;
}
