package com.duyi.bean;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**全国现有确证构成:
 * @author cr
 */
@AllArgsConstructor@Data
@NoArgsConstructor
@TableName("existing")
public class Existing implements Comparable<Existing>{
    /**
     * name 名称（港澳台病例..）
     */
    private String name;
    /**
     * count 数量
     */
    protected Integer value;

    @Override
    public int compareTo(Existing o) {
        return this.getValue() - o.getValue();
    }
}
