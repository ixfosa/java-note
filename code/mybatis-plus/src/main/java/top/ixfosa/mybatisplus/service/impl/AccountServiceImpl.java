package top.ixfosa.mybatisplus.service.impl;

import top.ixfosa.mybatisplus.entity.Account;
import top.ixfosa.mybatisplus.mapper.AccountMapper;
import top.ixfosa.mybatisplus.service.AccountService;
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
public class AccountServiceImpl extends ServiceImpl<AccountMapper, Account> implements AccountService {

}
