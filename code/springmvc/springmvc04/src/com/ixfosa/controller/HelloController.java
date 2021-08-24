package com.ixfosa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by ixfosa on 2021/4/11 16:00
 */

@Controller
@RequestMapping("hello")
public class HelloController {

    @RequestMapping("hellolong")
    public String helloLong() {
        System.out.println("hello long...");

        // 转发（默认）走视图解析器
        // return "hello";

        // 明确指出了 forward，redirect 就不走视图解析器
        return "forward:/WEB-INF/hello.jsp";     // 转发（默认）

    }


    @RequestMapping("hellozhong")
    public String helloZhong() {

        System.out.println("hello zhong...");

        // 重定向是客户端的，而转发是服务端内部的。
        // 重定向是让客户端去访问重定向的地址，而WEB-INF下的文件是不能通过外部访问的！
        // return "redirect:/WEB-INF/hello.jsp";

        // 间接跳转
        return "redirect:/hello/hellolong";
    }
}
