package com.ixfosa.dao;

import com.ixfosa.pojo.Student;

import java.util.List;

/**
 * Created by ixfosa on 2021/4/23 17:00
 */
public interface StudentDao {
    List<Student> selectStudents();
    int insertStudent(Student student);
}
