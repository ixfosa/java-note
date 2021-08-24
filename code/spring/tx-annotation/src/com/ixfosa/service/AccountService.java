package com.ixfosa.service;

import com.ixfosa.pojo.Account;

/**
 * Created by ixfosa on 2021/4/9 11:19
 */
public interface AccountService {
    // 转账
    public void transfer(Account accOut, Account accIn) throws Exception;
}
