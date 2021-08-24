package top.ixfosa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import top.ixfosa.servlet.HelloServlet;


@SpringBootApplication
// @ServletComponentScan   // 在 springBoot 启动时会扫描@WebServlet，并将该类实例化
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public ServletRegistrationBean getServletRegistrationBean() {
        ServletRegistrationBean bean = new ServletRegistrationBean(new HelloServlet());
        bean.addUrlMappings("/hello");
        return bean;
    }
}
