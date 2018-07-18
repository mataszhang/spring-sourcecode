package com.matas.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 *@author matas
 *@date  2018/7/18 16:56
 *@email   mataszhang@163.com
 */
@Controller
@RequestMapping("test")
public class TestController {

    @RequestMapping("hello")
    public String hello(){
        return "hello";
    }
}
