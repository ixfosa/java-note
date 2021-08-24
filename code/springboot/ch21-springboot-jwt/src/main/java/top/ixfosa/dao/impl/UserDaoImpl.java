package top.ixfosa.dao.impl;

import org.springframework.stereotype.Repository;
import top.ixfosa.dao.UserDao;
import top.ixfosa.entity.User;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ixfosa on 2021/8/13 17:24
 */
@Repository
public class UserDaoImpl implements UserDao {

    // 模拟 db 的数据
    private static Map<String, User> userMap = new HashMap<>();

    static {
        userMap.put("ixfosa",  new User("1", "ixfosa", "123"));
        userMap.put("long", new User("2", "long", "123"));
        userMap.put("zhong", new User("3", "zhong", "123"));
    }

    @Override
    public User findUserByName(User user) {
        if (userMap.containsKey(user.getName())) {
            return userMap.get(user.getName());
        }
        return null;
    }
}
