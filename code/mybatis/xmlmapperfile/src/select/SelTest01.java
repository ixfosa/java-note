package select;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import bean.Flower;


import java.io.IOException;
import java.io.InputStream;

/**
 * Created by ixfosa on 2021/3/25 16:23
 */
public class SelTest01 {
    public static void main(String[] args) throws IOException {
        InputStream is = Resources.getResourceAsStream("mybatis.xml");

        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(is);

        SqlSession sqlSession = factory.openSession();

        Flower flower = sqlSession.selectOne("select.mapper.FlowerMapper.selFlowerById", 1);

        System.out.println(flower);
    }
}
