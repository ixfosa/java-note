package top.ixfosa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by ixfosa on 2021/6/7 15:33
 */
@Controller
public class MyController {

    @RequestMapping("hello")
    public String hello() {
        System.out.println("lonffsfsfsfaffg...");
        return "index";
    }
}
