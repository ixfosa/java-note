package dao;


import org.apache.ibatis.annotations.Select;
import pojo.Flower;

import java.util.List;

/**
 * Created by ixfosa on 2021/3/21 15:49
 */
public interface FlowerDaoMapper {
    @Select("SELECT * FROM flower")
    List<Flower> selAll();
}
