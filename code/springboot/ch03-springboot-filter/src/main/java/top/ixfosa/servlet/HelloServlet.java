package top.ixfosa.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *SpringBoot整合Servlet方式一
 *
 *<servlet>
 *	 <servlet-name>HelloServlet</servlet-name>
 *	 <servlet-class>top.ixfosa.servlet.HelloServlet</servlet-class>
 *</servlet>
 *
 *<servlet-mapping>
 *   <servlet-name>HelloServlet</servlet-name>
 *   <url-pattern>/hello</url-pattern>
 *</servlet-mapping>
 *
 */
@WebServlet("/hello.do")
public class HelloServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        writer.println("hello springboot servlet");
    }
}
