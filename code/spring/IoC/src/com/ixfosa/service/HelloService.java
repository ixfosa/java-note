package com.ixfosa.service;

import com.ixfosa.dao.HelloDao;

public class HelloService {

    private HelloDao helloDao;


    public void setHelloDao(HelloDao helloDao) {
        this.helloDao = helloDao;
    }

    public void say() {
        helloDao.sayHello();
    }
}
