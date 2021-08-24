package com.ixfosa.advice;

import org.springframework.aop.AfterReturningAdvice;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * Created by ixfosa on 2021/4/7 18:18
 */
public class MyAfterAdvice implements AfterReturningAdvice {
    @Override
    public void afterReturning(Object o, Method method, Object[] objects, Object o1) throws Throwable {
       System.out.println("o: " + o);
       System.out.println("method: " + method);
       System.out.println("objects: " + Arrays.toString(objects));
       System.out.println("o1: " + o1);

        System.out.println("AfterReturningAdvice...");
    }
}
