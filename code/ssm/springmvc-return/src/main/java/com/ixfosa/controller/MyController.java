package com.ixfosa.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ixfosa.pojo.Student;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;


@Controller
public class MyController {
    /**
     * 处理器方法返回String--表示逻辑视图名称，需要配置视图解析器
     */
    @RequestMapping("returnstring_view1")
    public String doReturnView1() {
        System.out.println("doReturnView1...");

        // show : 逻辑视图名称，项目中配置了视图解析器
        return "show";
        // 完整视图路径，项目中不能配置视图解析器
        // return "/WEB-INF/view/shoe.jsp";
    }

    // 处理器方法返回String,表示完整视图路径， 此时不能配置视图解析器
    @RequestMapping("returnstring_view2")
    public String doReturnViwe2() {
        System.out.println("doReturnView2...");

        // show : 逻辑视图名称，项目中配置了视图解析器
        // 框架对视图执行forward转发操作, 显示写出 forward: 不走视图解析器
        return "forward:/WEB-INF/view/show.jsp";
    }



    // 处理器方法返回void， 响应ajax请求
    // 手工实现ajax，json数据： 代码有重复的 1. java对象转为json； 2. 通过HttpServletResponse输出json数据
    @RequestMapping("returnjson")
    @ResponseBody
    public void doReturnJson(HttpServletResponse res, String name, Integer age) throws IOException {
        // 处理ajax， 使用json做数据的格式
        Student student = new Student();
        student.setName(name);
        student.setAge(age);

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(student);
        System.out.println("json: " + json);

        // 输出数据，响应ajax的请求
        res.setContentType("application/json;charset=utf-8");
        PrintWriter writer = res.getWriter();
        writer.println(json);
        writer.flush();
        writer.close();
    }

    /**
     * 处理器方法返回一个Student，通过框架转为json，响应ajax请求
     *
     * @ResponseBody: 作用：把处理器方法返回对象转为json后，通过HttpServletResponse输出给浏览器。
     * 位置：方法的定义上面。 和其它注解没有顺序的关系。
     * 返回对象框架的处理流程：
     * 1. 框架会把返回Student类型，调用框架的中ArrayList<HttpMessageConverter>中每个类的canWrite()方法
     * 检查那个HttpMessageConverter接口的实现类能处理Student类型的数据--MappingJackson2HttpMessageConverter
     * <p>
     * 2.框架会调用实现类的write（）， MappingJackson2HttpMessageConverter的write()方法
     * 把udent对象转为json， 调用Jackson的ObjectMapper实现转为json
     * contentType: application/json;charset=utf-8
     * <p>
     * 3.框架会调用@ResponseBody把2的结果数据输出到浏览器， ajax请求处理完成
     */
    @RequestMapping("returnstudents")
    @ResponseBody
    public List<Student> doReturnStudents() {
        Student stu1 = new Student();
        stu1.setName("龙虾");
        stu1.setAge(22);
        Student stu2 = new Student();
        stu2.setName("楳珎");
        stu2.setAge(21);

        ArrayList<Student> list = new ArrayList<>();
        list.add(stu1);
        list.add(stu2);

        return list;
    }

    /**
     * 处理器方法返回的是String ， String表示数据的，不是视图。
     * 区分返回值String是数据，还是视图，看有没有@ResponseBody注解
     * 如果有@ResponseBody注解，返回String就是数据，反之就是视图
     *
     * 默认使用“text/plain;charset=ISO-8859-1”作为contentType,导致中文有乱码，
     * 解决方案：给RequestMapping增加一个属性 produces, 使用这个属性指定新的contentType.
     * 返回对象框架的处理流程：
     *  1. 框架会把返回String类型，调用框架的中ArrayList<HttpMessageConverter>中每个类的canWrite()方法
     *     检查那个HttpMessageConverter接口的实现类能处理String类型的数据--StringHttpMessageConverter
     *
     *  2.框架会调用实现类的write（）， StringHttpMessageConverter的write()方法
     *    把字符按照指定的编码处理 text/plain;charset=ISO-8859-1
     *
     *  3.框架会调用@ResponseBody把2的结果数据输出到浏览器， ajax请求处理完成
     */
    @RequestMapping("returnstr")
    @ResponseBody
    public String doReturnStr() {
        return "Hello Spring MVC...";
    }
}
