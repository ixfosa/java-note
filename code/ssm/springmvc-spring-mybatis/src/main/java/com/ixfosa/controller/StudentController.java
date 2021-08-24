package com.ixfosa.controller;

import com.ixfosa.pojo.Student;
import com.ixfosa.service.StudentService;
import com.sun.net.httpserver.spi.HttpServerProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.swing.*;
import java.util.List;

/**
 * Created by ixfosa on 2021/4/24 20:20
 */

@Controller
@RequestMapping("stu")
public class StudentController {
    @Resource
    private StudentService studentService;

    @RequestMapping("add")
    public String addStu(HttpServletRequest req, Student student) {
        int i = studentService.addStu(student);
        if (1> 0) {
            req.setAttribute("tips", "学生【" + student.getName() + "】注册成功");
            System.out.println(i);
            return "show";
        }
        return "redirect:index.jsp";
    }

    @RequestMapping("show")
    @ResponseBody
    public List<Student> showStu() {
        return studentService.showStu();
    }
}
