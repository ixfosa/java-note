package com.ixfosa.service.impl;

import com.ixfosa.dao.AccountDao;
import com.ixfosa.pojo.Account;
import com.ixfosa.service.AccountService;

/**
 * Created by ixfosa on 2021/4/9 11:21
 */
public class AccountServiceImpl implements AccountService {
    private AccountDao accountDao;

    public void setAccountDao(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    @Override
    public void transfer(Account accOut, Account accIn) throws Exception {
        int idxOut = accountDao.out(accOut);
        // 模拟转账出现错误或异常
        // int a = 1 / 0;
        int idxIn = accountDao.in(accIn);

        if (idxOut <=0 || idxIn <= 0) {
            throw new Exception("err...");
        }
    }
}
