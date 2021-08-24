package top.ixfosa.mybatisplus.service.impl;

import top.ixfosa.mybatisplus.entity.User;
import top.ixfosa.mybatisplus.mapper.UserMapper;
import top.ixfosa.mybatisplus.service.UserService;
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
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
