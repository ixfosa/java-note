package com.ixfosa.servlet;

import com.ixfosa.pojo.Student;
import com.ixfosa.service.StudentService;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/stu")
public class StudentServlet extends BaseServlet {

    public String regist(HttpServletRequest req, HttpServletResponse res) {

        String name = req.getParameter("name");
        String email = req.getParameter("email");
        int age = Integer.parseInt(req.getParameter("age"));

        Student student = new Student(name, email, age);

        int regist = super.service.regist(student);

        if (regist > 0) {
            return "index.jsp";
        }
        return "redirect:regist.jsp";
    }
}
