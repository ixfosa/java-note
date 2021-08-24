package top.ixfosa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import top.ixfosa.domain.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

/**
 * Created by ixfosa on 2021/5/31 16:20
 */
@Controller
public class MyController {

    @RequestMapping("show")
    public String show(Model model, HttpServletRequest req) {
        model.addAttribute("msg", "Hello Thymeleaf...");
        model.addAttribute("user", new User(1, "ixfosa", 23));

        req.setAttribute("req", "HttpServletRequest");

        req.getSession().setAttribute("sess", "HttpSession");

        req.getServletContext().setAttribute("app", "Application");

        return "show";
    }

    @RequestMapping("param")
    @ResponseBody
    public Object param(User user) {
        user.setAge(23);
        return user;
    }

    @RequestMapping("restful/{id}/{name}")
    @ResponseBody
    public Object restful(User user) {
        user.setAge(21);
        return user;
    }
}
