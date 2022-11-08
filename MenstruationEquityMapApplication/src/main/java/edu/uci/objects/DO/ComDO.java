package edu.uci.objects.DO;

import lombok.Data;

import javax.persistence.Column;
import java.util.Date;

/**
 * @author Huang Yuxin
 * @date 2022/11/1
 */
@Data
public class ComDO {

    @Column(name = "deleted")
    private Boolean deleted;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;
}
