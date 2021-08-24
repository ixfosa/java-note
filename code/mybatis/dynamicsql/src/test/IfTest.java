package test;

import dao.FlowerDao;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import pojo.Flower;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by ixfosa on 2021/3/27 13:58
 */
public class IfTest {
    public static void main(String[] args) throws IOException {
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(is);
        SqlSession sqlSession = factory.openSession();

        FlowerDao flowerDao = sqlSession.getMapper(FlowerDao.class);

        Flower flower1 = new Flower();
        flower1.setName("彼岸花");
        List<Flower> f1 = flowerDao.findFlower(flower1);
        System.out.println(f1);

        Flower flower2 = new Flower();
        flower2.setPrice(998);
        List<Flower> f2 = flowerDao.findFlower(flower2);
        System.out.println(f2);

        Flower flower3 = new Flower();
        flower3.setProduction("江科");
        List<Flower> f3 = flowerDao.findFlower(flower3);
        System.out.println(f3);
    }
}
