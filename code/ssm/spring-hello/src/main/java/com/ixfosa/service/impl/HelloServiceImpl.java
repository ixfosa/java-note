package com.ixfosa.service.impl;

import com.ixfosa.service.HelloService;

/**
 * Created by ixfosa on 2021/4/22 19:58
 */
public class HelloServiceImpl implements HelloService {
    @Override
    public void sayHello() {
        System.out.println("hello Spring...");
    }
}
