package dao;

import pojo.Flower;
import sun.plugin2.os.windows.FLASHWINFO;

import java.util.List;

/**
 * Created by ixfosa on 2021/3/27 13:57
 */
public interface FlowerDao {
    List<Flower> findFlower(Flower flower);
    List<Flower> findFlowerByChoose (Flower flower);
    List<Flower> findFlowerByWhere (Flower flower);
    int updateFlowerBySet(Flower flower);
    List<Flower> findFlowerByTrim(Flower flower);
    List<Flower> findFlowerByBind(Flower flower);
    List<Flower> findFlowerByForeach(List<Integer> id);
    int insertFlower(List<Flower> flowerList);
}
