package properties;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import utils.CodeUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by ixfosa on 2021/3/21 18:28
 */
public class PropertiesTest {

    public static void main(String[] args) throws IOException {

        // Resources 对象读取了一个 jdbc.properties 配置文件
        InputStream in = Resources.getResourceAsStream("config/jdbc.properties");
        Properties props = new Properties();
        props.load(in);

        // 获取了它原来配置的用户和密码
        String username = props.getProperty("database.username");
        String password = props.getProperty("database.password");

        System.out.println(username + " " + password);

        //解密用户和密码，并在属性中重置
        props.put("database.username", username);
        props.put ("database.password", CodeUtils.deCode(password));

        InputStream inputstream = Resources.getResourceAsStream("mybatis-config.xml");

        // 使用程递的方式覆盖原有的properties属性参数
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(inputstream, props);
        System.out.println(factory);
    }
}

