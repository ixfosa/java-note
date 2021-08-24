package top.ixfosa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by ixfosa on 2021/8/13 20:42
 */
@RestController
public class Controller {

    @Autowired
    private Service service;

    @GetMapping("test")
    public String test() {
        service.print();
        return "yes";
    }
}
