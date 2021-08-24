package top.ixfosa.mybatisplus.service.impl;

import top.ixfosa.mybatisplus.entity.Product;
import top.ixfosa.mybatisplus.mapper.ProductMapper;
import top.ixfosa.mybatisplus.service.ProductService;
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
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {

}
