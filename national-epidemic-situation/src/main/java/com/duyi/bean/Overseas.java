package com.duyi.bean;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**城外输入省事top10
 * @author cr
 */
@AllArgsConstructor@Data
@NoArgsConstructor
@TableName("overseas")
public class Overseas  implements Comparable<Overseas>{
    /**
     * name 地区名
     */
    private String area;
    /**
     * tip 境外输出人数
     */
    private Integer tip;

    @Override
    public int compareTo(Overseas o) {
        return o.getTip() - this.getTip();
    }
}

