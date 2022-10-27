/**
 * Building information class.
 *
 * @author ruiyan ma
 */

package edu.uci.objects;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

@ApiModel("Building information")
public class Building {
    public Building(String id, String name,
                    double latitude, double longitude) {
        this.id = id;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Building(String id, String name,
                    double latitude, double longitude,
                    int distance, int walkingTime) {
        this.id = id;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.distance = distance;
        this.walkingTime = walkingTime;
    }

    public Building(String id, String name,
                    double latitude, double longitude,
                    int distance, int walkingTime,
                    List<Floor> floors) {
        this.id = id;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.distance = distance;
        this.walkingTime = walkingTime;
        this.floors = floors;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public int getDistance() {
        return distance;
    }

    public int getWalkingTime() {
        return walkingTime;
    }

    public List<Floor> getFloors() {
        return floors;
    }

    @Override
    public String toString() {
        return "Building{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", distance=" + distance +
                ", walkingTime=" + walkingTime +
                ", floors=" + floors +
                '}';
    }

    @ApiModelProperty(value = "building_id", required = true)
    private final String id;

    @ApiModelProperty(value = "building_name", required = true)
    private final String name;

    @ApiModelProperty(value = "latitude", required = true)
    private final double latitude;

    @ApiModelProperty(value = "longitude", required = true)
    private final double longitude;

    @ApiModelProperty(value = "distance")
    private int distance;

    @ApiModelProperty(value = "walking_time")
    private int walkingTime;

    @ApiModelProperty(value = "floors_list")
    private List<Floor> floors;
}
