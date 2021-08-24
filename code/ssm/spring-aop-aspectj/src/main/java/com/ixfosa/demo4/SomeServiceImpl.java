package com.ixfosa.demo4;

import org.springframework.stereotype.Repository;

/**
 * Created by ixfosa on 2021/4/23 16:15
 */

@Repository("someServiceImpl4")
public class SomeServiceImpl {
    public void doSecond() {
        System.out.println("doSecond()..." + (1 / 0));
    }
}
