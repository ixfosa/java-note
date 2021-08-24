package com.ixfosa.test;

import com.ixfosa.pojo.User;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by ixfosa on 2021/4/6 14:47
 */
public class Demo10 {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("applicationContext.xml");

        User user6 = applicationContext.getBean("user6", User.class);
        System.out.println(user6.getPerson());
        // Person{id=0, name='ixfosa', age=0, sex=男}
    }
}
