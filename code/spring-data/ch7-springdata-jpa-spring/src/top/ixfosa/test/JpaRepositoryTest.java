package top.ixfosa.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import top.ixfosa.dao.UserDaoJpaRepository;
import top.ixfosa.pojo.User;

import java.util.List;

/**
 * Created by ixfosa on 2021/5/27 13:08
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class JpaRepositoryTest {

    @Autowired
    private UserDaoJpaRepository userDao;

    @Test
    public void test() {
        List<User> userList = this.userDao.findAll();
        for (User user : userList) {
            System.out.println(user);
        }

    }
}
