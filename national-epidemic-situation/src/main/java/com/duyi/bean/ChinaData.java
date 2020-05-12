package com.duyi.bean;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author cr
 */
@Data @AllArgsConstructor
public class ChinaData {
    /**
     * name 地区名
     */
    private String area;
    /**
     * nowConfirm 现有确诊
     */
    private int nowConfirm;
    /**
     * confirm 累计确诊
     */
    private int confirm;
    /**
     * heal 治愈
     */
    private int heal;
    /**
     * dead 死亡
     */
    private int dead;
}
