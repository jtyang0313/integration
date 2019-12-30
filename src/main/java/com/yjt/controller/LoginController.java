package com.yjt.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author ChengJianSheng
 * @date 2019-02-12
 */
@Controller
public class LoginController {

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @ResponseBody
    @GetMapping("/test")
    public String login2() {
    	System.out.println("===========多多2============");
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	System.out.println(authentication.getPrincipal());
        return "test ok";
    }
    
    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/index")
    public String index2() {
        return "index";
    }
}
