package com.duyi.bean;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**全国现有趋势折线图
 * @author cr
 */
@Data @AllArgsConstructor
@NoArgsConstructor
@TableName("imgdata")
public class Curve implements Serializable {
    /**
     * data 时间
     */
    private String data;
    /**
     * nowConfirm 现有确证
     */
    private Integer nowConfirm;
}
