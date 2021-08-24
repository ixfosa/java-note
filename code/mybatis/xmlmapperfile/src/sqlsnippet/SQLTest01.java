package sqlsnippet;

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
public class SQLTest01 {
    public static void main(String[] args) throws IOException {
        InputStream is = Resources.getResourceAsStream("mybatis.xml");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(is);
        SqlSession sqlSession = factory.openSession();

        List<Flower> flowers = sqlSession.selectList("sqlsnippet.mapper.FlowerMapper.selAll");

        for (Flower flower : flowers) {
            System.out.println(flower);
        }
    }
}
