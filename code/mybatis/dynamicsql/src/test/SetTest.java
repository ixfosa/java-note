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
public class SetTest {
    public static void main(String[] args) throws IOException {
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(is);
        SqlSession sqlSession = factory.openSession();

        FlowerDao flowerDao = sqlSession.getMapper(FlowerDao.class);

        Flower flower1 = new Flower();
        flower1.setId(3);
        flower1.setName("大菊花");
        flower1.setPrice(100);
        flower1.setProduction("M78星云");

        int i1 = flowerDao.updateFlowerBySet(flower1);
        System.out.println(i1);


        //  SQL: update flower     where id = ?
        Flower flower2 = new Flower();

        int i2 = flowerDao.updateFlowerBySet(flower2);
        System.out.println(i2);
        sqlSession.commit();
    }
}
