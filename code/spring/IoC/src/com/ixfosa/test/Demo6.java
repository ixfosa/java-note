package com.ixfosa.test;

import com.ixfosa.pojo.User;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by ixfosa on 2021/4/6 14:47
 */
public class Demo6 {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("applicationContext.xml");

        User user2 = applicationContext.getBean("user2", User.class);
        System.out.println(user2);

        User user22 = applicationContext.getBean("user22", User.class);
        System.out.println(user22);
        // User{id=0, name='null', set=[long, zhong], list=null, map=null, ints=null}
        // User{id=0, name='null', set=[zhong], list=null, map=null, ints=null}
    }
}
