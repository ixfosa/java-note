package com.ixfosa.service;

import com.ixfosa.pojo.Student;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by ixfosa on 2021/4/23 17:06
 */
public interface StudentService {
    Student login(Student student);
    int regist(Student student);
}
