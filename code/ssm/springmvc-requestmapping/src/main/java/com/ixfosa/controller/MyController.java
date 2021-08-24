package com.ixfosa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * @RequestMapping:
 *    value ： 所有请求地址的公共部分，叫做模块名称
 *    位置： 放在类的上面
 */
@Controller
@RequestMapping("/hello")
public class MyController {
    /**
     * @RequestMapping : 请求映射
     *             属性： method， 表示请求的方式。 它的值RequestMethod类枚举值。
     *                    例如表示get请求方式， RequestMethod.GET
     *                    post方式， RequestMethod.POST
     *
     *  你不用get方式，错误是：
     *  HTTP Status 405 - Request method 'GET' not supported
     */
    // 使用get请求方式
    @RequestMapping(value = "some", method = RequestMethod.GET)
    public ModelAndView doSome() {
        ModelAndView mv = new ModelAndView();

        mv.setViewName("redirect:/show.jsp");
        return mv;
    }

    // 指定other是post请求方式
    // HTTP状态 405 - 方法不允许
    @RequestMapping(value = "other", method = RequestMethod.POST)
    public ModelAndView doOther() {
        ModelAndView mv = new ModelAndView();

        mv.setViewName("redirect:/show.jsp");
        return mv;
    }

    // 不指定请求方式，没有限制
    @RequestMapping(value = "first")
    public ModelAndView first() {
        ModelAndView mv = new ModelAndView();

        mv.setViewName("redirect:/show.jsp");
        return mv;
    }
}
