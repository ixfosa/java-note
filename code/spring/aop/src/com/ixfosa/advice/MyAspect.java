package com.ixfosa.advice;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;

// 创建切面类
@Component
@Aspect
public class MyAspect {

    @Pointcut("execution(* com.ixfosa.demo.Demo.demo())")
    private void mypoint(){}

    @Pointcut("execution(* com.ixfosa.demo.Demo.demo1(String, int)) && args(arg0, arg1)")
    private void mypoint1(String arg0, int arg1){}

    @Pointcut("execution(* com.ixfosa.demo.Demo.demo2(String)) && args(arg0)")
    private void mypoint2(String arg0){}

    @Before(value = "mypoint1(arg0, arg1)")
    public void myBefore1(JoinPoint joinPoint,String arg0, int arg1) {
        System.out.println("joinPoint: " + Arrays.toString(joinPoint.getArgs()));
        System.out.println("前置: "+ arg0 + arg1);
    }

    @Before("mypoint2(arg0)")
    public void myBefore2(String arg0) {
        System.out.println("前置: "+ arg0);
    }

    @After("mypoint()")
    public void myAfter() {
        System.out.println("myAfter...");
    }

    @AfterReturning("mypoint()")
    public void myAftering() {
        System.out.println("myAftering...");
    }

    @AfterThrowing(value = "mypoint()")
    public void myThrow(){
        System.out.println("异常");
    }

    @Around("mypoint()")
    public Object myArround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println("环绕-前置");
        Object result = proceedingJoinPoint.proceed();
        System.out.println("环绕后置");
        return result;
    }
}
