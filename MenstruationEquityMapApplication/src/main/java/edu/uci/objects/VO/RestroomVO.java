/**
 * Restroom information class.
 *
 * @author ruiyan ma
 */

package edu.uci.objects.VO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("Restroom")
@Data
public class RestroomVO {

    @ApiModelProperty(value = "restroom_id", required = true)
    private int restroomId;

    @ApiModelProperty(value = "floor_name", required = true)
    private String floorName;

    @ApiModelProperty(value = "room_num", required = true)
    private String roomNum;
}
