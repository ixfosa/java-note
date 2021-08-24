package com.ixfosa.controller;


import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by ixfosa on 2021/4/10 17:37
 */
public class DemoController implements Controller {
    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println("Hello SpringMVC!!!");
        ModelAndView modelAndView = new ModelAndView("hello.jsp");
        return modelAndView;
    }
}
