package dao;

import pojo.Order;

/**
 * Created by ixfosa on 2021/3/26 19:55
 */
public interface OrderDao {
    Order selectOrdersById(Integer id);
}
