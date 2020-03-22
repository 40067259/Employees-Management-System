package com.concordia.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.Map;

/**
 * @author Fred Zhang
 * @create 2020-03-18 11:46 AM
 */
@Controller
public class HelloController {
    @ResponseBody
    @RequestMapping("/hello")
    public String hello(){

        return "Hello Spring Boot";
    }

    @RequestMapping({"/","/login"})
    public String index(){
         return "login";
    }

    @ResponseBody
    @RequestMapping("/success")
    public String success(Map<String,Object> map){

        map.put("hello","<h1>Hi,nihao<h1>");
        map.put("users", Arrays.asList("aloaf","Fred","elsa"));
        return "success!!";
    }
}
