package com.ixfosa.controller;

import com.ixfosa.pojo.Student;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import sun.awt.SunHints;

/**
 * Created by ixfosa on 2021/4/24 14:47
 */
@Controller
public class MyController {

    /**
     * 逐个接收请求参数：
     *   要求： 处理器（控制器）方法的形参名和请求中参数名必须一致。
     *          同名的请求参数赋值给同名的形参
     * 框架接收请求参数
     *   1. 使用request对象接收请求参数
     *      String strName = request.getParameter("name");
     *      String strAge = request.getParameter("age");
     *   2. springmvc框架通过 DispatcherServlet 调用 MyController的receiveBaseType()方法
     *      调用方法时，按名称对应，把接收的参数赋值给形参
     *      receiveBaseType（strName，Integer.valueOf(strAge)）
     *      框架会提供类型转换的功能，能把String转为 int ，long ， float， double等类型。
     *
     *  400状态码是客户端错误， 表示提交请求参数过程中，发生了问题。
     */

    @RequestMapping("basetype")
    public void receiveBaseType(String name, Integer age) {
        System.out.println("name: " + name);
        System.out.println("age: " + age);
    }

    /**
     * 请求中参数名和处理器方法的形参名不一样
     * @RequestParam: 逐个接收请求参数中， 解决请求中参数名形参名不一样的问题
     *      属性： 1. value 请求中的参数名称
     *            2. required 是一个boolean，默认是true
     *                true：表示请求中必须包含此参数。
     *      位置： 在处理器方法的形参定义的前面
     */
    @RequestMapping("paramdiff")
    public void receiveParamDiff(@RequestParam(value = "name", required = false) String myname, @RequestParam("age") Integer myage) {
        System.out.println("myname: " + myname);
        System.out.println("myage: " + myage);
    }

    /**
     * 处理器方法形参是java对象， 这个对象的属性名和请求中参数名一样的
     * 框架会创建形参的java对象， 给属性赋值。 请求中的参数是name，框架会调用setName()
     * @return
     */
    @RequestMapping("Objtyte")
    public void receiveObjTyte(Student student) {
        System.out.println(student);
    }


}
