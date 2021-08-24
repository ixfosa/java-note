package top.ixfosa;

import org.springframework.stereotype.Repository;

/**
 * Created by ixfosa on 2021/8/13 20:38
 */
@Repository
public class DaoImpl implements Dao {

    @Override
    public void print() {
        System.out.println("tset...");
    }
}
