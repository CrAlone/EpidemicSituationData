package com.duyi.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

/**
 * @author cr
 */
@Component
public class MyMail {
    @Autowired
    private JavaMailSender sender;
    public void send(String title,String text,String to){
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        //标题
        mailMessage.setSubject(title);
        //内容
        mailMessage.setText(text);
        //收件人
        mailMessage.setTo(to);
        //发件人
        mailMessage.setFrom("1071954519@qq.com");
        sender.send(mailMessage);
    }

}
