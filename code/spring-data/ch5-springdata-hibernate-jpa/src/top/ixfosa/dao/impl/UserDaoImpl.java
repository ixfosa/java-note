package top.ixfosa.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import top.ixfosa.dao.UserDao;
import top.ixfosa.pojo.User;

import javax.lang.model.element.VariableElement;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Created by ixfosa on 2021/5/24 15:32
 */
@Repository
public class UserDaoImpl implements UserDao {

    /**
     * PersistenceContext: JPA中的注解，，称为持久化上下文，它一般包含有当前事务范围内的
     * 被管理的实体对象(Entity)的数据。每个EntityManager，都会跟一个PersistenceContext相关联。
     * PersistenceContext中存储的是实体对象的数据，而关系数据库中存储的是记录。
     * persistencecontext中保存的对象如果做做修改和更新操作的话EntityManager都会跟踪到。
     *
     * 主要作用:就是它能够根据实体类的要求根据实体类状态的变化，能够做出不同的应对以及做持久化操作。
     */
    @PersistenceContext(name = "entityManagerFactory")
    private EntityManager entityManager;

    @Override
    public void insUser(User user) {
        this.entityManager.persist(user);
    }

    @Override
    public void updUser(User user) {
        this.entityManager.merge(user);
    }

    @Override
    public void delUser(User user) {
        User u = this.selUserById(user.getId());
        this.entityManager.remove(u);
    }

    @Override
    public User selUserById(Integer id) {
        return this.entityManager.find(User.class, id);
    }

    @Override
    public List<User> selUserByNameUseHQL(String name) {
        String hql = "from User where name = :name";
        return this.entityManager.createQuery(hql, User.class).
                setParameter("name", name)
                .getResultList();
    }

    @Override
    public List<User> selUserByNameUseSQL(String name) {
        String sql = "select * from user where name = ?";
        return this.entityManager.createNativeQuery(sql, User.class)
                .setParameter(1, name)
                .getResultList();
    }

    @Override
    public List<User> selUserByNameUseQBC(String name) {
        // CriteriaBuilder 对象：创建一个 CriteriaQuery,创建查询条件。
        CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<User> query = builder.createQuery(User.class);
        // CriteriaQuery 对象：执行查询的 Criteria 对象
        // select * from user
        Root<User> root = query.from(User.class);
        Predicate cate = builder.equal(root.get("name"), name);
        // select * from t_users where username = 大龙虾
        query.where(cate);

        // 执行查询
        TypedQuery<User> typedQuery = this.entityManager.createQuery(query);

        return typedQuery.getResultList();
    }
}
