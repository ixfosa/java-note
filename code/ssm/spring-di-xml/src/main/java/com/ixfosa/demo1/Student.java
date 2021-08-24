package com.ixfosa.demo1;

/**
 * Created by ixfosa on 2021/4/22 20:22
 */
public class Student {
    private String name;
    private Integer age;

    /*
     需要有set方法，没有set方法是报错的。
     Bean property 'name' is not writable or has an invalid setter method
    */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
