package com.ixfosa.service.impl;

import com.ixfosa.service.SomeService;

/**
 * Created by ixfosa on 2021/4/23 14:21
 */
public class SomeServiceImpl implements SomeService {
    @Override
    public void doSome() {
        System.out.println("doSome...");
    }

    @Override
    public void doOther() {
        System.out.println("doOther...");
    }
}
