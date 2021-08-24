package com.ixfosa.advice;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * Created by ixfosa on 2021/4/7 19:25
 */
public class MyArround implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        System.out.println("环绕-前置");
        // 放行,调用切点方式
        Object result = methodInvocation.proceed();
        System.out.println("环绕-后置");
        return result;
    }
}
