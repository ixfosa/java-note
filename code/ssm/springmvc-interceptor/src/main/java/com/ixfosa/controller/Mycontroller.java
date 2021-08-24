package com.ixfosa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by ixfosa on 2021/4/25 15:29
 */
@Controller
public class Mycontroller {
    @RequestMapping("dosome")
    public ModelAndView doSome(String name, Integer age) {
        System.out.println("doIntercptor...");
        ModelAndView mv = new ModelAndView();
        mv.addObject("name", name);
        mv.addObject("age", age);
        mv.setViewName("show");
        return mv;
    }

    // preHandle...夏---12
    // preHandle2...
    // doIntercptor...
    // postHandle2...
    // postHandle...夏---12
    // show
    // afterCompletion2...
    // afterCompletion...夏---12

    // name:hello 夏
    // age:112
}
