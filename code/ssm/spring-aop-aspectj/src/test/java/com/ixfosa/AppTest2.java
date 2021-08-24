package com.ixfosa;


import com.ixfosa.demo2.SomeServiceImpl;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Unit test for simple App.
 */
public class AppTest2 {
    @Test
    public void testAfterReturning() {
        ApplicationContext ac =
                new ClassPathXmlApplicationContext("applicationContext.xml");
        SomeServiceImpl service = ac.getBean("someServiceImpl2", SomeServiceImpl.class);
        String s = service.doOther("ixfosa", 23);
        System.out.println(s);
    }
}
