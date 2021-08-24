package code;

import dao.FlowerDaoMapper;
import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import pojo.Flower;

import java.util.List;


/**
 * Created by ixfosa on 2021/3/21 15:28
 */
public class CodeCreateSqlSessionFactory {
    public static void main(String[] args) {

        PooledDataSource source = new PooledDataSource();
        source.setDriver("com.mysql.jdbc.Driver");
        source.setUrl("jdbc:mysql://localhost:3306/ssm?useUnicode=true&characterEncoding=utf8");
        source.setUsername("root");
        source.setPassword("ixfosa");

        // source.setDefeultAutoCommit(false);

        // 采用 MyBatis 的 JDBC 事务方式
        JdbcTransactionFactory transactionFactory = new JdbcTransactionFactory();
        Environment environment = new Environment("test", transactionFactory, source);

        // 创建 Configuration 对象
        Configuration configuration = new Configuration(environment);


        // 加入一个映射器
        configuration.addMapper(FlowerDaoMapper.class);

        //使用 SqlSessionFactoryBuilder 构建 SqlSessionFactory
        SqlSessionFactory SqlSessionFactory =  new SqlSessionFactoryBuilder().build(configuration);

        // 生产SqlSession
        SqlSession session = SqlSessionFactory.openSession();

        // FlowerDaoMapper mapper = session.getMapper(FlowerDaoMapper.class);

        // List<Flower> flowers = mapper.selAll();

        List<Flower> flowers = session.selectList("selAll");

        System.out.println(flowers);

        session.close();
    }
}
