package top.ixfosa.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import top.ixfosa.interceptors.JWTInterceptor;

/**
 * Created by ixfosa on 2021/8/13 21:17
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new JWTInterceptor())
                .addPathPatterns("/user/test")  // 拦截
                .excludePathPatterns("/user/login"); // 放行
    }
}
