package com.ixfosa.demo1;

import org.springframework.stereotype.Repository;

/**
 * Created by ixfosa on 2021/4/23 15:21
 */
@Repository("someServiceImpl1")
public class SomeServiceImpl  implements SomeService {
    @Override
    public void doSome(String name, Integer age) {
        // 给doSome方法增加一个功能，在doSome()执行之前， 输出方法的执行时间
        System.out.println("doSome()...");
    }

    public void doOther(String name, Integer age) {
        System.out.println("doOther...");
    }
}
