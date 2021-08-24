package com.ixfosa;

import static org.junit.Assert.assertTrue;

import com.ixfosa.handler.MyIncationHandler;
import com.ixfosa.service.SomeService;
import com.ixfosa.service.impl.SomeServiceImpl;
import org.junit.Test;

import java.lang.reflect.Proxy;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {

        // 使用jdk的Proxy创建代理对象
        // 创建目标对象
        SomeService target = new SomeServiceImpl();

        // 创建InvocationHandler对象
        MyIncationHandler handler = new MyIncationHandler(target);
        SomeService proxy = (SomeService) Proxy.newProxyInstance(
                target.getClass().getClassLoader(),
                target.getClass().getInterfaces(), handler);
        // proxy======com.sun.proxy.$Proxy0
        System.out.println("proxy======"+proxy.getClass().getName());

        // 通过代理执行方法，会调用handler中的invoke（）
        proxy.doSome();

        proxy.doOther();
    }
}
