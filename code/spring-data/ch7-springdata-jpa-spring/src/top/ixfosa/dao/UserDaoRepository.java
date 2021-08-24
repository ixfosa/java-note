package top.ixfosa.dao;

import org.springframework.data.repository.Repository;
import top.ixfosa.pojo.User;

import java.util.List;

/**
 * Created by ixfosa on 2021/5/27 16:55
 */
public interface UserDaoRepository extends Repository<User, Integer> {
    List<User> findByNameIs(String name);

    List<User> findByNameLike(String name);

    List<User> findByNameAndAgeGreaterThan(String name, int age);

    List<User> queryByNameUseJPQL(String name);

    List<User> queryByLikeNameUseJPQL(String name);

    List<User> queryByNameAndAgeUseJPQL(String name, int i);

    void updateUserNameByIdUseJPQL(String name, int i);

    List<User> queryByNameUseSQL(String name);

    List<User> queryByLikeNameUseSQL(String name);

    List<User> queryByNameAndAgeUseSQL(String name, int age);
}
