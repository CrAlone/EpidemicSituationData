package com.duyi.bean;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author cr
 */
@AllArgsConstructor @Data
@NoArgsConstructor
@TableName("statistics")
public class Statistics implements Serializable {
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
     * nowConfirm 现有确证
     */
    private Integer nowConfirm;
    /**
     * noInfect 无症状感染者
     */
    private Integer noInfect;
    /**
     * importedCase 境外输入
     */
    private Integer importedCase;
    /**
     * 统计截止时间
     */
    private String lastTime;
}
