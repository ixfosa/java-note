package com.ixfosa.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ixfosa.pojo.Student;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;


@Controller
public class MyController {

    @RequestMapping(value = "/some")
    public ModelAndView doSome(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("show");
        return mv;
    }
}
