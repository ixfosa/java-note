package top.ixfosa.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import top.ixfosa.dao.one2many.RoleDao;
import top.ixfosa.dao.one2many.UserDao;
import top.ixfosa.pojo.one2many.Role;
import top.ixfosa.pojo.one2many.User;

import javax.persistence.criteria.*;

/**
 * Created by ixfosa on 2021/5/27 16:41
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class Ono2ManyTest {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Test
    public void test1() {
        Role role = new Role();
        role.setName("瓜皮");

        User user = new User();
        user.setName("林逸");
        user.setAge(18);

        User user2 = new User();
        user2.setName("haha");
        user2.setAge(18);

        role.getUsers().add(user);
        role.getUsers().add(user2);

        user.setRole(role);
        user2.setRole(role);

        // this.userDao.save(user);
        this.roleDao.save(role);
    }

    @Test
    public void test2() {
        User user = this.userDao.findOne(22);
        System.out.println(user);
    }

    @Test
    public void test3() {
        Role role = this.roleDao.findOne(8);
        System.out.println(role.getUsers());
    }
}
