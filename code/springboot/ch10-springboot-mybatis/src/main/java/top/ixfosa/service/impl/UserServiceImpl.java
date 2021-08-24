package top.ixfosa.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.ixfosa.dao.UserDao;
import top.ixfosa.domain.User;
import top.ixfosa.service.UserService;

import java.util.List;

/**
 * Created by ixfosa on 2021/6/1 14:26
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public void addUser(User user) {
        userDao.add(user);
    }

    @Override
    public void delUser(Integer id) {
        userDao.del(id);
    }

    @Override
    public void updUser(User user) {
        userDao.upd(user);
    }

    @Override
    public User selById(Integer id) {
        return userDao.selById(id);
    }

    @Override
    public List<User> selUser() {
        return userDao.sel();
    }
}
