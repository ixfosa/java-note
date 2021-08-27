package top.ixfosa.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import top.ixfosa.mapper.StudentMapper;
import top.ixfosa.model.student.Student;
import top.ixfosa.service.student.StudentService;

import java.util.List;

/**
 * Created by ixfosa on 2021/8/26 17:31
 */
@Service // 暴露服务
@Component
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper mapper;


    @Override
    public List<Student> findStuList() {
        return mapper.selectList(null);
    }
}
