package resultmap;

import bean.Flower;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import resultmap.dao.FlowerDao;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * Created by ixfosa on 2021/3/26 10:47
 */
public class ResMapTest02 {
    public static void main(String[] args) throws IOException {
        InputStream is = Resources.getResourceAsStream("mybatis.xml");

        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(is);

        SqlSession sqlSession = factory.openSession();

        FlowerDao flowerDao = sqlSession.getMapper(FlowerDao.class);

        List<Flower> flowerResMap = flowerDao.selectAllFlowerResMap();

        for (Flower flower : flowerResMap) {
            System.out.println(flower);
        }
    }
}
