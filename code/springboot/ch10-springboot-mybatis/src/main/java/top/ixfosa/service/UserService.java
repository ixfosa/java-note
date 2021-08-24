package top.ixfosa.service;

import top.ixfosa.domain.User;

import java.util.List;

/**
 * Created by ixfosa on 2021/6/1 14:19
 */
public interface UserService {

    void addUser(User user);

    void delUser(Integer id);

    void updUser(User user);

    User selById(Integer id);

    List<User> selUser();
}
