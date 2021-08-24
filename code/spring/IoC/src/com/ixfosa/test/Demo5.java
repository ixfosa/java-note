package com.ixfosa.test;

import com.ixfosa.pojo.Person;
import com.ixfosa.pojo.User;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by ixfosa on 2021/4/6 14:47
 */
public class Demo5 {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("applicationContext.xml");

        User user1 = applicationContext.getBean("user1", User.class);
        System.out.println(user1);

        User user11 = applicationContext.getBean("user11", User.class);
        System.out.println(user11);
    }
}
