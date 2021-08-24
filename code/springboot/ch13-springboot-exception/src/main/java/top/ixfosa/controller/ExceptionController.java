package top.ixfosa.controller;

        import org.springframework.stereotype.Controller;
        import org.springframework.web.bind.annotation.ExceptionHandler;
        import org.springframework.web.bind.annotation.RequestMapping;
        import org.springframework.web.servlet.ModelAndView;

/**
 * Created by ixfosa on 2021/6/7 14:22
 */
@Controller
public class ExceptionController {

    @RequestMapping("show1")
    public String show1() {
        String str = null;

        str.length();
        return "index";
    }

    @RequestMapping("show2")
    public String show2() {

        int a = 1 / 0;
        System.out.println("a = " + a);
        return "index";
    }
}
