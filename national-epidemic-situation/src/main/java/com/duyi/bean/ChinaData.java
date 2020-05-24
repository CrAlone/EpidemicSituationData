package com.duyi.bean;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.io.Serializable;

/**用于折线图数据
 * @author cr
 */
@Data @AllArgsConstructor
@NoArgsConstructor
@TableName("disease")
public class ChinaData implements Serializable {
    /**
     * name 地区名
     */
    private String area;
    /**
     * nowConfirm 现有确诊
     */
    private Integer nowConfirm;
    /**
     * confirm 累计确诊
     */
    private Integer confirm;
    /**
     * heal 治愈
     */
    private Integer heal;
    /**
     * dead 死亡
     */
    private Integer dead;
    /**
     * addAndCut 新增或则减少
     */
    private Integer addAndCut;
    /**
     * 持续的天数
     */
    private Integer dayNum;
}
