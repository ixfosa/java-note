package com.ixfosa.controller;

import com.ixfosa.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by ixfosa on 2021/4/12 16:31
 */
@Controller
public class FormatCoteroller {

    @RequestMapping(value = "format", produces = "application/json;charset=utf-8")
    @ResponseBody
    public Object format(User user) {
        return new User(user.getId(), user.getName(), user.getBirthday());
    }
}
