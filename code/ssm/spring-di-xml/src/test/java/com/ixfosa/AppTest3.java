package com.ixfosa;

import com.ixfosa.demo3.Student;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import static org.junit.Assert.assertTrue;

/**
 * Unit test for simple App.
 */
public class AppTest3 {
    @Test
    public void test01() {
        ClassPathXmlApplicationContext ac =
                new ClassPathXmlApplicationContext("applicationContext3.xml");

        Student student1 = ac.getBean("student1", Student.class);
        Student student2 = ac.getBean("student2", Student.class);
        Student student3 = ac.getBean("student3", Student.class);
        System.out.println(student1);
        System.out.println(student2);
        System.out.println(student2);
    }

    @Test
    public void test02() {
        ClassPathXmlApplicationContext ac =
                new ClassPathXmlApplicationContext("applicationContext3.xml");

        File myFile = ac.getBean("myFile", File.class);
        FileReader fileReader = null;
        try{
            fileReader = new FileReader(myFile);
            char[] buf = new char[1024];
            int len = 0;
            while ((len = fileReader.read(buf)) != -1) {
                System.out.println(new String(buf, 0, len));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileReader != null) {
                try {
                    fileReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}