package top.ixfosa.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import top.ixfosa.enums.SexEnum;


/**
 * Created by ixfosa on 2021/8/21 20:23
 */
@Data
@TableName(value = "user")
public class User {
    @TableId( type = IdType.AUTO)
    private Integer id;

    private String name;

    private SexEnum sex;

    // @Version
    // private Integer version;
    @TableLogic(value = "0", delval = "1")
    private Integer deleted;
}
