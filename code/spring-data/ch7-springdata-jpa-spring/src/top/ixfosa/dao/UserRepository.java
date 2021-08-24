package top.ixfosa.dao;

import top.ixfosa.pojo.User;

/**
 * Created by ixfosa on 2021/5/27 16:55
 */
public interface UserRepository {
    User findUserById(Integer id);
}
