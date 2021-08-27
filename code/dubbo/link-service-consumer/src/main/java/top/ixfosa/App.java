package top.ixfosa;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import top.ixfosa.service.SayService;

import java.io.IOException;

/**
 * Hello world!
 *
 */
public class App {
    public static void main( String[] args ) throws IOException {
        ClassPathXmlApplicationContext ioc =
                new ClassPathXmlApplicationContext("consumer.xml");

        SayService sayService = ioc.getBean(SayService.class);
        sayService.say();

        System.out.println( "Hello World!" );
        System.out.println("调用完成....");

        System.in.read();
    }
}

