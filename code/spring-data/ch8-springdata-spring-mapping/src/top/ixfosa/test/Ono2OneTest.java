package top.ixfosa.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import top.ixfosa.dao.one2one.RoleDao;
import top.ixfosa.dao.one2one.UserDao;
import top.ixfosa.pojo.one2one.Role;
import top.ixfosa.pojo.one2one.User;

/**
 * Created by ixfosa on 2021/5/27 16:41
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class Ono2OneTest {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    // 添加用户同时添加角色
    @Transactional
    @Rollback(false)
    @Test
    public void test1() {
        // 创建角色
        Role role = new Role();
        role.setName("admin");

        // 创建用户
        User user = new User();
        user.setName("zhong");
        user.setAge(21);

        // 建立关系
        role.setUser(user);
        user.setRole(role);

        // 保存数据
        // this.userDao.save(user);
        this.roleDao.save(role);
    }

    // 根据用户 ID 查询用户，同时查询用户角色
    @Test
    public void test2() {
        User user = this.userDao.findOne(21);
        System.out.println(user);

        Role role = this.roleDao.findOne(2);
        System.out.println(role.getUser());
    }
}
