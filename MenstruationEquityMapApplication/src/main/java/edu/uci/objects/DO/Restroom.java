package edu.uci.objects.DO;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.Date;

/**
 * RestroomVO class, represents a Restroom.
 *
 * @author ruiyan ma
 */
@Entity
@Data
@Accessors(chain = true)
@Table(name = "restroom")
public class Restroom{

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
    private Boolean productStatus;

    @Column(name = "deleted")
    private Boolean deleted;

    /**
     * UTC time
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * UTC time
     */
    @Column(name = "update_time")
    private Date updateTime;
}
