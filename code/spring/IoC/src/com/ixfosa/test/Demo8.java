package com.ixfosa.test;

import com.ixfosa.pojo.User;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by ixfosa on 2021/4/6 14:47
 */
public class Demo8 {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("applicationContext.xml");

        User user4 = applicationContext.getBean("user4", User.class);
        System.out.println(user4);
        // User{id=0, name='null', set=null, list=null, map={name1=long, name2=zhong}, ints=null}
    }
}
