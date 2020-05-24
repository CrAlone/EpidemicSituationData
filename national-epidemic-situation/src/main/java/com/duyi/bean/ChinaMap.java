package com.duyi.bean;


import lombok.AllArgsConstructor;
import lombok.Data;



/**
 * @author cr
 */
@AllArgsConstructor@Data
public class ChinaMap{
    /**
     * name 省名
     */
    private String name;
    /**
     *nowConfirm 确证人数
     */
    private Integer value;
}
