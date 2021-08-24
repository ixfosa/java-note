package com.test1;

/**
 * Created by ixfosa on 2021/4/8 16:12
 */
public class Test {
    public static void main(String[] args) {
        RealMovie realMovie = new RealMovie();
        Cinema cinema = new Cinema(realMovie);
        cinema.play();
    }
}
