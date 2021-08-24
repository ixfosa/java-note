package com.ixfosa;

import com.ixfosa.demo6.Student;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Unit test for simple App.
 */
public class AppTest6 {
    @Test
    public void test01() {
        ClassPathXmlApplicationContext ac =
                new ClassPathXmlApplicationContext("applicationContext.xml");

        Student student = ac.getBean("student6", Student.class);

        System.out.println(student);
    }
}
