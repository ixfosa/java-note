package com.ixfosa;

import static org.junit.Assert.assertTrue;

import com.ixfosa.dao.StudentDao;
import com.ixfosa.pojo.QueryParam;
import com.ixfosa.pojo.Student;
import com.ixfosa.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;

/**
 * Unit test for simple App.
 */
public class AppTest {

    @Test
    public void testSelectStudentById() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        StudentDao dao = sqlSession.getMapper(StudentDao.class);
        Student student = dao.selectStudentById(2);
        System.out.println(student);
    }

    @Test
    public void testSelectMultiParam() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        StudentDao dao = sqlSession.getMapper(StudentDao.class);
        List<Student> studentList = dao.selectMultiParam("long", 22);
        System.out.println(studentList);
    }

    @Test
    public void testSelectMultiObject() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        StudentDao dao = sqlSession.getMapper(StudentDao.class);
        QueryParam param = new QueryParam();
        param.setParamName("long");
        param.setParamAge(22);
        List<Student> studentList = dao.selectMultiObject(param);
        System.out.println(studentList);
    }

    @Test
    public void testSelectMultiStudent() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        StudentDao dao = sqlSession.getMapper(StudentDao.class);
        Student student = new Student();
        student.setName("zhong");
        student.setAge(21);
        List<Student> studentList = dao.selectMultiStudent(student);
        System.out.println(studentList);
    }

    @Test
    public void testSelectMultiPosition() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        StudentDao dao = sqlSession.getMapper(StudentDao.class);
        List<Student> studentList = dao.selectMultiPosition("zhong", 21);
        System.out.println(studentList);
    }


    @Test
    public void testSelectMultiByMap() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        StudentDao dao = sqlSession.getMapper(StudentDao.class);
        HashMap<String, Object> map = new HashMap<>();
        map.put("myname", "zhong");
        map.put("myage", 21);
        List<Student> studentList = dao.selectMultiByMap(map);
        System.out.println(studentList);
    }

    @Test
    public void testSelectUse$() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        StudentDao dao = sqlSession.getMapper(StudentDao.class);

        // ==>  Preparing: select * from student where name = ixfosa
        // ==>  org.apache.ibatis.exceptions.PersistenceException:
        // ==>  com.ixfosa.AppTest
        // Student student = dao.selectUse$("ixfosa");

        // Student{id=1, name='ixfosa', email='ixfosa@163.com', age=23}
        // Student student = dao.selectUse$("'ixfosa'");

        // ==>  Preparing: select * from student where name = 'ixfosa' or name = 'long'
        List<Student> studentList = dao.selectUse$("'ixfosa' or name = 'long'");
        System.out.println(studentList);
    }

    @Test
    public void testSelectUse$Order() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        StudentDao dao = sqlSession.getMapper(StudentDao.class);
        List<Student> studentList = dao.selectUse$Order("id");
        for (Student student : studentList) {
            System.out.println(student);
        }
    }

}
