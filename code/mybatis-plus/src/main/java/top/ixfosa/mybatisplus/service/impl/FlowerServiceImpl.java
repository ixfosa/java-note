package top.ixfosa.mybatisplus.service.impl;

import top.ixfosa.mybatisplus.entity.Flower;
import top.ixfosa.mybatisplus.mapper.FlowerMapper;
import top.ixfosa.mybatisplus.service.FlowerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ixfosa
 * @since 2021-08-23
 */
@Service
public class FlowerServiceImpl extends ServiceImpl<FlowerMapper, Flower> implements FlowerService {

}
