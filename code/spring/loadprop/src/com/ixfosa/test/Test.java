package com.ixfosa.test;

import com.ixfosa.pojo.User;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by ixfosa on 2021/4/9 15:31
 */
public class Test {
    public static void main(String[] args) {

        ClassPathXmlApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("applicationContext.xml");

        User user = applicationContext.getBean("user", User.class);
        System.out.println(user);
    }
}
