package top.ixfosa.service;

import top.ixfosa.domain.User;

import java.util.List;


public interface UserService {

    void addUser(User user);

    void delUser(Integer id);

    void updUser(User user);

    User selById(Integer id);

    List<User> selUser();
}
