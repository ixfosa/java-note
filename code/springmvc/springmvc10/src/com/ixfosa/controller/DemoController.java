package com.ixfosa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by ixfosa on 2021/4/11 16:00
 */

@Controller
public class DemoController {
    @RequestMapping("demo")
    public void demo() {
        System.out.println("demo...");
    }
}
