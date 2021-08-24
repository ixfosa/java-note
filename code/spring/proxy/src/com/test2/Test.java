package com.test2;

import java.lang.reflect.Proxy;

/**
 * Created by ixfosa on 2021/4/8 16:12
 */
public class Test {
    public static void main(String[] args) {
        // true
        System.out.println(RealMovie.class.getClassLoader() ==
                Cinema.class.getClassLoader());

        // 第一个参数:反射时使用的类加载器(类加载都是一样的)
        // 第二个参数:Proxy需要实现什么接口
        // 第三个参数:通过接口对象调用方法时,需要调用哪个类的invoke方法
        Cinema cinema = new Cinema();
        Movie movie =
                (Movie)Proxy.newProxyInstance(Cinema.class.getClassLoader(),
                        new Class[]{Movie.class}, cinema);

        movie.playHaiMianBaoBao();
        movie.playXiongChuMo();

        // 出现原因: 希望把接口对象转换为具体真实对象
        // Exception in thread "main" java.lang.ClassCastException: com.sun.proxy.$Proxy0
        // RealMovie realMovie = (RealMovie) movie;
        // realMovie.playHaiMianBaoBao();
        // realMovie.playXiongChuMo();
    }
}
