package edu.uci.objects.DO;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;

/**
 * @author Huang Yuxin
 * @date 2022/11/1
 */
@Entity
@Data
@Accessors(chain = true)
@Table( name ="restroom" )
public class Restroom extends ComDO{

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    // todo tobe chnage to building_id
    @Column(name = "building_num")
    private Integer buildingId;

    @Column(name = "floor_name")
    private String floorName;

    @Column(name = "room_num")
    private String roomNum;

    @Column(name = "product_status")
    private boolean productStatus;
}
