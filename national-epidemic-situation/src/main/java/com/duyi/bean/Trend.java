package com.duyi.bean;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**全国累计确证/治愈/死亡趋势折线图
 * @author cr
 */
@Data @AllArgsConstructor
@NoArgsConstructor
@TableName("trend")
public class Trend implements Serializable {
    /**
     * confirm 累计确证
     */
    private Integer confirm;
    /**
     * heal 累计治愈
     */
    private Integer heal;
    /**
     * dead 累计死亡
     */
    private Integer dead;
    /**
     * data 日期
     */
    private String data;
}
