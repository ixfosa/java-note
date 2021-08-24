package top.ixfosa;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import top.ixfosa.domain.User;
import top.ixfosa.service.UserService;


@SpringBootTest
public class ApplicationTests {

    @Autowired
    private UserService userService;

    @Test
    void testFindUserById() {
        User user = this.userService.findUserById(29);
        System.out.println(user);

        User user2 = this.userService.findUserById(29);
        System.out.println(user2);

        // Hibernate: select user0_.id as id1_0_0_, user0_.age as age2_0_0_,
        // user0_.name as name3_0_0_ from user user0_ where user0_.id=?
        // User{id=29, name='夏', age=100}
        // User{id=29, name='夏', age=100}
    }

    @Test
    void testFindUserByPage() {
        Pageable pageable = PageRequest.of(0, 2);

        // 第一次查询
        System.out.println(userService.findUserByPage(pageable).getContent());

        // 第二次查询
        System.out.println(userService.findUserByPage(pageable).getContent());

        // 第三次查询
        pageable = PageRequest.of(1, 2);  // 取缓存值
        Pageable pageable1 = PageRequest.of(1, 2); // 取缓存值
        System.out.println(userService.findUserByPage(pageable1).getContent());

        // Hibernate: select user0_.id as id1_0_, user0_.age as age2_0_, user0_.name as name3_0_ from user user0_ limit ?
        // Hibernate: select count(user0_.id) as col_0_0_ from user user0_
        // [User{id=29, name='夏', age=100}, User{id=31, name='ixfosa', age=121211}]
        // [User{id=29, name='夏', age=100}, User{id=31, name='ixfosa', age=121211}]
        // [User{id=29, name='夏', age=100}, User{id=31, name='ixfosa', age=121211}]
    }

    @Test
    void testFindAll() {

        //第一次查询
        System.out.println(userService.findUserAll().size());

        User user = new User();
        user.setName("ixfosa");
        userService.saveUser(user);

        // 第三次查询
        System.out.println(userService.findUserAll().size());

        // Hibernate: select user0_.id as id1_0_, user0_.age as age2_0_, user0_.name as name3_0_ from user user0_
        // 9
        // Hibernate: insert into user (age, name) values (?, ?)
        // Hibernate: select user0_.id as id1_0_, user0_.age as age2_0_, user0_.name as name3_0_ from user user0_
        // 10
    }
}
