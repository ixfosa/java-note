package top.ixfosa.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;
import top.ixfosa.model.student.Student;

/**
 * Created by ixfosa on 2021/8/26 17:30
 */
@Repository
public interface StudentMapper extends BaseMapper<Student> {
}
