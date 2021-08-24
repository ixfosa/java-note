package top.ixfosa.dao;

import top.ixfosa.pojo.User;

import java.util.List;

/**
 * Created by ixfosa on 2021/5/24 15:33
 */
public interface UserDao {
    void insUser(User user);
    void updUser(User user);
    void delUser(User user);
    User selUserById(Integer id);

    List<User> selUserByName(String name);

}
