package top.ixfosa.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import top.ixfosa.dao.UserDaoJpaSpecificationExecutor;
import top.ixfosa.pojo.User;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class JpaSpecificationExecutorTest {

    @Autowired
    private UserDaoJpaSpecificationExecutor userDao;

    // 单条件查询
    @Test
    public void test1() {

        /**
         * @return Predicate:定义了查询条件
         * @param Root<T> root:根对象。封装了查询条件的对象
         * @param CriteriaQuery<?> query:定义了一个基本的查询.一般不使用
         * @param CriteriaBuilder cb:创建一个查询条件
         */
        Specification<User> spec = (root, criteriaQuery, criteriaBuilder) -> {
            Predicate predicate = criteriaBuilder.equal(root.get("name"), "大龙虾");
            return predicate;
        };
        List<User> userList = this.userDao.findAll(spec);
        for (User user : userList) {
            System.out.println(user);
        }
    }

    // 多条件查询 --- 给定查询条件方式一
    @Test
    public void test2() {
        Specification<User> spec = (root, criteriaQuery, criteriaBuilder) -> {
            ArrayList<Predicate> list = new ArrayList<>();
            list.add(criteriaBuilder.like(root.get("name"), "%龙%"));
            list.add(criteriaBuilder.ge(root.get("age"), 1));
            // 此时条件之间是没有任何关系的。
            Predicate[] predicates = new Predicate[list.size()];
            return criteriaBuilder.and(list.toArray(predicates));
        };
        List<User> userList = this.userDao.findAll(spec);
        for (User user : userList) {
            System.out.println(user);
        }
    }

    // 多条件查询 --- 给定查询条件方式二
    @Test
    public void test3() {
        Specification<User> spec = (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.and(criteriaBuilder.like(root.get("name"), "%龙%"),
                criteriaBuilder.ge(root.get("age"), 1));

        List<User> userList = this.userDao.findAll(spec);
        for (User user : userList) {
            System.out.println(user);
        }
    }

    // 分页
    // 需求：查询age >= 10，并且做分页处理
    @Test
    public void test4() {
        Specification<User> spec = (root, criteriaQuery, criteriaBuilder) ->
                // criteriaBuilder.ge(root.get("age").as(Integer.class), 10);
                criteriaBuilder.ge(root.get("age"), 10);

        PageRequest pageRequest = new PageRequest(0, 2);
        Page<User> userPage = this.userDao.findAll(pageRequest);

        for (User user : userPage) {
            System.out.println(user);
        }
    }

    // 排序
    @Test
    public void test5() {
        Specification<User> spec = (root, criteriaQuery, criteriaBuilder) ->
                // criteriaBuilder.like(root.get("name").as(String.class), "%龙%");
                criteriaBuilder.like(root.get("name"), "%龙%");

        Sort sort = new Sort(Sort.Direction.DESC, "id");

        List<User> userList = this.userDao.findAll(spec, sort);

        for (User user : userList) {
            System.out.println(user);
        }
    }

    // 分页与排序
    @Test
    public void test6() {
        Specification<User> spec = (root, criteriaQuery, criteriaBuilder) ->
                // criteriaBuilder.ge(root.get("age").as(Integer.class), 10);
                criteriaBuilder.ge(root.get("age"), 10);

        Sort.Order order1 = new Sort.Order(Sort.Direction.DESC, "id");
        Sort.Order order2 = new Sort.Order(Sort.Direction.ASC, "age");
        Sort sort = new Sort(order2, order1);

        List<User> userList = this.userDao.findAll(spec, sort);

        for (User user : userList) {
            System.out.println(user);
        }
    }
}
