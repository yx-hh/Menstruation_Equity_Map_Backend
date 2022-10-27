/**
 * Floor information class.
 *
 * @author ruiyan ma
 */

package edu.uci.objects;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("Floor")
public class Floor {
    public Floor(String id, String name, int validRoomNum) {
        this.id = id;
        this.name = name;
        this.validRoomNum = validRoomNum;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getValidRoomNum() {
        return validRoomNum;
    }

    @Override
    public String toString() {
        return "Floor{" + "id='" + id + '\'' + ", name='" + name + '\'' + ", validRoomNum=" + validRoomNum + '}';
    }

    @ApiModelProperty(value = "floor_id", required = true)
    private final String id;

    @ApiModelProperty(value = "floor_name", required = true)
    private final String name;

    @ApiModelProperty(value = "valid_restroom_num", required = true)
    private final int validRoomNum;
}
