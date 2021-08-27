package top.ixfosa.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.ixfosa.service.HelloService;
import top.ixfosa.service.SayService;

/**
 * Created by ixfosa on 2021/8/25 17:46
 */
@Service
public class SayServiceImpl implements SayService  {
    @Autowired
    private HelloService helloService;


    @Override
    public void say() {
        System.out.println("say...");
        helloService.hello();
    }
}
