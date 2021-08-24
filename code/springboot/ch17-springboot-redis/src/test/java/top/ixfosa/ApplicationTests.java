package top.ixfosa;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import top.ixfosa.domain.User;

@SpringBootTest
class ApplicationTests {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Test
    void testSet() {
        redisTemplate.opsForValue().set("k", "v");
    }

    @Test
    void testGet() {
        String k = (String) redisTemplate.opsForValue().get("k");
        System.out.println(k);
    }

    @Test
    void testSetObj() {
        User user = new User();
        user.setId(1);
        user.setName("佛");
        user.setAge(23);
        redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
        redisTemplate.opsForValue().set("obj", user);
    }

    @Test
    void testGetObj() {
        redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
        User User = (User) redisTemplate.opsForValue().get("obj");
        System.out.println(User);
    }

    @Test
    void testSetJSON() {
        User user = new User();
        user.setId(1);
        user.setName("龙");
        user.setAge(22);

        redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<>(User.class));

        redisTemplate.opsForValue().set("json", user);
    }

    @Test
    void testGetJSON() {
        redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<>(User.class));
        User User = (User) redisTemplate.opsForValue().get("json");
        System.out.println(User);
    }
}
