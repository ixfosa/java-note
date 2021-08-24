package com.ixfosa;

import com.ixfosa.dao.StudentDao;
import com.ixfosa.dao.impl.StudentDaoImpl;
import com.ixfosa.pojo.Student;

import java.util.List;

/**
 * Hello world!
 *
 */
public class App {



    public static void main( String[] args ) {

        StudentDao studentDao = new StudentDaoImpl();
        List<Student> studentList = studentDao.selectStudents();
        System.out.println(studentList);

        Student student = new Student();
        // student.setId(10);
        student.setName("hao");
        student.setEmail("hao.@163.com");
        student.setAge(22);
        int i = studentDao.insertStudent(student);

        if (i >0 ) {
            System.out.println("insert success");
        }
    }
}
