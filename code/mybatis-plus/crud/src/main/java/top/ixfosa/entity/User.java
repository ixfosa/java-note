package top.ixfosa.entity;

import com.alibaba.druid.sql.visitor.functions.Char;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;


/**
 * Created by ixfosa on 2021/8/21 20:23
 */

@Data
@TableName(value = "user")
public class User {
    @TableId( type = IdType.AUTO)
    private Integer id;

    private String name;

    private String  sex;
}
