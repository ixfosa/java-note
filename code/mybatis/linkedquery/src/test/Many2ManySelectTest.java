package test;

import dao.OrdersDao;
import dao.UserDao;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import pojo.Orders;
import pojo.SelectUserOrdersById;
import pojo.User;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by ixfosa on 2021/3/26 19:54
 */
public class Many2ManySelectTest {
    public static void main(String[] args) throws IOException {
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(is);
        SqlSession sqlSession = factory.openSession();

        OrdersDao ordersDao = sqlSession.getMapper(OrdersDao.class);
        List<Orders> orders = ordersDao.selectallOrdersAndProducts();

        for (Orders order : orders) {
            System.out.println(order);
        }
    }
}
