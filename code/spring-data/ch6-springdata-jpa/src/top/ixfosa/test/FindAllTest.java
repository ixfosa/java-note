package top.ixfosa.test;

import org.junit.Test;
import top.ixfosa.pojo.User;
import top.ixfosa.util.JPAUtil;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Set;

/**
 * Created by ixfosa on 2021/5/25 15:29
 */
public class FindAllTest {

    @Test
    public void testFindAll1() {
        EntityManager em = JPAUtil.getEntityManager();

        String jpql = "from User";

        TypedQuery<User> query = em.createQuery(jpql, User.class);
        List<User> resultList = query.getResultList();

        for (User user : resultList) {
            System.out.println(user);
        }
    }

    @Test
    public void testFindAll2() {
        EntityManager em = JPAUtil.getEntityManager();

        String sql = "select * from user";

        Query nativeQuery = em.createNativeQuery(sql, User.class);

        List<User> resultList = nativeQuery.getResultList();

        for (User user : resultList) {
            System.out.println(user);
        }
    }

    @Test
    public void testFindAll3() {

        EntityManager em = JPAUtil.getEntityManager();

        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<User> query = builder.createQuery(User.class);

        Root<User> root = query.from(User.class);

        Predicate predicate = builder.equal(root.get("name"), "大龙虾");

        query.where(predicate);

        TypedQuery<User> typedQuery = em.createQuery(query);

        List<User> resultList = typedQuery.getResultList();

        for (User user : resultList) {
            System.out.println(user);
        }
    }
}
