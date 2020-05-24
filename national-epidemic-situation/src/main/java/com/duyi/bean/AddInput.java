package com.duyi.bean;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**境外输入新增趋势
 * @author cr
 */
@AllArgsConstructor @Data
@NoArgsConstructor
@TableName("add_input")
public class AddInput implements Serializable {
    /**
     * data时间
     */
    private String data;
    /**
     * importedCase 当天境外输入人数
     */
    private Integer importedCase;
}
