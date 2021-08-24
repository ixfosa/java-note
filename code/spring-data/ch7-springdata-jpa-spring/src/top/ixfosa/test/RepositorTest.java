package top.ixfosa.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import top.ixfosa.dao.UserDao;
import top.ixfosa.dao.UserDaoRepository;
import top.ixfosa.pojo.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


@RunWith(value = SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class RepositorTest {
    @PersistenceContext(name = "transactionManager")
    private EntityManager em;

    @Autowired
    private UserDaoRepository userDao;

    @Test
    public void test() {
        // org.springframework.data.jpa.repository.support.SimpleJpaRepository@30e6a763
        System.out.println(this.userDao);
        // class com.sun.proxy.$Proxy29 代理对象 是基于 JDK 的动态代理方式创建的
        System.out.println(this.userDao.getClass());

        JpaRepositoryFactory factory = new JpaRepositoryFactory(em);
        // getRepository(UsersDao.class);
        // 可以帮助我们为接口生成实现类。而这个实现类是 SimpleJpaRepository 的对象
        // 要求：该接口必须要是继承 Repository 接口
        // public class SimpleJpaRepository<T, ID extends Serializable>
        // implements JpaRepository<T, ID>, JpaSpecificationExecutor<T>
        UserDao dao = factory.getRepository(UserDao.class);
        System.out.println(dao);
        System.out.println(dao.getClass());
    }

    @Test
    public void testRepositor() {
        List<User> userList = userDao.findByNameIs("大龙虾");
        for (User user : userList) {
            System.out.println(user);
        }

        List<User> userList1 = userDao.findByNameLike("%龙%");
        for (User user : userList1) {
            System.out.println(user);
        }

        List<User> userList2 = userDao.findByNameAndAgeGreaterThan("大龙虾", 1);
        for (User user : userList2) {
            System.out.println(user);
        }
    }

    @Test
    public void testRepositorUseQueryJPQL() {
        List<User> userList = userDao.queryByNameUseJPQL("大龙虾");
        for (User user : userList) {
            System.out.println(user);
        }

       List<User> userList1 = userDao.queryByLikeNameUseJPQL("%龙%");
       for (User user : userList1) {
           System.out.println(user);
       }

       List<User> userList2 = userDao.queryByNameAndAgeUseJPQL("大龙虾", 1);
       for (User user : userList2) {
           System.out.println(user);
       }

        userDao.updateUserNameByIdUseJPQL("大麻瓜", 8);
    }


    @Test
    public void testRepositorUseQuerySQL() {
        List<User> userList = userDao.queryByNameUseSQL("大龙虾");
        for (User user : userList) {
            System.out.println(user);
        }

        List<User> userList1 = userDao.queryByLikeNameUseSQL("%龙%");
        for (User user : userList1) {
            System.out.println(user);
        }

        List<User> userList2 = userDao.queryByNameAndAgeUseSQL("大龙虾", 1);
        for (User user : userList2) {
            System.out.println(user);
        }
    }
}
