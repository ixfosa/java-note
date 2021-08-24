package com.ixfosa.test;

import com.ixfosa.pojo.Person;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by ixfosa on 2021/4/6 14:47
 */
public class Demo4 {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("applicationContext.xml");

        Person person = applicationContext.getBean("person4", Person.class);
        // Person{id=123, name='ixfosa', age=23, sex=男}
        System.out.println(person);
    }
}
