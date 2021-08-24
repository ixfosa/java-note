package dao;

import pojo.Idcard;

/**
 * Created by ixfosa on 2021/3/26 14:53
 */
public interface IdcardDao {
    Idcard selectCodeById(Integer id);

    Integer insertIdcard(Idcard idcard);

    Integer updateIdcard(Idcard idcard);

    Integer deleteIdcard(Integer id);
}
