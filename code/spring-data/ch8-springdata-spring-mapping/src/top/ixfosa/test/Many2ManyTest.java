package top.ixfosa.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import top.ixfosa.dao.many2many.RoleDao;
import top.ixfosa.dao.many2many.UserDao;
import top.ixfosa.pojo.many2many.Role;
import top.ixfosa.pojo.many2many.User;

/**
 * Created by ixfosa on 2021/5/27 16:41
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class Many2ManyTest {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;
    @Test
    public void test1() {
        // 创建对象
        User user1 = new User();
        user1.setName("用户1");
        Role role1 = new Role();
        role1.setName("角色1");

        User user2 = new User();
        user2.setName("用户2");


        // 建立关联关系
        user1.getRole().add(role1);
        user2.getRole().add(role1);
        role1.getUser().add(user1);
        role1.getUser().add(user2);

        // 保存,多对多放弃维护权：被动的一方放弃
        // this.roleDao.save(role);
        // this.userDao.save(user);

        // 保存一个用户的同时保存用户的关联角色
        // this.userDao.save(user1);
        // this.userDao.save(user2);
        this.roleDao.save(role1);
    }

    // 删除用户，同时删除他的关联对象
    // 在多对多的删除时，双向级联删除根本不能配置
    // 如果配了的话，如果数据之间有相互引用关系，可能会清空所有数据
    @Test
    public void test2() {
        User user = this.userDao.findOne(33);
        this.userDao.delete(user);
    }
}
