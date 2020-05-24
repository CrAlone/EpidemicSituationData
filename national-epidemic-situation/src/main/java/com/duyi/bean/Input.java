package com.duyi.bean;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**境外输入累计趋势折线图
 * @author cr
 */
@Data @AllArgsConstructor
@NoArgsConstructor
@TableName("input")
public class Input implements Serializable {
    /**
     * data时间
     */
    private String data;
    /**
     * 累计境外输入人数
     */
    private Integer importedCase;
}
