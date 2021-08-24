package top.ixfosa;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import top.ixfosa.entity.User;
import top.ixfosa.enums.SexEnum;
import top.ixfosa.mapper.UserMapper;

@SpringBootTest
class MpApplicationTests {

    @Autowired
    private UserMapper mapper;

    @Test
    void test() {
        mapper.selectList(null).forEach(System.out::println);
    }
    /*
    @Test
    void enumsValueTest() {
        User user = new User();
        user.setId(5);
        user.setName("long");
        user.setSex(SexEnum.MAN);
        mapper.insert(user);
    }

    @Test
    void enumsValueTest2() {
        System.out.println( mapper.selectById(1).getSex().getDesc() );
    }

    @Test
    void tableFieldFillTest() {
        User user = new User();
        user.setId(2);
        user.setName("long");
        user.setAge(22);
        user.setSex("女");
        mapper.insert(user);
        // ==>  Preparing: INSERT INTO user ( id, name, age, sex, create_time, update_time ) VALUES ( ?, ?, ?, ?, ?, ? )
        // ==> Parameters: 2(Integer), long(String), 22(Integer), 女(String), 2021-08-21 21:54:49.657(Timestamp), 2021-08-21 21:54:49.657(Timestamp)
        // <==    Updates: 1
    }

    @Test
    void tableFieldFillTest2() {
        User user = mapper.selectById(2);
        user.setId(3);
        mapper.update(user, null);
        // ==>  Preparing: SELECT id,name,sex,create_time,update_time FROM user WHERE id=?
        // ==> Parameters: 2(Integer)
        // <==    Columns: id, name, sex, create_time, update_time
        // <==        Row: 2, long, 女, 2021-08-21, 2021-08-21
        // <==      Total: 1
    }

    @Test
    void tableFieldTest() {
        System.out.println( mapper.selectById(3) );
        // 没有查询 age， 忽略 desc
        // ==>  Preparing: SELECT id,name,sex,create_time,update_time FROM user WHERE id=?
        // ==> Parameters: 3(Integer)
        // <==      Total: 0
    }

    @Test
    void versionTest() {
        User user1 = mapper.selectById(1);
        user1.setName("ixfosa");
        User user2 = mapper.selectById(1);
        user2.setName("zhong");

        mapper.updateById(user1);
        mapper.updateById(user2);
    }
     */

    @Test
    void tableLogicTest() {
        mapper.deleteById(2);
        System.out.println( mapper.selectById(1) );
    }
}

