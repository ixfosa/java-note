package top.ixfosa.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;
import top.ixfosa.entity.User;
import top.ixfosa.service.UserService;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ixfosa
 * @since 2021-08-23
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService service;

    @ResponseBody
    @RequestMapping("/list")
    public List<User> userList() {
        return service.list();
    }
}

