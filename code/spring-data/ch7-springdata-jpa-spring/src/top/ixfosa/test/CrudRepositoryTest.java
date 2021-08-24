package top.ixfosa.test;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import top.ixfosa.dao.UserDaoCrudRepository;
import top.ixfosa.pojo.User;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class CrudRepositoryTest {

    @Autowired
    private UserDaoCrudRepository userDao;

    // 添加单条数据
    @Test
    public void testSave() {
        User user = new User();
        user.setName("fo");
        user.setAge(23);
        this.userDao.save(user);
    }

    // 批量添加数据
    @Test
    public void testSave2() {
        User user1 = new User();
        user1.setName("周杰伦");
        user1.setAge(30);

        User user2 = new User();
        user2.setName("林俊杰");
        user2.setAge(30);

        List<User> users = new ArrayList<>();
        users.add(user1);
        users.add(user2);
        this.userDao.save(users);
    }

    // 根据 ID 查询单条数据
    @Test
    public void testFindOne() {
        User user = this.userDao.findOne(2);
        System.out.println(user);
    }

    // 查询全部数据
    @Test
    public void testFindAll() {
        List<User> userList = (List<User>) this.userDao.findAll();
        for (User user : userList) {
            System.out.println(user);
        }
    }

    // 删除数据
    @Test
    public void testDelete() {

        // 删除单条数据
        this.userDao.delete(10);

        // 删除多条数据
        User user1 = new User();
        user1.setId(6);
        User user2 = new User();
        user2.setId(7);
        User user3 = new User();
        user3.setId(8);
        ArrayList<User> users = new ArrayList<>();
        users.add(user1);
        users.add(user2);
        users.add(user3);
        this.userDao.delete(users);
    }

    // 更新数据 方式一
    @Test
    public void testUpdate1() {
        User user = this.userDao.findOne(13);
        user.setName("王菲");
        this.userDao.save(user);
    }

    // 更新数据 方式二
    @Test
    @Transactional
    @Rollback(false)
    public void testUpdate2() {
        User user = this.userDao.findOne(14);
        // 持久化状态的
        user.setName("李小龙");
    }
}
