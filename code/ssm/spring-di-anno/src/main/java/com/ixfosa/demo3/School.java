package com.ixfosa.demo3;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by ixfosa on 2021/4/22 20:29
 */
@Component("school3")
public class School {

    @Value("江科")
    private String name;
    @Value("瑶湖")
    private String address;

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "School{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
