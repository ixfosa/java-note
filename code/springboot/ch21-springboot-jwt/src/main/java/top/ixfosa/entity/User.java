package top.ixfosa.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Created by ixfosa on 2021/8/13 17:14
 */
@Data
@AllArgsConstructor
@Accessors(chain = true) // 链式访问，该注解设置chain=true，生成setter方法返回this
public class User {
    private String id;
    private String name;
    private String password;
}
