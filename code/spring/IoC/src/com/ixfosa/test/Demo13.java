package com.ixfosa.test;

import com.ixfosa.pojo.Flower;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by ixfosa on 2021/4/6 14:47
 */

public class Demo13 {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("applicationContext.xml");

        Flower flower1 = applicationContext.getBean("flower1", Flower.class);
        Flower flower11 = applicationContext.getBean("flower1", Flower.class);
        System.out.println(flower1 == flower11);
        Flower flower2 = applicationContext.getBean("flower2", Flower.class);
        Flower flower22 = applicationContext.getBean("flower2", Flower.class);
        System.out.println(flower2 == flower22);

    }
}