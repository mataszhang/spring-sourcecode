package com.matas.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 *@author matas
 *@date  2018/7/18 16:56
 *@email   mataszhang@163.com
 */
@Controller
@RequestMapping("test")
public class TestController{

    @RequestMapping("")
    public String hello(Model model){
        model.addAttribute("name"," test");
        return "hello";
    }
}
