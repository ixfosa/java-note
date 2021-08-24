package top.ixfosa.config;


import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.unit.DataSize;

import javax.servlet.MultipartConfigElement;


/**
 * Created by ixfosa on 2021/5/31 15:09
 */
@Configuration
public class MultFileConfig {

    @Bean
    public MultipartConfigElement getMultipartConfigElement() {

        MultipartConfigFactory factory = new MultipartConfigFactory();

        /**
         * DataSize
         *      ofKilobytes：KB
         *      ofMegabytes：MB
         *      ofBytes：字节
         */

        // 设置单个文件的大小。
        factory.setMaxFileSize(DataSize.ofMegabytes(100));

        // 设置总上传的数据大小。
        factory.setMaxRequestSize(DataSize.ofMegabytes(200));
        return factory.createMultipartConfig();
    }

}
