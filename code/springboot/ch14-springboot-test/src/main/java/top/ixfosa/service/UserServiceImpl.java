package top.ixfosa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.ixfosa.dao.UserDaoImpl;

/**
 * Created by ixfosa on 2021/6/7 15:02
 */
@Service
public class UserServiceImpl {

    @Autowired
    private UserDaoImpl userDao;

    public void addUser() {
        this.userDao.save();
    }
}
