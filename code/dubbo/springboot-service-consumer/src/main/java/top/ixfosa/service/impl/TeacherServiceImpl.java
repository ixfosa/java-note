package top.ixfosa.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Component;
import top.ixfosa.model.student.Student;
import top.ixfosa.service.student.StudentService;
import top.ixfosa.service.teacher.TeacherService;

import java.util.List;

/**
 * Created by ixfosa on 2021/8/26 22:39
 */
@Component
public class TeacherServiceImpl implements TeacherService {

    @Reference(url = "dubbo://localhost:20880")
    private StudentService studentService;
    @Override
    public List<Student> initStuList() {
        return studentService.findStuList();
    }
}
