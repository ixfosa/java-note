package com.ixfosa.servlet;

import com.ixfosa.pojo.Flower;
import com.ixfosa.service.FlowerService;
import com.ixfosa.service.impl.FlowerServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by ixfosa on 2021/4/6 18:30
 */

@WebServlet("/flower")
public class FlowerServlet extends HttpServlet {
    FlowerService flowerService;
    @Override
    public void init() {
        // spring和web整合后所有信息都存放在webApplicationContext
        ApplicationContext applicationContext = WebApplicationContextUtils.
                getRequiredWebApplicationContext(getServletContext());

        flowerService = applicationContext.getBean("flowerService", FlowerServiceImpl.class);
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Flower> flowers = flowerService.showFlower();
        for (Flower flower : flowers) {
            System.out.println(flower);
        }
    }
}
