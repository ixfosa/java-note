package top.ixfosa.mybatisplus.service.impl;

import top.ixfosa.mybatisplus.entity.Person;
import top.ixfosa.mybatisplus.mapper.PersonMapper;
import top.ixfosa.mybatisplus.service.PersonService;
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
public class PersonServiceImpl extends ServiceImpl<PersonMapper, Person> implements PersonService {

}
