package test;

import dao.FlowerDao;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import pojo.Flower;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ixfosa on 2021/3/27 13:58
 */
public class ForeachTest {
    public static void main(String[] args) throws IOException {
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(is);

        SqlSession sqlSession = factory.openSession(ExecutorType.BATCH);

        FlowerDao flowerDao = sqlSession.getMapper(FlowerDao.class);

        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);

        List<Flower> flowerByForeachs = flowerDao.findFlowerByForeach(list);

        for (Flower flowerByForeach : flowerByForeachs) {
            System.out.println(flowerByForeach);
        }

        ArrayList<Flower> flowerList = new ArrayList<>();
        flowerList.add(new Flower("菊花1", 1,"M11星云"));
        flowerList.add(new Flower("菊花2", 2,"M12星云"));
        flowerList.add(new Flower("菊花3", 3,"M13星云"));
        flowerList.add(new Flower("菊花4", 4,"M14星云"));
        flowerList.add(new Flower("菊花5", 5,"M15星云"));

        int i = flowerDao.insertFlower(flowerList);
        System.out.println(i);
        sqlSession.commit();
    }
}
