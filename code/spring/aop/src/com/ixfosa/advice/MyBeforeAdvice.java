package com.ixfosa.advice;

import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * Created by ixfosa on 2021/4/7 17:36
 */
public class MyBeforeAdvice implements MethodBeforeAdvice {
    @Override
    public void before(Method method, Object[] objects, Object o) throws Throwable {
        System.out.println("method：" + method);
        System.out.println("objects：" + Arrays.toString(objects));
        System.out.println("o：" + o);
        System.out.println("MethodBeforeAdvice...");
    }
}
