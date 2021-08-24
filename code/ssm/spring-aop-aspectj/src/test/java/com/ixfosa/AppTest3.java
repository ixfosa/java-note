package com.ixfosa;


import com.ixfosa.demo3.SomeServiceImpl;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Unit test for simple App.
 */
public class AppTest3 {
    @Test
    public void testAround() {
        ApplicationContext ac =
                new ClassPathXmlApplicationContext("applicationContext.xml");
        SomeServiceImpl service = ac.getBean("someServiceImpl3", SomeServiceImpl.class);
        service.doFirst("long", 23);
    }
}
