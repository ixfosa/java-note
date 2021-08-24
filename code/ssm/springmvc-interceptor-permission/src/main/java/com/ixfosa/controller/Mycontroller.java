package com.ixfosa.controller;

import com.ixfosa.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

/**
 * Created by ixfosa on 2021/4/25 15:29
 */
@Controller
public class Mycontroller {

    @RequestMapping("{page}")
    public String main(@PathVariable String page) {
        System.out.println("restful: " + page);
        return page;
    }

    @RequestMapping("login")
    public String doSome(User user, HttpSession session) {
        if ("ixfosa".equals(user.getName()) || "123456".equals(user.getPassword())) {
            session.setAttribute("user", user);
            return "show";
        }
        return "redirect:/index.jsp";
    }
}
