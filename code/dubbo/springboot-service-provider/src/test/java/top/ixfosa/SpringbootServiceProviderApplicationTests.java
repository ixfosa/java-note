package top.ixfosa;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import top.ixfosa.model.student.Student;
import top.ixfosa.service.student.StudentService;

import java.util.List;

@SpringBootTest
class SpringbootServiceProviderApplicationTests {

    @Autowired
    StudentService studentService;

    @Test
    void contextLoads() {
        studentService.findStuList().forEach(System.out::println);
    }

}
