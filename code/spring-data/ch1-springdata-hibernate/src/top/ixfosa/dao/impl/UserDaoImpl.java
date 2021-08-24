package top.ixfosa.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import top.ixfosa.dao.UserDao;
import top.ixfosa.pojo.User;

/**
 * Created by ixfosa on 2021/5/24 15:32
 */
@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private HibernateTemplate hibernateTemplate;


    @Override
    public void insUser(User user) {
        this.hibernateTemplate.save(user);
    }

    @Override
    public void updUser(User user) {
        this.hibernateTemplate.update(user);
    }

    @Override
    public void delUser(User user) {
        this.hibernateTemplate.delete(user);
    }

    @Override
    public User selUserById(Integer id) {
        return this.hibernateTemplate.get(User.class, id);
    }
}
