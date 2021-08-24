package com.ixfosa;

import com.ixfosa.dao.StudentDao;
import com.ixfosa.pojo.Student;
import com.ixfosa.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

/**
 * Hello world!
 *
 */
public class App {
    public static void main( String[] args ) {

        SqlSession sqlSession = MybatisUtils.getSqlSession();

        StudentDao studentDao = sqlSession.getMapper(StudentDao.class);
        List<Student> students = studentDao.selectStudents();
        System.out.println(students);

        Student student = new Student();
        student.setId(3);
        student.setName("zhong");
        student.setEmail("zhong.@163.com");
        student.setAge(21);

        int i = studentDao.insertStudent(student);
        if (i > 0) {
            System.out.println("insertStudent success");
        }
        sqlSession.commit();
        sqlSession.close();
    }
}
