package com.ixfosa.dao.impl;

import com.ixfosa.dao.AccountDao;
import com.ixfosa.pojo.Account;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * Created by ixfosa on 2021/4/9 11:10
 */

@Repository("accountDao")
public class AccountDaoImpl implements AccountDao {

    @Resource(name = "jdbcTemplate")
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
