package com.ixfosa.demo5;

import org.springframework.stereotype.Repository;

/**
 * Created by ixfosa on 2021/4/23 16:21
 */
@Repository("someServiceImpl5")
public class SomeServiceImpl {
    public void doThird() {
        System.out.println("doThird()..." + (1 / 0));
    }
}
