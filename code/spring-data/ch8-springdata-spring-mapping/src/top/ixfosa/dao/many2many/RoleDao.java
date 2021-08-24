package top.ixfosa.dao.many2many;

import org.springframework.data.jpa.repository.JpaRepository;
import top.ixfosa.pojo.many2many.Role;

/**
 * Created by ixfosa on 2021/5/27 17:15
 */
public interface RoleDao extends JpaRepository<Role, Integer> {
}
