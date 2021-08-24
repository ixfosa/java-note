package dao;

import pojo.Order;
import pojo.Orders;

import java.util.List;

/**
 * Created by ixfosa on 2021/3/26 19:55
 */
public interface OrdersDao {
    public List<Orders> selectallOrdersAndProducts();
}
