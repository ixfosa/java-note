package top.ixfosa.test;


import org.junit.Test;
import top.ixfosa.pojo.User;
import top.ixfosa.util.JPAUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 * Created by ixfosa on 2021/5/24 15:39
 */

public class RemoveTest {

    /**
     * 测试jpa的保存
     *      案例：保存一个客户到数据库中
     *  Jpa的操作步骤
     *     1.加载配置文件创建工厂（实体管理器工厂）对象
     *     2.通过实体管理器工厂获取实体管理器
     *     3.获取事务对象，开启事务
     *     4.完成增删改查操作
     *     5.提交事务（回滚事务）
     *     6.释放资源
     */
    @Test
    public void testRemove() {
        // 1.通过工具类获取entityManager
        EntityManager manager = JPAUtil.getEntityManager();

        // 2.开启事务
        EntityTransaction transaction = manager.getTransaction();
        transaction.begin();

        // 3.删除操作
        // 3.1根据id查询客户
        User user = manager.find(User.class, 5);
        // 3.2删除客户
        manager.remove(user);

        // 4.提交事务
        transaction.commit();

        // 5.释放资源
        manager.close();
    }
}
