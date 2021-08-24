package com.ixfosa.dao;

import com.ixfosa.pojo.MyStudent;
import com.ixfosa.pojo.Student;
import com.ixfosa.pojo.ViewStudent;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by ixfosa on 2021/4/22 14:32
 */
public interface StudentDao {

    ViewStudent selectStudentReturnViewStudent(@Param("sid") Integer id);

    int countStudent();

    // 定义方法返回Map
    Map<Object, Object> selectMapById(Integer id);

    // 使用resultMap定义映射关系
    List<Student> selectAllStudents();

    List<MyStudent> selectMyStudent();

    List<MyStudent> selectDiffColProperty();

    // 第一种模糊查询， 在java代码指定 like的内容
    List<Student> selectLikeOne(String email);

    // name就是ixfosa， 在mapper中拼接 like  "%" o "%"
    List<Student> selectLikeTwo(String email);
}
