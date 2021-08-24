package com.ixfosa.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;


/**
 * Created by ixfosa on 2021/4/10 17:37
 */
@Controller
public class DemoController {

    @RequestMapping("demo1")
    public String demo1(HttpServletRequest request, HttpSession sessionParam) {
        // request 作用域
        request.setAttribute("request", "request");

        // session 作用域
        HttpSession session = request.getSession();
        session.setAttribute("session1", "通过request获取");
        sessionParam.setAttribute("session2", "通过handleMethod参数获取");

        // appliaction 作用域
        ServletContext application = request.getServletContext();
        application.setAttribute("application", "application");
        return "index1.jsp";
    }

    @RequestMapping("demo2")
    public String demo2(Map<String, Object> map) {
        System.out.println(map.getClass());
        map.put("map", "value");
        return "index2.jsp";
    }

    @RequestMapping("demo3")
    public String demo3(Model model) {
        model.addAttribute("model", "model value");
        return "index3.jsp";
    }

    @RequestMapping("demo4")
    public Object demo4() {
        System.out.println("hhhhhhhh");
        ModelAndView modelAndView = new ModelAndView("/index4.jsp");
        modelAndView.addObject("modelAndView", "modelAndView value");
        return modelAndView;
    }
}
