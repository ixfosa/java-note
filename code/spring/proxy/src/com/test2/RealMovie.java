package com.test2;

/**
 * Created by ixfosa on 2021/4/8 16:06
 */
public class RealMovie implements Movie {
    @Override
    public void playXiongChuMo() {
        System.out.println("《熊出没》");
    }

    @Override
    public void playHaiMianBaoBao() {
        System.out.println("《海绵宝宝》");
    }
}


