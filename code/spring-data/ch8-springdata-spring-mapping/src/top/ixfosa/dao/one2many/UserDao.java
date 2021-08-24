package top.ixfosa.dao.one2many;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import top.ixfosa.pojo.one2many.User;
/**
 * Created by ixfosa on 2021/5/27 17:02
 */
public interface UserDao extends JpaRepository<User, Integer> {
}
