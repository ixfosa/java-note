package top.ixfosa.test;

import org.junit.Test;
import top.ixfosa.pojo.User;
import top.ixfosa.util.JPAUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by ixfosa on 2021/5/25 15:48
 */
public class PagedTest {

    @Test
    public void testPaged() {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        String jpql = "from User";
        Query query = em.createQuery(jpql, User.class);

        // 起始索引
        query.setFirstResult(1);
        // 每页查询的条数
        query.setMaxResults(1);

        List<User> resultList = query.getResultList();

        for (Object user : resultList) {
            System.out.println(user);
        }

        tx.commit();
        em.close();
    }
}
