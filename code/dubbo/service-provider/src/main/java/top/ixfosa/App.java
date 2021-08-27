package top.ixfosa;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * Hello world!
 *
 */
public class App {
    public static void main( String[] args ) throws IOException {
        ClassPathXmlApplicationContext ioc =
                new ClassPathXmlApplicationContext("provider.xml");

        ioc.start();

        System.in.read();
    }
}
