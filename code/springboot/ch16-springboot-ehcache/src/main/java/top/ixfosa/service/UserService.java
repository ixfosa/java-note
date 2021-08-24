package top.ixfosa.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import top.ixfosa.domain.User;

import java.util.List;

/**
 * Created by ixfosa on 2021/6/7 16:30
 */

public interface UserService {
    List<User> findUserAll();
    User findUserById(Integer id);
    Page<User> findUserByPage(Pageable pageable);
    void saveUser(User user);
}
