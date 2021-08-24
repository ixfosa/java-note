package com.test3;

import net.sf.cglib.proxy.Enhancer;

import java.lang.reflect.Proxy;

/**
 * Created by ixfosa on 2021/4/8 16:12
 */
public class Test {
    public static void main(String[] args) {
        // 通过CGLIB动态代理获取代理对象的过程
        Enhancer enhancer = new Enhancer();
        // 设置enhancer对象的父类
        enhancer.setSuperclass(RealMovie.class);
        // 设置enhancer的回调对象
        enhancer.setCallback(new Cinema());
        // 创建代理对象
        RealMovie realMovie = (RealMovie) enhancer.create();
        // 通过代理对象调用目标方法
        realMovie.playHaiMianBaoBao();

        realMovie.playXiongChuMo();
    }
}
