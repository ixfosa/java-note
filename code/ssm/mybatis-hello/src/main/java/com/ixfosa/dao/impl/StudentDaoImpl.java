package com.ixfosa.dao.impl;

import com.ixfosa.dao.StudentDao;
import com.ixfosa.pojo.Student;
import com.ixfosa.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

/**
 * Created by ixfosa on 2021/4/22 14:48
 */
public class StudentDaoImpl implements StudentDao {
    @Override
    public List<Student> selectStudents() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        List<Student> studentList = sqlSession.selectList("stu.selectStudents");
        sqlSession.close();
        return studentList;
    }

    @Override
    public int insertStudent(Student student) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        int i = sqlSession.insert("stu.insertStudent", student);
        sqlSession.commit();
        sqlSession.close();
        return i;
    }
}
