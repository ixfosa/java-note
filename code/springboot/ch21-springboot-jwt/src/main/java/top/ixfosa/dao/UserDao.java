package top.ixfosa.dao;

import top.ixfosa.entity.User;

/**
 * Created by ixfosa on 2021/8/13 17:22
 */
public interface UserDao {
    User findUserByName(User user);
}
