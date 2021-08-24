package com.ixfosa.test;

import com.ixfosa.pojo.User;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by ixfosa on 2021/4/6 14:47
 */
public class Demo7 {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("applicationContext.xml");

        User user3 = applicationContext.getBean("user3", User.class);
        System.out.println(user3);

        User user33 = applicationContext.getBean("user33", User.class);
        System.out.println(user33);
        // User{id=0, name='null', set=null, list=[long, zhong], map=null, ints=null}
        // User{id=0, name='null', set=null, list=[long], map=null, ints=null}
    }
}
