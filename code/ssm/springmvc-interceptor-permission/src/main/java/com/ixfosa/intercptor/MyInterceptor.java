package com.ixfosa.intercptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by ixfosa on 2021/4/25 15:32
 */
public class MyInterceptor implements HandlerInterceptor {

    // 验证登录的用户信息， 正确return true，其它return false
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String uri = request.getRequestURI();
        String passUrl = request.getContextPath() + "/login";
        System.out.println(passUrl);
        if (uri.equals(passUrl)) {
            return true;
        } else {
            Object user = request.getSession().getAttribute("user");
            if(user!=null){
                return true;
            }
            response.sendRedirect("index.jsp");
            return false;
        }
    }
}
