package com.ixfosa;

import static org.junit.Assert.assertTrue;

import com.ixfosa.dao.StudentDao;
import com.ixfosa.pojo.MyStudent;
import com.ixfosa.pojo.Student;
import com.ixfosa.pojo.ViewStudent;
import com.ixfosa.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;
import java.util.Map;

/**
 * Unit test for simple App.
 */
public class AppTest {

    @Test
    public void testSelectStudentReturnViewStudent() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        StudentDao dao = sqlSession.getMapper(StudentDao.class);
        ViewStudent viewStudent = dao.selectStudentReturnViewStudent(1);
        System.out.println(viewStudent);
        sqlSession.close();
    }

    @Test
    public void testCountStudent() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        StudentDao dao = sqlSession.getMapper(StudentDao.class);
        int i = dao.countStudent();
        System.out.println(i);
        sqlSession.close();
    }


    @Test
    public void testSelectMapById() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        StudentDao dao = sqlSession.getMapper(StudentDao.class);
        Map<Object, Object> map = dao.selectMapById(1);
        // {name=ixfosa, id=1, email=ixfosa@163.com, age=23}
        System.out.println(map);
        sqlSession.close();
    }

    @Test
    public void testSelectAllStudents() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        StudentDao dao = sqlSession.getMapper(StudentDao.class);
        List<Student> studentList = dao.selectAllStudents();
        for (Student student : studentList) {
            System.out.println(student);
        }
        sqlSession.close();
    }

    @Test
    public void testSelectMyStudent() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        StudentDao dao = sqlSession.getMapper(StudentDao.class);
        List<MyStudent> myStudents = dao.selectMyStudent();
        for (MyStudent myStudent : myStudents) {
            System.out.println(myStudent);
        }
        sqlSession.close();
    }

    @Test
    public void testSelectDiffColProperty() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        StudentDao dao = sqlSession.getMapper(StudentDao.class);
        List<MyStudent> myStudents = dao.selectDiffColProperty();
        for (MyStudent myStudent : myStudents) {
            System.out.println(myStudent);
        }
        sqlSession.close();
    }

    @Test
    public void testSelectLikeOne() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        StudentDao dao = sqlSession.getMapper(StudentDao.class);
        // Preparing: select * from student where email like ?;
        List<Student> studentList = dao.selectLikeOne("%163%");
        for (Student student : studentList) {
            System.out.println(student);
        }
        sqlSession.close();
    }

    @Test
    public void testSelectLikeTwo() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        StudentDao dao = sqlSession.getMapper(StudentDao.class);
        // Preparing: select * from student where email like "%" ? "%";
        List<Student> studentList = dao.selectLikeTwo("163");
        for (Student student : studentList) {
            System.out.println(student);
        }
        sqlSession.close();
    }


}
