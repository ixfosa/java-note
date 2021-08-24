package top.ixfosa.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * Created by ixfosa on 2021/5/30 17:38
 */

// @WebFilter(urlPatterns = {"*.do", "*.jsp"})
public class HelloFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("into Filter.....");
        filterChain.doFilter(servletRequest, servletResponse);
        System.out.println("leave Filter.....");
    }

    @Override
    public void destroy() {

    }
}
