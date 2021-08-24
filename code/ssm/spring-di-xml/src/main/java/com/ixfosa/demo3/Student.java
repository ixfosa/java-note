package com.ixfosa.demo3;

/**
 * Created by ixfosa on 2021/4/22 20:29
 */
public class Student {

    private String name;
    private int age;

    //声明一个引用类型
    private School school;


    public Student() {
        System.out.println("spring会调用类的无参数构造方法创建对象");
    }

    /**
     * 创建有参数构造方法
     */
    public Student(String myname,int myage, School mySchool){
        System.out.println("=====Student有参数构造方法======");
        // 属性赋值
        this.name  = myname;
        this.age  = myage;
        this.school = mySchool;

    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", school=" + school +
                '}';
    }
}