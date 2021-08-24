package com.ixfosa.demo6;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by ixfosa on 2021/4/22 20:29
 */
@Component("school6")
public class School {

    @Value("江科")
    private String name;
    @Value("南昌瑶湖校区")
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

