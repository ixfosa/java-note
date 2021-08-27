package top.ixfosa.service.impl;

import top.ixfosa.service.HelloService;

/**
 * Created by ixfosa on 2021/8/25 17:09
 */
public class HelloServiceImpl implements HelloService {
    @Override
    public void hello() {
        System.out.println("Hello Dubbo...");
    }
}
