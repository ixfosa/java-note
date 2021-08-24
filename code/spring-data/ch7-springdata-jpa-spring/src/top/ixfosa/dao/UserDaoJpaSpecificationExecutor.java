package top.ixfosa.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import top.ixfosa.pojo.User;

/**
 * Created by ixfosa on 2021/5/27 16:54
 */
public interface UserDaoJpaSpecificationExecutor extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User> {
}
