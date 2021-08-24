package com.ixfosa.dao.impl;

import com.ixfosa.dao.AccountDao;
import com.ixfosa.pojo.Account;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Created by ixfosa on 2021/4/9 11:10
 */
public class AccountDaoImpl implements AccountDao {

    JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // 汇款的实现方法
    @Override
    public int out(Account accOut) {
        return this.jdbcTemplate.update("update account set money=money-? " +
                    "where id=? and username=?",
                    accOut.getMoney(), accOut.getId(), accOut.getUsername());
    }

    // 收款的实现方法
    @Override
    public int in(Account accIn) {
        return this.jdbcTemplate.update("update account set money=money+? " +
                    "where id=? and username=?",
                    accIn.getMoney(), accIn.getId(), accIn.getUsername());
    }
}
