package com.ixfosa.dao;

import com.ixfosa.pojo.Flower;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by ixfosa on 2021/4/6 18:14
 */
public interface FlowerDao {
    @Select("select * from flower")
    List<Flower> selAll();
}
