package com.ixfosa;

import com.ixfosa.demo4.Student;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.assertTrue;

/**
 * Unit test for simple App.
 */
public class AppTest4 {
    @Test
    public void test01() {
        ClassPathXmlApplicationContext ac =
                new ClassPathXmlApplicationContext("applicationContext4.xml");

        Student student = ac.getBean("student", Student.class);

        System.out.println(student);
    }

}
