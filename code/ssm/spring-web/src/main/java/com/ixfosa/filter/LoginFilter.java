package com.ixfosa.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by ixfosa on 2021/4/24 12:02
 */


public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // 将父接口转为子接口
        HttpServletRequest request = (HttpServletRequest) servletRequest;

        // 1.获取资源请求路径
        String uri = request.getRequestURI();

        if (uri.contains("/login.jsp") || uri.contains("/login") ||
                uri.contains("/regist.jsp") || uri.contains("/regist") ||
                uri.contains("/css/") || uri.contains("/js/")
                || uri.contains("/images/") || uri.contains("/checkCode")) {

            // 登录。放行
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            // 不包含，需要验证用户是否登录
            // 3.从获取session中获取user
            Object stu = request.getSession().getAttribute("stu");
            if(stu != null){
                // 登录了。放行
                filterChain.doFilter(servletRequest, servletResponse);
            }else{
                // 没有登录。跳转登录页面
                // request.setAttribute("login_msg","您尚未登录，请登录");
                request.getRequestDispatcher("/login.jsp").forward(request,servletResponse);
            }
        }
    }

    @Override
    public void destroy() {

    }
}
