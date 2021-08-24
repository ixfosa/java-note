package top.ixfosa.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Hello World 控制器
 * @author <a href="https://waylau.com">Way Lau</a> 
 * @date 2017年1月26日
 */
@Controller
public class HelloController {
	@RequestMapping("/hello")
	@ResponseBody
	public String hello() {
	    return "Hello World! Welcome to visit waylau.com!";
	}

	@RequestMapping("/{page}")
	public String page(@PathVariable String page) {
		return page;
	}
}
