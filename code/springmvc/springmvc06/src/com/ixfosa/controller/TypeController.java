package com.ixfosa.controller;

import com.ixfosa.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.net.SocketTimeoutException;


@Controller
public class TypeController {

    @RequestMapping(value = "typeConverter", produces = "application/json;charset=utf-8")
    @ResponseBody
    public Object typeConverter(User user) {
        System.out.println(user.getName());
        User u = new User(user.getId(), user.getName(), user.getBirthday());
        return u;
    }
}
