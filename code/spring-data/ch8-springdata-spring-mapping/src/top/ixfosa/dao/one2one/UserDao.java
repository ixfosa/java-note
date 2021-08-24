package top.ixfosa.dao.one2one;

import org.springframework.data.jpa.repository.JpaRepository;
import top.ixfosa.pojo.one2one.User;

/**
 * Created by ixfosa on 2021/5/27 17:02
 */
public interface UserDao extends JpaRepository<User, Integer> {
}
