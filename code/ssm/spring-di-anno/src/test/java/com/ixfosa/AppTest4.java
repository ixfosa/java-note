package com.ixfosa;

import com.ixfosa.demo4.Student;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Unit test for simple App.
 */
public class AppTest4 {
    @Test
    public void test01() {
        ClassPathXmlApplicationContext ac =
                new ClassPathXmlApplicationContext("applicationContext.xml");

        Student student = ac.getBean("student4", Student.class);

        System.out.println(student);
    }

}
