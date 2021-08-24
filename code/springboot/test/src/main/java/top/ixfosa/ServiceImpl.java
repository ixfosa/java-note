package top.ixfosa;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by ixfosa on 2021/8/13 20:39
 */
@org.springframework.stereotype.Service
public class ServiceImpl implements Service {
    @Autowired
    private DaoImpl dao;
    @Override
    public void print() {
        dao.print();
    }
}
