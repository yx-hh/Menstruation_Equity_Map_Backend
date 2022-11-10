/**
 * Floor information class.
 *
 * @author ruiyan ma
 */

package edu.uci.objects.VO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@ApiModel("Floor")
@Data
public class FloorVO {

    @ApiModelProperty(value = "floor_name", required = true)
    private String floorName;

    @ApiModelProperty(value = "valid_room_number")
    private int validRoomNum;

    @ApiModelProperty(value = "restroom_list")
    private List<RestroomVO> restrooms;
}
