package test;

import dao.UserDao;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import pojo.SelectUserOrdersById;
import pojo.User;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by ixfosa on 2021/3/26 19:54
 */
public class One2ManySelectTest {
    public static void main(String[] args) throws IOException {
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(is);
        SqlSession sqlSession = factory.openSession();

        UserDao userDao = sqlSession.getMapper(UserDao.class);
        // User user = userDao.selectUserOrdersById1(1);
        User user = userDao.selectUserOrdersById2(1);
        System.out.println(user);
        List<SelectUserOrdersById> selectUserOrdersByIds = userDao.selectUserOrdersById3(1);
        for (SelectUserOrdersById selectUserOrdersById : selectUserOrdersByIds) {
            System.out.println(selectUserOrdersById);
        }
    }
}
