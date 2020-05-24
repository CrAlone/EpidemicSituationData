package com.duyi.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**用于向浏览器发送信息
 * @author cr
 */
@Data@AllArgsConstructor
@NoArgsConstructor
public class Message {
    /**
     * code返回状态吗
     */
    private Integer code;
    /**
     * msg 返回信息
     */
    private String msg;
    /**
     * data 返回对象
     */
    private Object data;
}
