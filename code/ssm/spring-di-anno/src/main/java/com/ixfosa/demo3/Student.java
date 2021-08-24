package com.ixfosa.demo3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by ixfosa on 2021/4/22 20:29
 */
@Component("student3")
public class Student {

    /**
     * @Value: 简单类型的属性赋值
     *   属性： value 是String类型的，表示简单类型的属性值
     *   位置： 1.在属性定义的上面，无需set方法，推荐使用。
     *         2.在set方法的上面
     */
    @Value("小佛" )
    private String name;
    private Integer age;

    /**
     * 引用类型
     * @Autowired: spring框架提供的注解，实现引用类型的赋值。
     * spring中通过注解给引用类型赋值，使用的是自动注入原理 ，支持byName, byType
     * @Autowired:默认使用的是 byType 自动注入。
     *
     *  位置：1）在属性定义的上面，无需set方法， 推荐使用
     *       2）在set方法的上面
     */
    @Autowired
    private School school;

    public Student() {
        System.out.println("==student无参数构造方法===");
    }

    public void setName(String name) {
        this.name = name;
    }
    @Value("23")
    public void setAge(Integer age) {
        System.out.println("setAge:"+age);
        this.age = age;
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
