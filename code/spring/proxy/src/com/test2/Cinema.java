package com.test2;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by ixfosa on 2021/4/8 16:04
 */
public class Cinema implements InvocationHandler {

    private RealMovie realMovie = new RealMovie();


    private void ad(boolean isStart) {
        if (isStart) {
            System.out.println("南孚电池，一节更比一节强！！！");
        } else {
            System.out.println("鄂尔多斯羊绒衫温暖全世界——鄂尔多斯羊绒衫!!!");
        }
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        ad(true);
        Object object = method.invoke(realMovie, args);
        return object;
    }
}
