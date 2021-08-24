package resultmap.dao;

import bean.Flower;

import java.util.List;
import java.util.Map;

/**
 * Created by ixfosa on 2021/3/26 10:42
 */
public interface FlowerDao {
    List<Map<String,Object>> selectAllFlowerMap();
    List<Flower> selectAllFlowerResMap();
}
