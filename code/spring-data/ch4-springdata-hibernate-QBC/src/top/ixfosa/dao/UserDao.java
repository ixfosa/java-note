package top.ixfosa.dao;

import top.ixfosa.pojo.User;

import java.util.List;

/**
 * Created by ixfosa on 2021/5/24 15:33
 */
public interface UserDao {
    List<User> selUserByNameUseQBC(String name);
}
