package com.ixfosa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by ixfosa on 2021/4/10 18:12
 */

@Controller
public class DemoController {
    // @RequestMapping("hello")
    @RequestMapping(value = "hello")
    public String hello() {
        System.out.println("hello ixfosa!!!");


        // 配置了视图解析器
        // InternalResourceViewResolver
        return "hello";
        // return "hello.jsp";
        // 默认都以 forward 的方式
    }
}
