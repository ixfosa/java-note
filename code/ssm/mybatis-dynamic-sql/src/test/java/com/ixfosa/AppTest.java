package com.ixfosa;

import static org.junit.Assert.assertTrue;

import com.github.pagehelper.PageHelper;
import com.ixfosa.dao.StudentDao;
import com.ixfosa.pojo.Student;
import com.ixfosa.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Unit test for simple App.
 */
public class AppTest {
    @Test
    public void testSelectStudentIf() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        StudentDao dao = sqlSession.getMapper(StudentDao.class);

        Student student = new Student();
        student.setName("ixfosa");
        student.setAge(18);
        List<Student> studentList = dao.selectStudentIf(student);
        System.out.println(studentList);
        sqlSession.close();
    }

    @Test
    public void testSelectForeachOne() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        StudentDao dao = sqlSession.getMapper(StudentDao.class);
        ArrayList<Integer> ids = new ArrayList<>();
        ids.add(1);
        ids.add(2);
        ids.add(3);
        ids.add(4);
        List<Student> studentList = dao.selectForeachOne(ids);

        for (Student student : studentList) {
            System.out.println(student);
        }
        sqlSession.close();
    }


    @Test
    public void testSelectForeachTwo() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        StudentDao dao = sqlSession.getMapper(StudentDao.class);

        ArrayList<Student> stuList = new ArrayList<>();
        Student stu = new Student();
        stu.setId(1);
        stuList.add(stu);
        stu = new Student();
        stu.setId(2);
        stuList.add(stu);
        // Student sut3 = new Student();
        // sut1.setId(3);
        // Student sut4 = new Student();
        // sut1.setId(4);
        // stuList.add(sut1);
        // stuList.add(sut2);
        // stuList.add(sut3);
        // stuList.add(sut4);

        List<Student> studentList = dao.selectForeachTwo(stuList);
        for (Student student : studentList) {
            System.out.println(student);
        }

        sqlSession.close();
    }


    @Test
    public void testSelectAllPageHelper() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        StudentDao dao = sqlSession.getMapper(StudentDao.class);

        // 加入PageHelper的方法，分页
        // pageNum: 第几页， 从1开始
        // pageSize: 一页中有多少行数据
        PageHelper.startPage(2, 2);
        List<Student> studentList = dao.selectAll();
        for (Student student : studentList) {
            System.out.println(student);
        }
        sqlSession.close();
    }
}
