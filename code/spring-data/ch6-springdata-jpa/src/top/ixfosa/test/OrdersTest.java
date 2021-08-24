package top.ixfosa.test;

import org.junit.Test;
import top.ixfosa.pojo.User;
import top.ixfosa.util.JPAUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by ixfosa on 2021/5/25 16:13
 */
public class OrdersTest {
    @Test
    public void testOrders() {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        String jpql = "from User order by id desc";
        TypedQuery<User> query = em.createQuery(jpql, User.class);
        List<User> resultList = query.getResultList();
        for (User user : resultList) {
            System.out.println(user);
        }
        tx.commit();
        em.close();
    }
}
