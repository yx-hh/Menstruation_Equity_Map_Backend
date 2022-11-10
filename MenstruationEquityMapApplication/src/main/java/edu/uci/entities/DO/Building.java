package edu.uci.entities.DO;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;

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
public class Building extends ComDO {
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
}
