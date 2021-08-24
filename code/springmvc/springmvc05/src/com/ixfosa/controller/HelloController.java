package com.ixfosa.controller;

import com.ixfosa.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class HelloController {
    // produces 表示响应头中 Content-Type 取值.
    @RequestMapping(value = "demo1", produces = "text/html;charset=utf-8")
    @ResponseBody
    public String demo1() {
        System.out.println("demo1...");
        return "小佛";
    }

    @RequestMapping(value = "demo2")
    @ResponseBody
    public void demo2(HttpServletResponse response) throws IOException {
        System.out.println("demo2...");
        // produces = "text/html;charset=utf-8" 无效
        response.setHeader("content-type", "text/html;charset=utf-8");
        PrintWriter writer = response.getWriter();
        writer.write("大龙虾。。。");
        writer.flush();
        writer.close();
    }

    @RequestMapping(value = "demo3", produces = "application/json;charset=utf-8")
    @ResponseBody
    public Object demo3() {
        System.out.println("demo3...");
        User user = new User(1, "大龙虾", 22, '女');
        return user;
    }

    @RequestMapping(value = "demo4", produces = "application/json;charset=utf-8")
    @ResponseBody
    public Object demo4() {
        User user1 = new User(1, "大龙虾", 22, '女');
        User user2 = new User(1, "大龙虾", 22, '女');
        User user3 = new User(1, "大龙虾", 22, '女');
        List<User> list = new ArrayList<>();
        list.add(user1);
        list.add(user2);
        list.add(user2);
        return list;
    }

    @RequestMapping(value = "demo5")
    @ResponseBody
    public Object demo5() {
        int[] ints = {1, 2, 3};
        return ints;
    }

    @RequestMapping(value = "demo6")
    @ResponseBody
    public Object demo6() {
        System.out.println("demo3...");
        User user1 = new User(1, "大龙虾", 22, '女');
        User user2 = new User(1, "大龙虾", 22, '女');
        User user3 = new User(1, "大龙虾", 22, '女');
        Map<String, User> map = new HashMap<>();
        map.put("user1", user1);
        map.put("user2", user2);
        map.put("user3", user3);
        return map;
    }
}
