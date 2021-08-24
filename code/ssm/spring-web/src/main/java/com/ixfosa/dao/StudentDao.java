package com.ixfosa.dao;

import com.ixfosa.pojo.Student;


/**
 * Created by ixfosa on 2021/4/23 17:00
 */
public interface StudentDao {
    Student selectStudentByNameAndPassword(Student student);
    int insertStudent(Student student);
}
