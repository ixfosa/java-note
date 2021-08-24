package com.ixfosa;

import static org.junit.Assert.assertTrue;

import com.ixfosa.pojo.Student;
import com.ixfosa.service.StudentService;
import com.ixfosa.service.impl.StudentServiceImpl;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * Unit test for simple App.
 */
public class AppTest {

    @Test
    public void test() {
        ClassPathXmlApplicationContext ac =
                new ClassPathXmlApplicationContext("applicationContext.xml");

        StudentService service = ac.getBean("studentService", StudentServiceImpl.class);

        List<Student> studentList = service.selectStudents();

        for (Student student : studentList) {
            System.out.println(student);
        }
    }

}
