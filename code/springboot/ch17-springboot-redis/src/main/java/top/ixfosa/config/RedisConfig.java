package top.ixfosa.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Created by ixfosa on 2021/6/7 17:44
 */
@Configuration
public class RedisConfig {

    // 1. 创建 JedisPoolConfig 对象。在该对象中完成一些链接池配置
    // @ConfigurationProperties:会将前缀相同的内容创建一个实体。
    @Bean
    @ConfigurationProperties("spring.redis.jedis.pool")
    public JedisPoolConfig getjedisPoolConfig() {
        JedisPoolConfig config = new JedisPoolConfig();
        // 最大空闲数
        // config.setMaxIdle(10);
        //最小空闲数
        // config.setMinIdle(5);
        // 最大链接数
        // config.setMaxTotal(20);

        System.out.println("默认值："+config.getMaxIdle());  // 默认值：8
        System.out.println("默认值："+config.getMinIdle());  // 默认值：0
        System.out.println("默认值："+config.getMaxTotal()); // 默认值：8

        return config;
    }

    // 2. 创建 JedisConnectionFactory：配置 redis 链接信息
    @Bean
    @ConfigurationProperties(prefix = "spring.redis")
    public JedisConnectionFactory getJedisConnectionFactory(JedisPoolConfig config) {

        System.out.println("配置完毕："+config.getMaxIdle());  // 10
        System.out.println("配置完毕："+config.getMinIdle());  // 5
        System.out.println("配置完毕："+config.getMaxTotal()); // 20

        JedisConnectionFactory factory = new JedisConnectionFactory();
        // 关联链接池的配置对象
        factory.setPoolConfig(config);

        // 配置链接 Redis 的信息
        // factory.setHostName("127.0.0.1");
        // factory.setPort(6379);
        return factory;
    }

    // 3. 创建 RedisTemplate:用于执行 Redis 操作的方法
    @Bean
    public RedisTemplate getRedisTemplate(JedisConnectionFactory factory) {
        RedisTemplate<String, Object> template = new RedisTemplate();
        template.setConnectionFactory(factory);

        // 为 key 设置序列化器
        template.setKeySerializer(new StringRedisSerializer());
        // 为 value 设置序列化器
        template.setValueSerializer(new StringRedisSerializer());

        return template;
    }
}
