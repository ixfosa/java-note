package com.ixfosa;


import com.ixfosa.demo6.SomeServiceImpl;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Unit test for simple App.
 */
public class AppTest6 {
    @Test
    public void testMyPointCut() {
        ApplicationContext ac =
                new ClassPathXmlApplicationContext("applicationContext.xml");
        SomeServiceImpl service = ac.getBean("someServiceImpl6", SomeServiceImpl.class);
        service.sayHi();
    }
}
