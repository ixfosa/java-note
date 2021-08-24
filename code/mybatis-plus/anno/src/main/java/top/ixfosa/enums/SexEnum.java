package top.ixfosa.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Data;

/**
 * Created by ixfosa on 2021/8/22 20:03
 */
public enum SexEnum {
    MAN(0, "男"),
    WOMAN(1, "女");

    @EnumValue  // 存入数据库的字段上加上@EnumValue注解
    private final Integer code;
    private final String desc;

    SexEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }
}
