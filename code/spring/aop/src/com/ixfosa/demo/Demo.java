package com.ixfosa.demo;

import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * Created by ixfosa on 2021/4/7 17:19
 */

@Component
public class Demo {


    public void demo() {
        // int a = 1 / 0;
        System.out.println("无参 -- demo...");
    }

    public void demo1(String arg0, int arg1) {
        System.out.println("demo1: " + arg0 + arg1);
    }

    public void demo2(String arg0) {
        System.out.println("demo2: " + arg0);
    }
}
