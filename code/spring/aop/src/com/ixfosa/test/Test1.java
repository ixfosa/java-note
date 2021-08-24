package com.ixfosa.test;

import com.ixfosa.demo.Demo;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by ixfosa on 2021/4/7 17:17
 */
public class Test1 {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("applicationContext.xml");

        Demo demo = applicationContext.getBean("demo", Demo.class);

        demo.demo();
        demo.demo1("ixfosa - ", 998);
        demo.demo2("ixfosa");
    }
}
