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
    /**
     *  插入用户
     */
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
        user.setId(2);
        user.setName("大龙虾");
        user.setAge(22);
        // Hibernate: update user set age=?, name=? where id=?
        this.userDao.updUser(user);
    }

    @Test
    @Transactional   // 在测试类对于事务提交方式默认的是回滚。
    @Rollback(false) // 取消自动回滚
    public void testDelUser() {
        User user = new User();
        user.setId(1);
        // Hibernate: delete from user where id=?
        this.userDao.delUser(user);
    }

    @Test
    public void testSelUser() {
        // Hibernate: select user0_.id as id1_0_0_, user0_.age as age2_0_0_,
        // user0_.name as name3_0_0_ from user user0_ where user0_.id=?
        User user = this.userDao.selUserById(2);
        System.out.println(user);
    }

    @Test
    @Transactional
    public void testSelUserByName() {
        List<User> userList = this.userDao.selUserByName("大龙虾");
        for (User user : userList) {
            System.out.println(user);
        }
    }
}
