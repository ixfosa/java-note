package com.ixfosa.test;

import com.ixfosa.pojo.Account;
import com.ixfosa.service.AccountService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by ixfosa on 2021/4/9 11:02
 */

public class Test {

    private AccountService accountService;

    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }

    public static void main(String[] args) throws Exception {

        ClassPathXmlApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("applicationContext.xml");

        Test test = applicationContext.getBean("test", Test.class);

        int money = 100;
        Account accOut = new Account(1, "long", money);
        Account accIn = new Account(2, "zhong", money);

        test.accountService.transfer(accOut, accIn);
    }
}
