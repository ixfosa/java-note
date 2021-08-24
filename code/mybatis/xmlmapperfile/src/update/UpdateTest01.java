package update;

import bean.Flower;
import com.sun.scenario.effect.impl.prism.ps.PPSBlend_MULTIPLYPeer;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.jdbc.SQL;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by ixfosa on 2021/3/25 18:23
 */
public class UpdateTest01 {
    public static void main(String[] args) throws IOException {
        InputStream is = Resources.getResourceAsStream("mybatis.xml");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(is);
        SqlSession sqlSession = factory.openSession();

        Flower flower = new Flower();
        flower.setId(6);
        flower.setName("粉菊花");
        flower.setPrice(299.99);
        flower.setProduction("南昌");

        List<Flower> flowers1 = sqlSession.selectList("update.mapper.FlowerMapper.selAll");

        for (Flower flower1 : flowers1) {
            System.out.println(flower1);
        }

        int i = sqlSession.update("update.mapper.FlowerMapper.updateFlower", flower);
        System.out.println("i: " + i);

        List<Flower> flowers2 = sqlSession.selectList("update.mapper.FlowerMapper.selAll");

        for (Flower flower2 : flowers2) {
            System.out.println(flower2);
        }

        sqlSession.commit();
    }
}
