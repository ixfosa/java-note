package com.ixfosa.pojo;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Created by ixfosa on 2021/4/14 19:48
 */
public class Demo {
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date time;

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Demo{" +
                "time=" + time.toLocaleString() +
                '}';
    }
}
