package com.test1;

/**
 * Created by ixfosa on 2021/4/8 16:04
 */
public class Cinema implements Movie {

    private RealMovie realMovie;

    public Cinema(RealMovie realMovie) {
        this.realMovie = realMovie;
    }

    @Override
    public void play() {
        ad(true);
        realMovie.play();
        ad(false);
    }

    private void ad(boolean isStart) {
        if (isStart) {
            System.out.println("南孚电池，一节更比一节强！！！");
        } else {
            System.out.println("鄂尔多斯羊绒衫温暖全世界——鄂尔多斯羊绒衫!!!");
        }
    }
}
