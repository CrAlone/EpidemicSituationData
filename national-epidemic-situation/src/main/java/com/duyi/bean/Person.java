package com.duyi.bean;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author cr
 */
@Data @AllArgsConstructor
@NoArgsConstructor
@TableName("person")
public class Person implements Serializable {
    /**
     * name 账号
     */
    private String name;
    /**
     * password 密码
     */
    private String password;
    /**
     * mail 邮件
     */
    private String mail;
}
