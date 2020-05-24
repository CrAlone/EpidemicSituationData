package com.duyi.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.duyi.bean.Message;
import com.duyi.bean.Person;
import com.duyi.service.BasePerson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**用于处理登录
 * @author cr
 */
@Controller
public class LoginController {
    @Autowired
    private BasePerson person;
    @GetMapping("/login")
    public String login(){
        return "login";
    }
    @PostMapping("/login")
    @ResponseBody
    public Message login(@RequestParam("username")String username, @RequestParam("password")String password,
                         Map<String,Object> map, HttpSession session, Model model){
        Message message;
        //从数据库中获取值
        Person p = person.getOne(new QueryWrapper<Person>().eq("name",username));
        if (p != null && password.equals(p.getPassword())){
            session.setAttribute("user",p);
            message = new Message(200,"登录成功",p);
        }else {
            message = new Message();
            message.setCode(400);
            message.setMsg("用户名或密码不正确");
        }
        return message;
    }
}
