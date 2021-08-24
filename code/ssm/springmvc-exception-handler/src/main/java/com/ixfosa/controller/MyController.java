package com.ixfosa.controller;

import com.ixfosa.exception.AgeException;
import com.ixfosa.exception.NameException;
import com.ixfosa.exception.UserException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MyController {
    @RequestMapping("ex")
    public ModelAndView doEx(String name, Integer age) throws UserException {
        if ("ixfosa".equalsIgnoreCase(name) ||name == null) {
            throw new NameException("name err...");
        }

        if (age >  120 || age == null) {
            throw new AgeException("age err...");
        }

        ModelAndView mv = new ModelAndView();
        mv.addObject("name", name);
        mv.addObject("age", age);
        mv.setViewName("show");
        return mv;
    }
}
