package top.ixfosa.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.ixfosa.model.student.Student;
import top.ixfosa.service.student.StudentService;
import top.ixfosa.service.teacher.TeacherService;

import java.util.List;

/**
 * Created by ixfosa on 2021/8/26 22:44
 */
@RestController
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @RequestMapping("list")
    public List<Student> list() {
        return teacherService.initStuList();
    }
}
