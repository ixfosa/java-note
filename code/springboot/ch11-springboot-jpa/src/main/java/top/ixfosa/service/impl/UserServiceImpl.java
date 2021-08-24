package top.ixfosa.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.ixfosa.dao.UserDao;
import top.ixfosa.domain.User;
import top.ixfosa.service.UserService;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public void addUser(User user) {
        userDao.save(user);
    }

    @Override
    public void delUser(Integer id) {
        userDao.deleteById(id);
    }

    @Override
    public void updUser(User user) {
        userDao.save(user);
    }

    @Override
    public User selById(Integer id) {
        return userDao.findById(id).get();
    }

    @Override
    public List<User> selUser() {
        return userDao.findAll();
    }
}
