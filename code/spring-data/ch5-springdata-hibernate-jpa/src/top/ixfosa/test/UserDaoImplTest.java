package top.ixfosa.test;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import top.ixfosa.dao.UserDao;
import top.ixfosa.pojo.User;

import java.util.List;

/**
 * Created by ixfosa on 2021/5/24 15:39
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class UserDaoImplTest {

    @Autowired
    private UserDao userDao;


    @Test
    @Transactional   // 在测试类对于事务提交方式默认的是回滚。
    @Rollback(false) // 取消自动回滚
    public void testInsUser() {
        User user = new User();
        user.setName("奥特曼");
        user.setAge(99);
        // Hibernate: insert into user (age, name) values (?, ?)
        this.userDao.insUser(user);
    }

    @Test
    @Transactional   // 在测试类对于事务提交方式默认的是回滚。
    @Rollback(false) // 取消自动回滚
    public void testUpdUser() {
        User user = new User();
        user.setId(4);
        user.setName("凹凸曼");
        user.setAge(123);
        this.userDao.updUser(user);
    }

    @Test
    @Transactional   // 在测试类对于事务提交方式默认的是回滚。
    @Rollback(false) // 取消自动回滚
    public void testDelUser() {
        User user = new User();
        user.setId(4);
        this.userDao.delUser(user);
    }

    @Test
    public void testSelUser() {
        User user = this.userDao.selUserById(2);
        System.out.println(user);
    }

    @Test
    @Transactional
    public void testSelUserByNameUserHQL() {
        List<User> userList = this.userDao.selUserByNameUseHQL("大龙虾");
        for (User user : userList) {
            System.out.println(user);
        }
    }

    @Test
    @Transactional
    public void testSelUserByNameUseSQL() {
        List<User> userList = this.userDao.selUserByNameUseSQL("大龙虾");
        for (User user : userList) {
            System.out.println(user);
        }
    }

    @Test
    @Transactional
    public void testSelUserByNameUseQBC() {
        List<User> userList = this.userDao.selUserByNameUseQBC("大龙虾");
        for (User user : userList) {
            System.out.println(user);
        }
    }
}

