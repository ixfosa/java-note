package com.ixfosa;

import static org.junit.Assert.assertTrue;

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
                new ClassPathXmlApplicationContext("applicationContext1.xml");

        Student student = ac.getBean("student", Student.class);
        System.out.println(student);


        Date mydate = ac.getBean("mydate", Date.class);
        System.out.println(mydate.getYear());
    }
}
