package top.ixfosa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import top.ixfosa.domain.User;
import top.ixfosa.service.UserService;

import java.util.List;

/**
 * Created by ixfosa on 2021/6/1 11:40
 */
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/{page}")
    public String showPage(@PathVariable String page) {
        System.out.println(page);
        return page;
    }

    @RequestMapping("/addu")
    public String add(User user) {
        userService.addUser(user);
        return "redirect:selu";
    }

    @RequestMapping("/selu")
    public String sel(Model model) {
        List<User> list = userService.selUser();
        model.addAttribute("list", list);
        return "show";
    }

    @RequestMapping("/delu/{id}")
    public String del(@PathVariable Integer id) {
        userService.delUser(id);
        return "redirect:/selu";
    }

    @RequestMapping("/findu/{id}")
    public String selById(@PathVariable Integer id, Model model) {
        User user = userService.selById(id);
        model.addAttribute(user);
        return "upd";
    }

    @RequestMapping("/updu")
    public String upd(User user) {
        userService.updUser(user);
        return "redirect:selu";
    }
}
