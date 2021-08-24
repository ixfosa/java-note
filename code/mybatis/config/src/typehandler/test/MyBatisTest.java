package typehandler.test;


import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import typehandler.mapper.UserMapper;
import typehandler.pojo.User;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by ixfosa on 2021/3/24 16:35
 */
public class MyBatisTest {

    public static void main(String[] args) throws IOException {
        InputStream is = Resources.getResourceAsStream("mybatis.xml");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(is);

        SqlSession sqlSession = factory.openSession();

        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        // EnumTypeHandler
        // User user = mapper.getUser(1L);

        // User user = mapper.getUser2(1L);
        // System.out.println(user.getSex().getName()); // 男

        User user = mapper.getUser3(1L);
        System.out.println(user);
    }
}
