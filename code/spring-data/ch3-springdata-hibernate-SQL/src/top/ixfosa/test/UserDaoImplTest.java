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
    @Transactional
    public void testSelUserByName() {
        List<User> userList = this.userDao.selUserByNameUseSQL("大龙虾");
        for (User user : userList) {
            System.out.println(user);
        }
    }
}
