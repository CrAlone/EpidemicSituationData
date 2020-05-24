package com.duyi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**权限拦截
 * @author cr
 */
@Controller
public class AuthorityController {
    @GetMapping("/authority")
    public String authority(){
        return "/authority";
    }
}
