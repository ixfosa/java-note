package top.ixfosa.test;


        import org.junit.Test;
        import org.junit.runner.RunWith;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.test.context.ContextConfiguration;
        import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
        import top.ixfosa.dao.UserDao;
        import top.ixfosa.pojo.User;

/**
 * Created by ixfosa on 2021/5/24 15:39
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class UserDaoTest {

    @Autowired
    private UserDao userDao;

    @Test
    public void testSave() {
        User user = new User();
        user.setName("麻瓜");
        user.setAge(1);
        userDao.save(user);
    }

    /**
     * 修改客户：调用save(obj)方法
     *    对于save方法的解释：如果执行此方法是对象中存在id属性，
     *    即为更新操作会先根据id查询，再更新
     *    如果执行此方法中对象中不存在id属性，即为保存操作
     */
    @Test
    public void testUpdate() {
        User user = userDao.findOne(9);
        user.setName("哈哈");
        userDao.save(user);
    }

    @Test
    public void testDelete() {
        userDao.delete(9);
    }

    @Test
    public void testFindById() {
        System.out.println(this.userDao);
        User user = userDao.findOne(8);
        System.out.println(user);
    }

    @Test
    public void test() {
        System.out.println(this.userDao);
        User user = userDao.findUserById(2);
        System.out.println(user);
    }


}

