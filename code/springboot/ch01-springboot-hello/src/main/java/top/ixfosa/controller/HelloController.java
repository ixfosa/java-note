package top.ixfosa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import top.ixfosa.config.ConfigInfo;

/**
 * Created by ixfosa on 2021/5/30 15:37
 */
@Controller
public class HelloController {

    /*@Value("${student.name}")
    private String studentName;

    @Value("${websit}")
    private String websit;*/

    @Autowired
    private ConfigInfo configInfo;

    @RequestMapping("hello")
    @ResponseBody
    public String hello() {
        return "Hello " + configInfo.getName() + "<br/>"
                + "Hello " + configInfo.getWebsit();
    }
}
