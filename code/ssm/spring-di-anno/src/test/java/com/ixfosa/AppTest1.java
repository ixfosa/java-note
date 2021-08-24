package com.ixfosa;

import com.ixfosa.demo1.Student;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Date;

/**
 * Unit test for simple App.
 */
public class AppTest1 {
    @Test
    public void test01() {
        ClassPathXmlApplicationContext ac =
                new ClassPathXmlApplicationContext("applicationContext.xml");

        Student student = ac.getBean("student1", Student.class);
        System.out.println(student);
    }
}
