package com.ixfosa.test;

import com.ixfosa.pojo.User;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by ixfosa on 2021/4/6 14:47
 */
public class Demo9 {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("applicationContext.xml");

        User user5 = applicationContext.getBean("user5", User.class);
        System.out.println(user5);

        User user55 = applicationContext.getBean("user55", User.class);
        System.out.println(user55);
        // User{id=0, name='null', set=null, list=null, map=null, ints=[1, 2, 3]}
        // User{id=0, name='null', set=null, list=null, map=null, ints=[66]}
    }
}
