package com.ixfosa.service.impl;

import com.ixfosa.dao.FlowerDao;
import com.ixfosa.pojo.Flower;
import com.ixfosa.service.FlowerService;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * Created by ixfosa on 2021/4/6 18:24
 */
public class FlowerServiceImpl implements FlowerService {

    FlowerDao flowerDao;

    public FlowerDao getFlowerDao() {
        return flowerDao;
    }

    public void setFlowerDao(FlowerDao flowerDao) {
        this.flowerDao = flowerDao;
    }

    @Override
    public List<Flower> showFlower() {
        return flowerDao.selAll();
    }
}
