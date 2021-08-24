package com.ixfosa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class MyController {

    // @RequestMapping(value = "/some/path")
    @RequestMapping(value = "some/path")
    public ModelAndView doSome(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("show");
        return mv;
    }
}
