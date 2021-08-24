package top.ixfosa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import top.ixfosa.domain.User;

import java.util.ArrayList;

/**
 * Created by ixfosa on 2021/5/31 16:20
 */
@Controller
public class UserController {

    @RequestMapping("show")
    public String show(Model model) {
        System.out.println("into show...");
        ArrayList<User> list = new ArrayList<>();
        list.add(new User(1, "小佛", 23));
        list.add(new User(2, "大龙虾", 22));
        model.addAttribute("list", list);
        return "show";
    }
}
