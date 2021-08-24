package com.ixfosa.demo3;

import org.springframework.stereotype.Repository;

/**
 * Created by ixfosa on 2021/4/23 16:06
 */
@Repository("someServiceImpl3")
public class SomeServiceImpl {
    public String doFirst(String name, Integer age) {
        System.out.println("业务方法 doFirst()......");

        return "doFirst";
    }
}
