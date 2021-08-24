package com.ixfosa;

import com.ixfosa.demo5.Student;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Unit test for simple App.
 */
public class AppTest5 {
    @Test
    public void test01() {
        ClassPathXmlApplicationContext ac =
                new ClassPathXmlApplicationContext("applicationContext.xml");

        Student student = ac.getBean("student5", Student.class);
        System.out.println(student);
    }
}
