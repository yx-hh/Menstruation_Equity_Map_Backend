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
    public Floor(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Floor{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    @ApiModelProperty(value = "floor_id", required = true)
    private final String id;

    @ApiModelProperty(value = "floor_name", required = true)
    private final String name;
}
