package top.ixfosa.dao.one2one;

import org.springframework.data.jpa.repository.JpaRepository;
import top.ixfosa.pojo.one2one.Role;

/**
 * Created by ixfosa on 2021/5/27 17:15
 */
public interface RoleDao extends JpaRepository<Role, Integer> {
}
