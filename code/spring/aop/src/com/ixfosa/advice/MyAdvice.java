package com.ixfosa.advice;

import org.aspectj.lang.ProceedingJoinPoint;

/**
 * Created by ixfosa on 2021/4/7 20:21
 */
public class MyAdvice {
    public void myBefore1(String arg0, int arg1) {
        System.out.println("前置"+ arg0 + arg1);
    }

    public void myBefore2(String arg0) {
        System.out.println("前置"+ arg0);
    }

    public void myAfter() {
        System.out.println("myAfter...");
    }

    public void myAftering() {
        System.out.println("myAftering...");
    }

    public void myThrow(){
        System.out.println("异常");
    }

    public Object myArround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println("环绕-前置");
        Object result = proceedingJoinPoint.proceed();
        System.out.println("环绕后置");
        return result;
    }
}
