package edu.uci.entities.DO;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;

/**
 * RestroomVO class, represents a Restroom.
 *
 * @author ruiyan ma
 */
@Entity
@Data
@Accessors(chain = true)
@Table(name = "restroom")
public class Restroom extends ComDO {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "building_id")
    private Integer buildingId;

    @Column(name = "floor_name")
    private String floorName;

    @Column(name = "room_num")
    private String roomNum;

    @Column(name = "product_status")
    private boolean productStatus;
}
