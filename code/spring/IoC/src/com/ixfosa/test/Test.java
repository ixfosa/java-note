package com.ixfosa.test;

import com.ixfosa.service.HelloService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {

    private HelloService service;


    public void setService(HelloService service) {
        this.service = service;
    }

    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("applicationContext2.xml");

        Test test = applicationContext.getBean("test", Test.class);
        test.service.say();
    }
}
