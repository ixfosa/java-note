package com.ixfosa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by ixfosa on 2021/4/25 14:06
 */
@Controller
public class MyController {
    /**
     * 处理器方法返回ModelAndView,实现转发forward
     * 语法： setViewName("forward:视图文件完整路径")
     * forward特点： 不和视图解析器一同使用，就当项目中没有视图解析器
     */
    @RequestMapping("forward")
    public ModelAndView doForward() {
        ModelAndView mv = new ModelAndView();
        // 转发
        // mv.setViewName("forward:/WEB-INF/view/show.jsp");
        // mv.setViewName("show");
        mv.setViewName("forward:hello.jsp");
        return mv;
    }

    /**
     * 处理器方法返回ModelAndView,实现重定向redirect
     * 语法：setViewName("redirect:视图完整路径")
     * redirect特点： 不和视图解析器一同使用，就当项目中没有视图解析器
     *
     * 框架对重定向的操作：
     * 1.框架会把Model中的简单类型的数据，转为string使用，作为hello.jsp的get请求参数使用。
     *   目的是在 doRedirect.do 和 hello.jsp 两次请求之间传递数据
     *
     * 2.在目标hello.jsp页面可以使用参数集合对象 ${param}获取请求参数值
     *    ${param.name}
     *
     * 2.重定向不能访问/WEB-INF资源
     */
    @RequestMapping("redirect")
    public String doRedirect() {
        System.out.println("doRedirect...");
        return "redirect:https://www.baidu.com/";
    }
}
