package com.ixfosa.service.impl;

import com.ixfosa.dao.StudentDao;
import com.ixfosa.pojo.Student;
import com.ixfosa.service.StudentService;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by ixfosa on 2021/4/23 17:06
 */

@Repository("studentService")
public class StudentServiceImpl implements StudentService {

    @Resource
    StudentDao studentDao;


    @Override
    public List<Student> showStu() {
        return studentDao.selectStudents();
    }

    @Override
    public int addStu(Student student) {
        return studentDao.insertStudent(student);
    }
}
