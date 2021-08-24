package top.ixfosa.test;

        import org.junit.Test;
        import top.ixfosa.pojo.User;
        import top.ixfosa.util.JPAUtil;

        import javax.persistence.EntityManager;
        import javax.persistence.EntityTransaction;

/**
 * Created by ixfosa on 2021/5/25 15:11
 */
public class FindTest {

    @Test
    public void testFind() {

        // 1.通过工具类获取entityManager
        EntityManager manager = JPAUtil.getEntityManager();


        // 2.查找操作
        User user = manager.find(User.class, 2);
        System.out.println(user);

        // 3.释放资源
        manager.close();
    }
}
