package com.ixfosa.handler;

import com.ixfosa.util.ServiceTools;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by ixfosa on 2021/4/23 14:24
 */
public class MyIncationHandler implements InvocationHandler {
    // 目标对象
    private Object target;
    public MyIncationHandler(Object target) {
        this.target = target;
    }

    // 通过代理对象执行方法时，会调用执行这个invoke（）
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String methodName = method.getName();
        Object rs = null;
        if ("doSome".equals(methodName)) {
            // 在目标方法之前，输出时间
            ServiceTools.doLog();

            // 执行目标类的方法，通过Method类实现
            rs = method.invoke(target, args);

            // 在目标方法执行之后，提交事务
            ServiceTools.doTrans();
        } else {
            rs = method.invoke(target, args);
        }
        return rs;
    }
}
