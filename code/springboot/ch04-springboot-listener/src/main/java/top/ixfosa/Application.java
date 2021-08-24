package top.ixfosa;

        import org.springframework.boot.SpringApplication;
        import org.springframework.boot.autoconfigure.SpringBootApplication;
        import org.springframework.boot.web.servlet.ServletComponentScan;
        import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
        import org.springframework.context.annotation.Bean;
        import top.ixfosa.listener.HelloListener;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public ServletListenerRegistrationBean getServletListenerRegistrationBean() {
        ServletListenerRegistrationBean bean =
                new ServletListenerRegistrationBean(new HelloListener());

        return bean;
    }
}
