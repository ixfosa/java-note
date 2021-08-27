package top.ixfosa;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import com.alibaba.dubbo.config.spring.context.annotation.EnableDubboConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
@MapperScan("top.ixfosa.mapper")
@EnableDubbo(scanBasePackages="top.ixfosa.service")
public class SpringbootServiceProviderApplication {

    public static void main(String[] args) throws IOException {
        SpringApplication.run(SpringbootServiceProviderApplication.class, args);
        System.in.read();
    }
}
