package com.ixfosa;

import static org.junit.Assert.assertTrue;

import com.ixfosa.service.impl.BuyGoodsServiceImpl;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Unit test for simple App.
 */
public class AppTest {
    @Test
    public void test() {
        ClassPathXmlApplicationContext ac =
                new ClassPathXmlApplicationContext("applicationContext.xml");
        BuyGoodsServiceImpl service = ac.getBean("buyGoodsServiceImpl", BuyGoodsServiceImpl.class);

        service.buy(1, 2);
    }
}
