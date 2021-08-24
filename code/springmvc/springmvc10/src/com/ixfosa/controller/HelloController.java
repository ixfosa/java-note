package com.ixfosa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by ixfosa on 2021/4/11 16:00
 */

@Controller
public class HelloController {
    @RequestMapping("hello")
    public String hello(Model model) {
        System.out.println("hello...");
        model.addAttribute("model", "我爱吃青菜！！！");
        return "index.jsp";
    }
}
