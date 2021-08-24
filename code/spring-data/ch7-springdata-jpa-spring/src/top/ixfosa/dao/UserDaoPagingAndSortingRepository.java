package top.ixfosa.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import top.ixfosa.pojo.User;

/**
 * Created by ixfosa on 2021/5/27 16:55
 */
public interface UserDaoPagingAndSortingRepository extends PagingAndSortingRepository<User, Integer> {
}
