package com.ixfosa.dao;

import com.ixfosa.pojo.Student;

import java.util.List;

/**
 * Created by ixfosa on 2021/4/22 14:32
 */
public interface StudentDao {

    // if 使用
    List<Student> selectStudentIf(Student student);

    // where 使用
    List<Student> selectStudentWhere(Student student);


    // foreach 用法 1
    List<Student> selectForeachOne(List<Integer> idlist);

    // foreach 用法 2
    List<Student> selectForeachTwo(List<Student> stulist);

    // 使用PageHelper分页数据
    List<Student> selectAll();
}
