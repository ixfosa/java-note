package com.ixfosa;

import com.ixfosa.demo2.Student;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.assertTrue;

/**
 * Unit test for simple App.
 */
public class AppTest2 {
    @Test
    public void test01() {
        ClassPathXmlApplicationContext ac =
                new ClassPathXmlApplicationContext("applicationContext2.xml");

        Student student = ac.getBean("student", Student.class);
        System.out.println(student);

        // spring会调用类的无参数构造方法创建对象
        // setName:ixfosa
        // setAge:23
        // setSchool:School{name='家里蹲', address='火星'}
        // Student{name='ixfosa', age=23, school=School{name='家里蹲', address='火星'}}
    }
}
