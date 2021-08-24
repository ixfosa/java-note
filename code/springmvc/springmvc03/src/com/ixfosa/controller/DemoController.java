package com.ixfosa.controller;

        import com.ixfosa.pojo.*;
        import org.springframework.stereotype.Controller;
        import org.springframework.web.bind.annotation.ModelAttribute;
        import org.springframework.web.bind.annotation.PathVariable;
        import org.springframework.web.bind.annotation.RequestMapping;
        import org.springframework.web.bind.annotation.RequestParam;
        import org.springframework.web.servlet.config.annotation.EnableWebMvc;

        import java.util.Arrays;
        import java.util.Date;
        import java.util.List;


@Controller
public class DemoController {

    @RequestMapping("demo1")
    public String demo1(@RequestParam(required = true) int id,
                        String username,
                        @RequestParam("password") String pwd,
                        @RequestParam(defaultValue = "男") String sex) {

        System.out.println("id: " +id);
        System.out.println("username: " +username);
        System.out.println("pwd: " + pwd);
        System.out.println("sex: " + sex);
        return "hello";
    }

    @RequestMapping("demo2")
    public String demo2(User user) {
        System.out.println(user);
        return "hello";
    }


    @RequestMapping("demo3")
    public String demo3(User3 user3, @RequestParam("hobby[]") List<String> hobby) {
        System.out.println(user3);
        System.out.println(hobby);
        return "hello";
    }

    @RequestMapping("demo4")
    public String demo5(User4 user4) {
        System.out.println(user4);
        return "hello";
    }

    @RequestMapping("demo5")
    public String demo5(User5 user5, String[] hobby) {
        System.out.println(user5);
        System.out.println(Arrays.toString(hobby));
        return "hello";
    }

    @RequestMapping("demo6")
    public String demo6(User6 user6) {
        System.out.println(user6);
        return "hello";
    }

    @RequestMapping("demo7")
    public String demo7(User7 user7) {
        System.out.println(user7.getUser4());
        return "hello";
    }

    @RequestMapping("demo8")
    public String demo8(User8 user8) {
        System.out.println(user8.getUser4());
        return "hello";
    }

    @RequestMapping("demo9")
    public String demo9(User9 user9) {
        System.out.println(Arrays.toString(user9.getUser4()));
        return "hello";
    }

    @RequestMapping("demo10")
    public String demo10(int id, String name) {
        System.out.println("id: " + id + " " + "name: " + name);
        return "hello";
    }

    // 在@RequestMapping 中一定要和请求格式对应
    // {名称} 中名称自定义名称
    // @PathVariable 获取@RequestMapping 中内容,默认按照方法参数名称去寻找
    @RequestMapping("demo11/{id}/{name}")
    public String demo11(@PathVariable int id, @PathVariable("name") String username) {
        System.out.println("id: " + id + " " + "username: " + username);
        return "hello";
    }

    @RequestMapping("demo12")
    public String demo12(Date date) {
        System.out.println("demo12...");
        System.out.println("date: " + date.toString());
        return "hello";
    }
}
