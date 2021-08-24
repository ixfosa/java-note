package xml;


import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import pojo.Flower;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;


/**
 * Created by ixfosa on 2021/3/20 20:38
 */
public class XMLCreateSqlSessionFactory {

    public static void main(String[] args) throws IOException {

        String resoucrce = "mybatis-config.xml";

        InputStream is =  Resources.getResourceAsStream(resoucrce);

        //使用工厂设计模式
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(is);

        // 生产SqlSession
        SqlSession session = factory.openSession();

        List<Flower> flowers = session.selectList("pojo.Flower.selAll");

        System.out.println(flowers);

        session.close();
    }


}
