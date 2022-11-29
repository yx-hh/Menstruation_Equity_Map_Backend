package edu.uci.objects.DO;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Date;

/**
 * Building object
 *
 * @author Huang Yuxin
 * @date 2022/11/1
 */
@Entity
@Data
@Table(name = "building")
@Accessors(chain = true)
public class Building {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "building_name")
    private String buildingName;

    @Column(name = "latitude")
    private Double latitude;

    @Column(name = "longitude")
    private Double longitude;

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
