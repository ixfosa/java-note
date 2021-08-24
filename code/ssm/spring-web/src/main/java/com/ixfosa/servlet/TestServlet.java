package com.ixfosa.servlet;

import com.ixfosa.pojo.Student;
import com.ixfosa.service.StudentService;
import com.ixfosa.service.impl.StudentServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by ixfosa on 2021/4/24 12:44
 */


@WebServlet("/test")
public class TestServlet extends HttpServlet {

    StudentService service;
    @Override
    public void init() throws ServletException {
        // 创建spring的容器对象
        // String config= "applicationContext.xml";
        // ApplicationContext ctx = new ClassPathXmlApplicationContext(config);

        // WebApplicationContext ctx = null;
        //获取ServletContext中的容器对象，创建好的容器对象，拿来就用
        /*
        String key =  WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE;
        Object attr  = getServletContext().getAttribute(key);
        if( attr != null){
            ctx = (WebApplicationContext)attr;
        }
        */

        ApplicationContext ctx = WebApplicationContextUtils.
                getRequiredWebApplicationContext(getServletContext());

        service = ctx.getBean("studentService", StudentServiceImpl.class);
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {



        Student student = new Student("name", "email", 11);
        service.regist(student);

    }
}
