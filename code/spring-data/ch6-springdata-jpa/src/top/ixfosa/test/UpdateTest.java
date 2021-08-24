package top.ixfosa.test;

import org.junit.Test;
import top.ixfosa.pojo.User;
import top.ixfosa.util.JPAUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

/**
 * Created by ixfosa on 2021/5/25 15:11
 */
public class UpdateTest {

    @Test
    public void testUpdate() {

        // 1.通过工具类获取entityManager
        EntityManager manager = JPAUtil.getEntityManager();

        // 2.开启事务
        EntityTransaction transaction = manager.getTransaction();
        transaction.begin();

        // 3.更新操作
        // 3.1查询客户
        User user = manager.find(User.class, 5);
        // 3.2更新客户
        user.setName("黑菊花");
        manager.merge(user);

        // 4.提交事务
        transaction.commit();

        // 5.释放资源
        manager.close();
    }
}
