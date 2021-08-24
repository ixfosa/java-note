package top.ixfosa;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import top.ixfosa.entity.User;
import top.ixfosa.mapper.UserMapper;
import top.ixfosa.vo.ProductVO;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


@SpringBootTest
class CrudApplicationTests {

    @Autowired
    private UserMapper mapper;

    @Test
    void saveTest() {
        User user = new User();
        user.setId(9);
        user.setName("梅");
        user.setSex("女");
        mapper.insert(user);
    }

    @Test
    void delTest1() {
        mapper.deleteById(6);
    }
    @Test
    void delTest2() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("name", "ixfosa");
        mapper.delete(wrapper);
    }
    @Test
    void delTest3() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("id", 2);
        mapper.deleteByMap(map);
    }
    @Test
    void delTest4() {
        mapper.deleteBatchIds(Arrays.asList(1, 2, 3));
    }

    @Test
    void updTest1() {
        User user = mapper.selectById(5);
        user.setSex("女");
        mapper.updateById(user);
    }

    @Test
    void updTest2() {
        User user = new User();
        user.setName("钟");
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("name", "龙");
        mapper.update(user, wrapper);
        // ==>  Preparing: UPDATE user SET name=? WHERE (name = ?)
        // ==> Parameters: 钟(String), 龙(String)
    }

    @Test
    void selectTest1() {
        // ==>  Preparing: SELECT id,name,sex FROM user WHERE id=?
        System.out.println(mapper.selectById(5));

        // ==>  Preparing: SELECT id,name,sex FROM user WHERE (id = ?)
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("id", 5);
        System.out.println(mapper.selectOne(wrapper));

        // ==>  Preparing: SELECT id,name,sex FROM user WHERE id IN ( ? , ? , ? )
        mapper.selectBatchIds(Arrays.asList(5, 6, 7)).forEach(System.out::println);
    }

    @Test
    void selectTest2() {
        // ==>  Preparing: SELECT id,name,sex FROM user WHERE (id >= ? AND id < ?)
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.ge("id", 5);
        wrapper.lt("id", 7);
        mapper.selectList(wrapper).forEach(System.out::println);

        // ==>  Preparing: SELECT id,name,sex FROM user WHERE id = ?
        // User(id=5, name=long, sex=女)
        HashMap<String, Object> map = new HashMap<>();
        map.put("id", 5);
        mapper.selectByMap(map).forEach(System.out::println);

        // ==>  Preparing: SELECT id,name,sex FROM user WHERE (id >= ? AND id < ?)
        // {sex=女, name=long, id=5}, ...
        mapper.selectMaps(wrapper).forEach(System.out::println);
    }

    @Test
    void selectTest3() {
        // ==>  Preparing: SELECT COUNT( * ) FROM user
        System.out.println(mapper.selectCount(null));

        // ==>  Preparing: SELECT id,name,sex FROM user WHERE (name = ? OR name = ?)
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("name", "芳");
        wrapper.or();
        wrapper.eq("name", "钟");
        System.out.println(mapper.selectObjs(wrapper));
    }


    @Test
    void selectTest4() {
        // ==>  Preparing: SELECT id,name,sex FROM user
        Page<User> page = new Page<>(1, 2);
        Page<User> userPage = mapper.selectPage(page, null);
        System.out.println(userPage.getRecords());
    }

    @Test
    void test() {
        // ==>  Preparing: select p.name pname, p.price, u.name uname, u.id
        // from product p, user u where p.user_id = u.id and u.id = ?
        mapper.productList(7).forEach(System.out::println);
    }
}
