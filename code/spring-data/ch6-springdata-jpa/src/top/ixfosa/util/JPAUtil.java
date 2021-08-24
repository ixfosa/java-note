package top.ixfosa.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Created by ixfosa on 2021/5/25 15:03
 */
public final class JPAUtil {
    // JPA的实体管理器工厂：相当于Hibernate的SessionFactory
    private static EntityManagerFactory factory;

    // // 使用静态代码块赋值
    static {
        // 注意：该方法参数必须和persistence.xml中persistence-unit标签name属性取值一致
        factory = Persistence.createEntityManagerFactory("myJpa");
    }

    // 使用管理器工厂生产一个管理器对象
    public static EntityManager getEntityManager() {
        return factory.createEntityManager();
    }
}
