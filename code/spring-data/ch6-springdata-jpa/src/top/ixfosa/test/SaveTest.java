package top.ixfosa.test;


import org.junit.Test;
import top.ixfosa.pojo.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 * Created by ixfosa on 2021/5/24 15:39
 */

public class SaveTest {

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
    public void testSave() {
        // 1.加载配置文件创建工厂（实体管理器工厂）对象
        EntityManagerFactory factory =
                Persistence.createEntityManagerFactory("myJpa");

        // 2.通过实体管理器工厂获取实体管理器
        EntityManager manager = factory.createEntityManager();

        // 3.获取事务对象，开启事务
        EntityTransaction tx = manager.getTransaction();
        tx.begin();

        User user = new User();
        // javax.persistence.PersistenceException: org.hibernate.PersistentObjectException:
        // 主键自增策略 @GeneratedValue(strategy=GenerationType.IDENTITY):
        // 1-设置主键自增时，必须在数据库中设置主键自增和在对应的实体类中设置对应的主键自增，只要有哪一边没有设置，就会报该异常
        // 2-在设置了主键自增了，保存数据时，自己设置了主键，则也会报PersistenceException
        // user.setId(5);
        user.setName("菊花");
        user.setAge(123);

        // 4.完成增删改查操作：保存一个客户到数据库中
        manager.persist(user);

        // 5.提交事务（回滚事务）
        tx.commit();

        //6.释放资源
        manager.close();
    }
}
