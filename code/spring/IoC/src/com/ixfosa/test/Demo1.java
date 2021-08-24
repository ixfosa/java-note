package com.ixfosa.test;

import com.ixfosa.pojo.Person;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by ixfosa on 2021/4/6 14:47
 */
public class Demo1 {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("applicationContext1.xml");

        Person person = applicationContext.getBean("person", Person.class);
        System.out.println(person); // Person{id=null, name='null', age=null, sex=null}

        String[] names = applicationContext.getBeanDefinitionNames();
        for (String name : names) {
            System.out.println(name); // person

        }
    }
}
