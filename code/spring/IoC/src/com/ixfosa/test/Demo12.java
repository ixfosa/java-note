package com.ixfosa.test;

import com.ixfosa.pojo.Flower;
import com.ixfosa.pojo.User;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by ixfosa on 2021/4/6 14:47
 */
public class Demo12 {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("applicationContext.xml");

        Flower flower1 = applicationContext.getBean("flower1", Flower.class);
        System.out.println(flower1);

        Flower flower2 = applicationContext.getBean("flower2", Flower.class);
        System.out.println(flower2);

        // Flower{id=1, name='黑菊花'}
        // Flower{id=2, name='粉菊花'}
    }
}
