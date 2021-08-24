package top.ixfosa.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import top.ixfosa.dao.UserDao;
import top.ixfosa.domain.User;
import top.ixfosa.service.UserService;

import java.util.List;

/**
 * Created by ixfosa on 2021/6/7 16:32
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;


    @Override
    @Cacheable(value = "user")
    public List<User> findUserAll() {
        return this.userDao.findAll();
    }

    @Override
    @Cacheable(value="user") //对当前查询的对象做缓存处理
    public User findUserById(Integer id) {
        return this.userDao.findById(id).get();
    }


    @Override
    @Cacheable(value = "user", key = "#pageable.pageSize")
    public Page<User> findUserByPage(Pageable pageable) {
        return this.userDao.findAll(pageable);
    }


    @Override
    @CacheEvict(value = "user", allEntries = true)
    public void saveUser(User user) {
        this.userDao.save(user);
    }
}
