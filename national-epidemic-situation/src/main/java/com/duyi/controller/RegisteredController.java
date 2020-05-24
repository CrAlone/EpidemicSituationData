package com.duyi.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.duyi.bean.Message;
import com.duyi.bean.Person;
import com.duyi.config.MyMail;
import com.duyi.service.BasePerson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import java.util.Random;


/**用户注册
 * @author cr
 */
@Controller
public class RegisteredController {
    private int num;
    @Autowired
    private MyMail myMail;
    @Autowired
    private BasePerson person;
    @GetMapping("/registered")
    public String registered(){
        return "registered";
    }
    //注册获取验证码
    @PostMapping("/obtain")
    @ResponseBody
    public Message obtain(@RequestParam("mailCode")String mailCode){
        Message message;
        Person p;
        p = person.getOne(new QueryWrapper<Person>().eq("mail",mailCode));

        if (p == null){
            //获取随机6个数
            num = random();
            try {
                myMail.send("糟老头儿公司","您好，您正在注册糟老头儿疫情系统，验证码为:"+num+",请勿告诉他人",mailCode);
                message = new Message();
                message.setCode(200);
                message.setMsg("发送成功");
                return message;
            }catch (Exception e){
                e.printStackTrace();
                message = new Message();
                message.setCode(500);
                message.setMsg("发送失败!error,邮箱不正确或网络不稳定,请重试");
                return message;
            }
        }
        System.out.println("obtain收到了");
        message = new Message();
        message.setCode(400);
        message.setMsg("该邮箱已注册");
        return message;
    }
    //注册
    @PostMapping("/registered")
    @ResponseBody
    public Message submit(@RequestParam("mailCode")String mailCode, @RequestParam("verificationCode")String verificationCode, @RequestParam("id")String id, @RequestParam("pass")String pass){
        Message message;
        if (Integer.parseInt(verificationCode) != num){
            message = new Message();
            message.setCode(400);
            message.setMsg("验证码不正确");
            return message;
        }else if (person.getOne(new QueryWrapper<Person>().eq("name",id)) != null){
            message = new Message();
            message.setCode(404);
            message.setMsg("id已被使用,请重新填写");
            return message;
        }
        Person p = new Person(id,pass,mailCode);
        boolean flag = person.save(p);
        if (flag){
            message = new Message();
            message.setCode(200);
            message.setMsg("注册成功,即将跳转到登录页面");
            return message;
        }
        message = new Message();
        message.setCode(500);
        message.setMsg("注册失败");
        return message;
    }
    //随机获取6个数字
    private static int random(){
        Random r = new Random();
        return r.nextInt(900000)+100000;
    }
    @GetMapping("/FindBackPass")
    public String findBackPass(){
        return "FindBackPass";
    }
    //找回密码获取验证码
    @PostMapping("/find")
    @ResponseBody
    public Message find(@RequestParam("mailCode")String mailCode){
        Message message;
        Person p;
        p = person.getOne(new QueryWrapper<Person>().eq("mail",mailCode));

        if (p != null){
            //获取随机6个数
            num = random();
            try {
                myMail.send("糟老头儿公司","您好，您正在注册糟老头儿疫情系统，验证码为:"+num+",请勿告诉他人",mailCode);
                message = new Message();
                message.setCode(200);
                message.setMsg("发送成功");
                return message;
            }catch (Exception e){
                e.printStackTrace();
                message = new Message();
                message.setCode(500);
                message.setMsg("发送失败!error,邮箱不正确或网络不稳定,请重试");
                return message;
            }
        }
        message = new Message();
        message.setCode(400);
        message.setMsg("该邮箱账号不存在,请重新输入");
        return message;
    }
    //找回密码
    @PostMapping("/update")
    @ResponseBody
    public Message update(@RequestParam("mailCode")String mailCode,@RequestParam("verificationCode")String verificationCode,
                          @RequestParam("id")String id){
        Message message;
        boolean flag;
        if (Integer.parseInt(verificationCode) != num){
            message = new Message();
            message.setCode(400);
            message.setMsg("验证码不正确");
            return message;
        }
        flag = person.update(new UpdateWrapper<Person>().eq("mail",mailCode).set("password",id));
        if (flag){
            message = new Message();
            message.setCode(200);
            message.setMsg("修改成功,即将跳转到登录页面");
            return message;
        }
        message = new Message();
        message.setCode(500);
        message.setMsg("修改失败");
        return message;
    }
}
