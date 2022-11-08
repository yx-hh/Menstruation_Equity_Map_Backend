package edu.uci.objects;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * building address
 * @author Huang Yuxin
 * @date 2022/11/3
 */
@Data
@AllArgsConstructor
@Accessors(chain = true)
public class Baddress {

    private Double latitude;

    private Double longitude;
}
