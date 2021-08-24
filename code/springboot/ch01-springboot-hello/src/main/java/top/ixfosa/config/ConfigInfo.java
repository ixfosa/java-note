package top.ixfosa.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by ixfosa on 2021/5/30 16:36
 */
@Component
@ConfigurationProperties(prefix = "student")
public class ConfigInfo {
    private String name;
    private String websit;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWebsit() {
        return websit;
    }

    public void setWebsit(String websit) {
        this.websit = websit;
    }
}
