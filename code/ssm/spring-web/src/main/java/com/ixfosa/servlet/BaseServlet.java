package com.ixfosa.servlet;

import com.ixfosa.service.StudentService;
import com.ixfosa.service.impl.StudentServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


/**
 * Created by ixfosa on 2021/4/24 11:44
 */
public class BaseServlet extends HttpServlet {

    public StudentService service;

    @Override
    public void init() {
        ApplicationContext ctx = WebApplicationContextUtils.
                getRequiredWebApplicationContext(getServletContext());
        service = ctx.getBean("studentService", StudentServiceImpl.class);
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        // 1.获取请求路径
        String methodName = req.getParameter("method");

        // 2.获取方法名称
        System.out.println("methodName: " + methodName);

        // 3.获取方法对象Method
        try {
            // 获取方法
            Method method = this.getClass().
                    getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);

            // 4.执行方法
            // 暴力反射
            // method.setAccessible(true);
            Object obj  = method.invoke(this, req, res);

            if(obj!=null){
                //将返回值转换为string类型
                String target = obj.toString();
                if(target.startsWith("redirect:")){
                    //重定向
                    target = target.substring("redirect:".length());
                    res.sendRedirect(target);
                }else {
                    // 请求转发
                    req.getRequestDispatcher(target).forward(req,res);
                }
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
