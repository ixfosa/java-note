package com.ixfosa.demo6;

import org.springframework.stereotype.Repository;

/**
 * Created by ixfosa on 2021/4/23 16:26
 */
@Repository("someServiceImpl6")
public class SomeServiceImpl {
    public void sayHi() {
        System.out.println("sayHi()...");
    }
}
