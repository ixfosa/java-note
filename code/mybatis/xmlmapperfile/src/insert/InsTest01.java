package insert;

import bean.Flower;
import com.sun.scenario.effect.impl.prism.ps.PPSBlend_MULTIPLYPeer;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by ixfosa on 2021/3/25 18:23
 */
public class InsTest01 {
    public static void main(String[] args) throws IOException {
        InputStream is = Resources.getResourceAsStream("mybatis.xml");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(is);
        SqlSession sqlSession = factory.openSession();

        Flower flower = new Flower();
        flower.setName("黑菊花");
        flower.setPrice(199.99);
        flower.setProduction("江科");

        int i = sqlSession.insert("insert.mapper.FlowerMapper.addFlower", flower);

        System.out.println("i: " + i);
        System.out.println(flower.getId());

        sqlSession.commit();

    }
}
