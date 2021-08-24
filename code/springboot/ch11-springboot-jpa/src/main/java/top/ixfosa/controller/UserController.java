package top.ixfosa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import top.ixfosa.domain.User;
import top.ixfosa.service.UserService;

import java.util.List;


@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/addu")
    @ResponseBody
    public String add(User user) {
        userService.addUser(user);
        return "ok";
    }

    @RequestMapping("/selu")
    @ResponseBody
    public Object sel() {
        List<User> list = userService.selUser();
        return list;
    }

    @RequestMapping("/delu/{id}")
    @ResponseBody
    public String del(@PathVariable Integer id) {
        userService.delUser(id);
        return "ok";
    }

    @RequestMapping("/findu/{id}")
    @ResponseBody
    public Object selById(@PathVariable Integer id) {
        User user = userService.selById(id);
        return user;
    }

    @RequestMapping("/updu")
    @ResponseBody
    public String upd(User user) {
        userService.updUser(user);
        return "ok";
    }
}
