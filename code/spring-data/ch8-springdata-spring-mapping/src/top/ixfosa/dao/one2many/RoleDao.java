package top.ixfosa.dao.one2many;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import top.ixfosa.pojo.one2many.Role;

/**
 * Created by ixfosa on 2021/5/27 17:15
 */
public interface RoleDao extends JpaRepository<Role, Integer> {
}
