package top.ixfosa.dao.many2many;

import org.springframework.data.jpa.repository.JpaRepository;
import top.ixfosa.pojo.many2many.User;

/**
 * Created by ixfosa on 2021/5/27 17:02
 */
public interface UserDao extends JpaRepository<User, Integer> {
}
