package com.test3;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;


// 自定义MethodInterceptor
public class Cinema implements MethodInterceptor {

    private RealMovie realMovie = new RealMovie();


    private void ad(boolean isStart) {
        if (isStart) {
            System.out.println("南孚电池，一节更比一节强！！！");
        } else {
            System.out.println("鄂尔多斯羊绒衫温暖全世界——鄂尔多斯羊绒衫!!!");
        }
    }

    // o            表示增强的对象，即实现这个接口类的一个对象；
    // method       表示要被拦截的方法；
    // objects      表示要被拦截方法的参数；
    // methodProxy  表示要触发父类的方法对象；

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        Object object;
        if (method.getName() == "playHaiMianBaoBao") {
            ad(true);
            object = methodProxy.invoke(realMovie, objects);
            ad(false);
        } else {
            object = methodProxy.invoke(realMovie, objects);
        }
        return object;
    }
}
