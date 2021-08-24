package top.ixfosa.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.ixfosa.dao.UserDao;
import top.ixfosa.service.UserService;
import top.ixfosa.entity.User;


/**
 * Created by ixfosa on 2021/8/13 17:37
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User login(User user) {
        return userDao.findUserByName(user);
    }
}
