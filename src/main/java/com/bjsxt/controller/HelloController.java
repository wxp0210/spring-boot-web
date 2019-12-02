package com.bjsxt.controller;


import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.Map;

@Controller
public class HelloController {


    //显示登录主页
    /*@RequestMapping("/*")
    public String showIndex(){
        return "index";
    }*/


    //登录
    @RequestMapping(value = "/login")
    public String login(@RequestParam("username") String username, @RequestParam("password") String password, Map<String,Object> map, HttpSession session){
        System.out.println(password);
        if(!StringUtils.isEmpty(username) && "123456".equalsIgnoreCase(password)){
            //登陆成功
            session.setAttribute("loginUser",username);
            return "redirect:/main.html";
        }else {
            map.put("msg","密码错误，请重新登录");
            return "login";
        }
    }

    //测试springboot快速显示页面
    @RequestMapping("/success")
    public String show( Map<String,Object> map){
        //System.out.println(page);
        map.put("hello","你好");
        map.put("hey","<h1>啦啦啦</h1>");
        map.put("users", Arrays.asList("wxp","cmx","sjx","lyl"));
        return "success";
    }

}
