package top.ixfosa.test;

import org.junit.Test;
import top.ixfosa.pojo.User;
import top.ixfosa.util.JPAUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by ixfosa on 2021/5/25 16:17
 */
public class CountTest {
    @Test
    public void testCount() {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        String jpql = "select count(id) from User";

        Query query = em.createQuery(jpql);

        Object result = query.getSingleResult();

        System.out.println(result);

        tx.commit();
        em.close();
    }
}
