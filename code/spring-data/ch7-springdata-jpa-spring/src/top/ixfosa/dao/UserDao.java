package top.ixfosa.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import top.ixfosa.pojo.User;

/**
 * Created by ixfosa on 2021/5/27 16:51
 */
public interface UserDao extends JpaRepository<User, Integer>, UserRepository {
    User findUserById(int id);
}
