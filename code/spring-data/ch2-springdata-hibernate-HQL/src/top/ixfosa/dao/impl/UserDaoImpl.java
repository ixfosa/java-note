package top.ixfosa.dao.impl;

import org.hibernate.Session;
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

    @Override
    public List<User> selUserByName(String name) {
        // getCurrentSession:当前 session 必须要有事务边界，且只能处理唯一
        // 的一个事务。当事务提交或者回滚后 session 自动失效

        // openSession:每次都会打开一个新的 session.加入每次使用多次。则获
        // 得的是不同 session 对象。使用完毕后我们需要手动的调用 colse 方法关闭 session
        Session session = this.hibernateTemplate.getSessionFactory().getCurrentSession();
        Query query = session.createQuery("from User where name = :username");

        Query queryTemp = query.setString("username", name);
        return queryTemp.list();
    }
}
