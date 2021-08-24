package top.ixfosa.test;

        import org.junit.Test;
        import org.junit.runner.RunWith;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.data.domain.Page;
        import org.springframework.data.domain.PageRequest;
        import org.springframework.data.domain.Sort;
        import org.springframework.test.context.ContextConfiguration;
        import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
        import top.ixfosa.dao.UserDaoPagingAndSortingRepository;
        import top.ixfosa.pojo.User;

        import java.util.List;

/**
 * Created by ixfosa on 2021/5/27 11:28
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class PagingAndSortingRepositoryTest {
    @Autowired
    private UserDaoPagingAndSortingRepository userDao;

    // 分页处理
    @Test
    public void testPaged() {
        // page:当前页的索引。注意索引都是从 0 开始的。
        int page = 0;
        // size:每页显示 3
        int size = 2;

        PageRequest pageRequest = new PageRequest(page, size);

        Page<User> userPage = this.userDao.findAll(pageRequest);
        for (User user : userPage) {
            System.out.println(user);
        }

        System.out.println("数据的总条数：" + userPage.getTotalElements());
        System.out.println("总页数：" + userPage.getTotalPages());

        List<User> userList = userPage.getContent();
        for (User user : userList) {
            System.out.println(user);
        }
    }

    // 对单列做排序处理
    @Test
    public void testOrder1() {
        // Sort:该对象封装了排序规则以及指定的排序字段(对象的属性来表示)
        // direction:排序规则
        // properties:指定做排序的属性
        Sort sort = new Sort(Sort.Direction.DESC, "age");
        List<User> users = (List<User>) this.userDao.findAll(sort);
        for (User user : users) {
            System.out.println(user);
        }
    }

    // 多列的排序处理
    @Test
    public void testOrder2() {
        Sort.Order order1 = new Sort.Order(Sort.Direction.DESC, "age");
        Sort.Order order2 = new Sort.Order(Sort.Direction.DESC, "id");
        Sort sort = new Sort(order1, order2);

        List<User> userList = (List<User>) this.userDao.findAll(sort);

        for (User user : userList) {
            System.out.println(user);
        }
    }
}
