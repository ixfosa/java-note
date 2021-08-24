package com.ixfosa;

import com.ixfosa.demo5.Student;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.assertTrue;

/**
 * Unit test for simple App.
 */
public class AppTest5 {
    @Test
    public void test01() {
        ClassPathXmlApplicationContext ac =
                new ClassPathXmlApplicationContext("applicationContext5.xml");

        Student student = ac.getBean("student", Student.class);
        System.out.println(student);

        // Student{name='zhong', age=21,
        // school=School{name='江工', address='西湖区'},
        // school2=School{name='江工', address='西湖区'}}
    }
}
