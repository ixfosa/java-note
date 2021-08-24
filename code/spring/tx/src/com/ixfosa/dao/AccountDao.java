package com.ixfosa.dao;

import com.ixfosa.pojo.Account;

/**
 * Created by ixfosa on 2021/4/9 11:08
 */
public interface AccountDao {
    // 汇款
    public int out(Account account);

    // 收款
    public int in(Account account);
}
