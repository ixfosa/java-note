package com.ixfosa.pojo;

/**
 * Created by ixfosa on 2021/4/6 15:38
 */
public class PeopleFactory {
    public Person newInstance() {
        return new Person(4410, "ixfosa", 23, '男');
    }
}
