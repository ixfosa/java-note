package top.ixfosa.dao;

import org.springframework.data.repository.CrudRepository;
import top.ixfosa.pojo.User;

/**
 * Created by ixfosa on 2021/5/27 16:53
 */
public interface UserDaoCrudRepository extends CrudRepository<User, Integer> {
}
