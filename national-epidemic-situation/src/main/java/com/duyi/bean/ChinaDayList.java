package com.duyi.bean;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**全国疫情新增趋势折线图
 * @author cr
 */
@AllArgsConstructor@Data
@NoArgsConstructor
@TableName("polyline")
public class ChinaDayList implements Serializable {
    /**
     * confirm 新增确证
     */
    private Integer confirm;
    /**
     * suspect 新增疑似
     */
    private Integer suspect;
    /**
     * data 时间
     */
    private String data;
}
