package top.ixfosa.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import top.ixfosa.pojo.User;

/**
 * Created by ixfosa on 2021/5/28 11:09
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class AppTest {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    // 添加键值对
    @Test
    public void test1() {
        this.redisTemplate.opsForValue().set("key", "value");
    }

    // 获取redis中的数据
    @Test
    public void test2() {
        String key = (String) this.redisTemplate.opsForValue().get("key");
        System.out.println(key);
    }

    // 存储实体对象
    @Test
    public void testSaveObj() {
        User user = new User();
        user.setId(1);
        user.setName("大龙虾");
        user.setAge(22);

        // 更换序列化器
        this.redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
        this.redisTemplate.opsForValue().set("user", user);
    }

    // 读取实体对象
    // 要缓存的JavaBean必须实现Serializable接口，因为Spring会将对象先序列化再存入 Redis
    @Test
    public void testReadObj() {
        // 更换序列化器
        this.redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
        User user = (User) this.redisTemplate.opsForValue().get("user");
        System.out.println(user);
    }


    // 保存 以 JSON 的格式存储实体对象
    @Test
    public void testSaveJSON() {
        User user = new User();
        user.setId(1);
        user.setName("大龙虾");
        user.setAge(22);

        this.redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<>(User.class));
        this.redisTemplate.opsForValue().set("userJson", user);
    }

    // 读取 以 JSON 的格式存储实体对象
    @Test
    public void testReadJSON() {
        this.redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<>(User.class));
        User userJson = (User) this.redisTemplate.opsForValue().get("userJson");
        System.out.println(userJson);
    }


}
