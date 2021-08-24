package com.ixfosa;


import com.ixfosa.demo5.SomeServiceImpl;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Unit test for simple App.
 */
public class AppTest5 {
    @Test
    public void testAfter() {
        ApplicationContext ac =
                new ClassPathXmlApplicationContext("applicationContext.xml");
        SomeServiceImpl service = ac.getBean("someServiceImpl5", SomeServiceImpl.class);
        service.doThird();
    }
}
