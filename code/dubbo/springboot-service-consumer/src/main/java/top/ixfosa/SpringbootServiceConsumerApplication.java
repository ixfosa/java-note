package top.ixfosa;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDubbo // (scanBasePackages = "top.ixfosa.controller")
public class SpringbootServiceConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootServiceConsumerApplication.class, args);
    }

}
s