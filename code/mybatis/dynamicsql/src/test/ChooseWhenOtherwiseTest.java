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
public class ChooseWhenOtherwiseTest {
    public static void main(String[] args) throws IOException {
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(is);
        SqlSession sqlSession = factory.openSession();

        FlowerDao flowerDao = sqlSession.getMapper(FlowerDao.class);

        Flower flower = new Flower();
        flower.setId(2);
        flower.setName("菊花");
        flower.setPrice(99.99);
        flower.setProduction("夏");

        List<Flower> flowerByChoose = flowerDao.findFlowerByChoose(flower);
        System.out.println(flowerByChoose);
    }
}
