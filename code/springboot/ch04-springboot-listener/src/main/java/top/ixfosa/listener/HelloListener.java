package top.ixfosa.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Created by ixfosa on 2021/5/30 19:50
 */
// @WebListener
public class HelloListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("Listener init.....");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("listener destroy.....");
    }
}
