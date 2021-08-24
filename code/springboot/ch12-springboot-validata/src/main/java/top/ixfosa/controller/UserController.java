package top.ixfosa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import top.ixfosa.domain.User;

import javax.validation.Valid;

@Controller
public class UserController {

    @RequestMapping("/add")
    public String add(@ModelAttribute("user") User user) {
        return "add";
    }

    // @Valid 开启对 Users 对象的数据校验
    // BindingResult:封装了校验的结果
    @RequestMapping("/save")
    public String save(@Valid User user, BindingResult result) {
        System.out.println(user);
        System.out.println(result);
        if (result.hasErrors()) {
            System.out.println("err...");
            return "add";
        }
        return "ok";
    }
}
