package top.ixfosa.mybatisplus.service.impl;

import top.ixfosa.mybatisplus.entity.Orders;
import top.ixfosa.mybatisplus.mapper.OrdersMapper;
import top.ixfosa.mybatisplus.service.OrdersService;
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
public class OrdersServiceImpl extends ServiceImpl<OrdersMapper, Orders> implements OrdersService {

}
