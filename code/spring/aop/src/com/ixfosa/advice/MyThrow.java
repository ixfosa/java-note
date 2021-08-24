package com.ixfosa.advice;

import org.springframework.aop.ThrowsAdvice;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * Created by ixfosa on 2021/4/7 18:30
 */

public class MyThrow implements ThrowsAdvice {

    /*public void afterThrowing(Exception ex) {
        System.out.println(ex.getMessage());
        System.out.println("执行异常通过-schema-base 方式 ");
    }*/

    public void afterThrowing(Method method, Object[] args, Object target, Exception ex) {
        System.out.println("method: " + method);
        System.out.println("args: " + Arrays.toString(args));
        System.out.println("target: " + target);
        System.out.println(ex.getMessage());
        System.out.println("执行异常通过-schema-base 方式 ");
    }
}
