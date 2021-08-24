package com.ixfosa.test;

import com.ixfosa.pojo.User;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by ixfosa on 2021/4/6 14:47
 */
public class Demo11 {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("applicationContext.xml");

        User user7 = applicationContext.getBean("user7", User.class);
        System.out.println(user7.getFriends());

        User user77 = applicationContext.getBean("user77", User.class);
        System.out.println(user77.getFriends());
        // [Person{id=0, name='ixfosa', age=0, sex=男}]
        // [Person{id=0, name='ixfosa', age=0, sex=男}, Person{id=0, name='ixfosa', age=0, sex=男}]
    }
}
