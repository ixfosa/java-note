package com.ixfosa.advice;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * Created by ixfosa on 2021/4/7 19:38
 */

// 需要实现接口，确定哪个通知，及告诉Spring应该执行哪个方法
public class MyThrowAdvice {
    public void myException(Exception e) {
        System.out.println("执行异常通知" + e.getMessage());
    }
}
