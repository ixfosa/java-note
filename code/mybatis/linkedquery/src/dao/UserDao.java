package dao;

import pojo.SelectUserOrdersById;
import pojo.User;

import java.util.List;

/**
 * Created by ixfosa on 2021/3/26 19:56
 */
public interface UserDao {
    User selectUserOrdersById1(Integer id);
    User selectUserOrdersById2(Integer id);
    List<SelectUserOrdersById> selectUserOrdersById3(Integer id);
}
