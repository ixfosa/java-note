package top.ixfosa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import top.ixfosa.filter.HelloFilter;
import top.ixfosa.servlet.HelloServlet;

@SpringBootApplication
// @ServletComponentScan
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    //@Bean 是一个方法级别上的注解，主要用在配置类里
    /* 相当于一个
     *      <beans>
     *          <bean id="" class=""/>
     *      </beans>
     * */

    // 注册 servlet
    @Bean
    public ServletRegistrationBean getServletRegistrationBean() {
        ServletRegistrationBean bean = new ServletRegistrationBean(new HelloServlet());
        bean.addUrlMappings("/hello.do");
        return bean;
    }

    // 注册 Filter
    @Bean
    public FilterRegistrationBean getFilterRegistrationBean() {
        FilterRegistrationBean bean = new FilterRegistrationBean(new HelloFilter());
        // bean.addUrlPatterns(new String[]{"*.do", "*.jsp"});
        bean.addUrlPatterns("/hello.do");
        return bean;
    }
}
