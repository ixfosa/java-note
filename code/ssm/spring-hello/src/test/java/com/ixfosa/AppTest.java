package com.ixfosa;

import static org.junit.Assert.assertTrue;

import com.ixfosa.service.HelloService;
import com.ixfosa.service.impl.HelloServiceImpl;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Date;
import java.util.UUID;

/**
 * Unit test for simple App.
 */
public class AppTest {

    // 自己手动创建 对象
    @Test
    public void test01() {
        HelloService helloService = new HelloServiceImpl();
        helloService.sayHello();
    }


    /**
     * spring默认创建对象的时间：在创建spring的容器时，会创建配置文件中的所有的对象。
     * spring创建对象：默认调用的是无参数构造方法
     */
    @Test
   public void test02() {
        // 1.指定spring配置文件的名称
       String config = "beans.xml";

        // 2.创建表示spring容器的对象， ApplicationContext
        // ApplicationContext就是表示Spring容器，通过容器获取对象了
        // ClassPathXmlApplicationContext:表示从类路径中加载spring的配置文件
       ClassPathXmlApplicationContext applicationContext =
               new ClassPathXmlApplicationContext(config);

        // 从容器中获取某个对象， 你要调用对象的方法
        // getBean("配置文件中的bean的id值")
       HelloServiceImpl helloService =
               applicationContext.getBean("helloService", HelloServiceImpl.class);
       helloService.sayHello();
   }

    /**
     * 获取spring容器中 java 对象的信息
     */
    @Test
    public void test03() {
        ClassPathXmlApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("beans.xml");

        // 使用spring提供的方法， 获取容器中定义的对象的数量
        String[] names = applicationContext.getBeanDefinitionNames();
        for (String name : names) {
            System.out.println(name);
        }
    }

    // 获取一个非自定义的类对象
    @Test
    public void test04() {
        ClassPathXmlApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("beans.xml");

        Date date = applicationContext.getBean("date", Date.class);

        System.out.println(date.getTime());
    }
}
