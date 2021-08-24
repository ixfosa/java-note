package top.ixfosa.dao;

import org.springframework.stereotype.Repository;
import top.ixfosa.domain.User;

import java.util.List;

/**
 * Created by ixfosa on 2021/6/1 14:08
 */
@Repository
public interface UserDao {

    void add(User user);

    void del(Integer id);

    void upd(User user);

    User selById(Integer id);

    List<User> sel();
}
