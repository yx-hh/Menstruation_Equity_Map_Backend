package edu.uci.objects.VO;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author Huang Yuxin
 * @date 2022/11/30
 */
@ApiModel("Message")
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class MessageVO {
    private String message;
}
