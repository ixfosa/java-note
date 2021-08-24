package com.ixfosa.filter;


import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by ixfosa on 2021/4/24 11:57
 */

@WebFilter("/*")
public class CharchaterFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // 将父接口转为子接口
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;
        // 获取请求方法
        String method = req.getMethod();

        if ("post".equalsIgnoreCase(method)) {
            req.setCharacterEncoding("utf-8");
        }
        // 处理响应乱码
        res.setHeader("Content-type", "text/html;charset=utf-8");
        filterChain.doFilter(req, res);
    }

    @Override
    public void destroy() {

    }
}
