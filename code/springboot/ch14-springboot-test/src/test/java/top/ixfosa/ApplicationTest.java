package top.ixfosa;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import top.ixfosa.service.UserServiceImpl;

/**
 * @RunWith:启动器
 * SpringJUnit4ClassRunner.class：让 junit 与 spring 环境进行整合
 *
 * @SpringBootTest(classes={App.class}) 1,当前类为 springBoot 的测试类
 *                                      2,加载 SpringBoot 启动类。启动springBoot
 *
 * junit 与 spring 整合
 * @Contextconfiguartion("classpath:applicationContext.xml")
 * @RunWith(SpringJUnit4ClassRunner.class)
 * @SpringBootTest(classes={App.class})
 */
@SpringBootTest
class ApplicationTest {

    @Autowired
    private UserServiceImpl service;
    @Test
    void testAddUser() {
        this.service.addUser();
    }
}
