package top.ixfosa.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import top.ixfosa.domain.User;

/**
 * Created by ixfosa on 2021/6/7 16:30
 */
public interface UserDao extends JpaRepository<User, Integer> {
}
