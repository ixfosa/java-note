package resultmap;

import bean.Flower;
import bean.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import resultmap.dao.FlowerDao;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by ixfosa on 2021/3/26 10:47
 */
public class AutoMapperTest03 {
    public static void main(String[] args) throws IOException {
        InputStream is = Resources.getResourceAsStream("mybatis.xml");

        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(is);

        SqlSession sqlSession = factory.openSession();

        User user = sqlSession.selectOne("resultmap.mapper.UserMapper.selUserById", 1);

        System.out.println(user);
    }
}
