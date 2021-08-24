package com.ixfosa.dao;

import com.ixfosa.pojo.Sale;

/**
 * Created by ixfosa on 2021/4/23 17:48
 */
public interface SaleDao {
    //增加销售记录
    int insertSale(Sale sale);
}

