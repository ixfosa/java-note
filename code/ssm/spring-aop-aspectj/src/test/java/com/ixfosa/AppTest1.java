package com.ixfosa;


import com.ixfosa.demo1.SomeServiceImpl;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Unit test for simple App.
 */
public class AppTest1 {
    @Test
    public void testBefore() {
        ApplicationContext ac =
                new ClassPathXmlApplicationContext("applicationContext.xml");
        SomeServiceImpl service = ac.getBean("someServiceImpl1", SomeServiceImpl.class);
        service.doSome("ixfosa", 23);
    }
    // 方法的签名（定义）=void com.ixfosa.demo1.SomeServiceImpl.doSome(String,Integer)
    // 方法的名称=doSome
    // 参数=ixfosa
    // 参数=23
    // 7=====前置通知， 切面功能：在目标方法之前输出执行时间：Fri Apr 23 15:52:51 CST 2021
    // 1=====前置通知， 切面功能：在目标方法之前输出执行时间：Fri Apr 23 15:52:51 CST 2021
    // 2=====前置通知， 切面功能：在目标方法之前输出执行时间：Fri Apr 23 15:52:51 CST 2021
    // 3=====前置通知， 切面功能：在目标方法之前输出执行时间：Fri Apr 23 15:52:51 CST 2021
    // 4=====前置通知， 切面功能：在目标方法之前输出执行时间：Fri Apr 23 15:52:51 CST 2021
    // 5=====前置通知， 切面功能：在目标方法之前输出执行时间：Fri Apr 23 15:52:51 CST 2021
    // 6=====前置通知， 切面功能：在目标方法之前输出执行时间：Fri Apr 23 15:52:51 CST 2021
    // doSome()...
}
