package top.ixfosa.test;

import org.junit.Test;
import top.ixfosa.pojo.User;
import top.ixfosa.util.JPAUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by ixfosa on 2021/5/25 15:59
 */
public class ConditionTest {

    @Test
    public void testCondition() {

        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        String jpql = "from User where name = :name";
        // String jpql = "from User where name like ? ";

        TypedQuery<User> query = em.createQuery(jpql, User.class);
        query.setParameter("name", "%龙%");

        // Query query = em.createQuery(jpql);
        // query.setParameter(0, "%龙%");

        List<User> resultList = query.getResultList();

        for (User user : resultList) {
            System.out.println(user);
        }

        tx.commit();
        em.close();
    }
}
