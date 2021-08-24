package com.ixfosa;

import com.ixfosa.demo3.Student;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;



/**
 * Unit test for simple App.
 */
public class AppTest3 {
    @Test
    public void test01() {
        ClassPathXmlApplicationContext ac =
                new ClassPathXmlApplicationContext("applicationContext.xml");


        Student student3 = ac.getBean("student3", Student.class);
        System.out.println(student3);
    }
}