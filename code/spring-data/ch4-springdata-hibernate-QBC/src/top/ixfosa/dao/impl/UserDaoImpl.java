package top.ixfosa.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import top.ixfosa.dao.UserDao;
import top.ixfosa.pojo.User;

import java.util.List;

/**
 * Created by ixfosa on 2021/5/24 15:32
 */
@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private HibernateTemplate hibernateTemplate;


    @Override
    public List<User> selUserByNameUseQBC(String name) {
        Session session = this.hibernateTemplate.getSessionFactory().getCurrentSession();

        Criteria criteria = session.createCriteria(User.class);
        criteria.add(Restrictions.eq("name", name));

        return criteria.list();
    }
}
