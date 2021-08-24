package com.ixfosa;


import com.ixfosa.demo4.SomeServiceImpl;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Unit test for simple App.
 */
public class AppTest4 {
    @Test
    public void testAfterThrowing() {
        ApplicationContext ac =
                new ClassPathXmlApplicationContext("applicationContext.xml");
        SomeServiceImpl service = ac.getBean("someServiceImpl4", SomeServiceImpl.class);
        service.doSecond();
        // 异常通知：方法发生异常时，执行：/ by zero
        // java.lang.ArithmeticException: / by zero
    }
}
