package select;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import bean.Flower;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

/**
 * Created by ixfosa on 2021/3/25 16:23
 */
public class SelTest02 {
    public static void main(String[] args) throws IOException {
        InputStream is = Resources.getResourceAsStream("mybatis.xml");

        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(is);

        SqlSession sqlSession = factory.openSession();

        HashMap<String, Object> map = new HashMap<>();
        map.put("production", "夏");
        map.put("price", 99.66);

        List<Flower> flowers = sqlSession.selectList("select.mapper.FlowerMapper.selFlowerByProductionAndPrice", map);

        for (Flower flower : flowers) {
            System.out.println(flower);
        }
    }
}
