package top.ixfosa.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import top.ixfosa.entity.User;
import top.ixfosa.vo.ProductVO;

import java.util.List;

/**
 * Created by ixfosa on 2021/8/21 20:26
 */
@Repository
public interface UserMapper extends BaseMapper<User> {

    @Select("select p.name pname, p.price, u.name uname, u.id " +
            "from product p, user u where p.user_id = u.id and u.id = #{id}")
    List<ProductVO> productList(Integer id);
}
