package select;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import bean.Flower;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by ixfosa on 2021/3/25 16:23
 */
public class SelTest03 {
    public static void main(String[] args) throws IOException {
        InputStream is = Resources.getResourceAsStream("mybatis.xml");

        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(is);

        SqlSession sqlSession = factory.openSession();

        Flower flower = new Flower();
        flower.setProduction("夏");
        flower.setPrice(90.0);

        List<Flower> flowers = sqlSession.selectList("select.mapper.FlowerMapper.selFlowerByBean", flower);

        for (Flower f : flowers) {
            System.out.println(f);
        }
    }
}
