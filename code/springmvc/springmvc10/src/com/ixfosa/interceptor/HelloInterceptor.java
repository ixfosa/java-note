package com.ixfosa.interceptor;

import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by ixfosa on 2021/4/14 18:04
 */
public class HelloInterceptor implements HandlerInterceptor {

    // 在进入控制器之前执行
    // 如果返回值为false,阻止进入控制器
    // 控制代码
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("handler: " + handler);
        System.out.println("preHandle...");
        return true;
    }

    // 控制器执行完成,进入到jsp之前执行.
    // 日志记录.
    // 敏感词语过滤
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("to: " + modelAndView.getViewName());
        String word = modelAndView.getModel().get("model").toString();
        System.out.println("model的值: " + word);
        String newWord = word.replace("青菜", "辣条");
        modelAndView.getModel().put("model", newWord);
        System.out.println("postHandle...");
    }

    // jsp执行完成后执行
    // 记录执行过程中出现的异常.
    // 可以把异常记录到日志中
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("afterCompletion: " + ex.getMessage());
    }
}
