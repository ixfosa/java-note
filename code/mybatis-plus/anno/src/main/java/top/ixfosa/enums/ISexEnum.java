package top.ixfosa.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.baomidou.mybatisplus.annotation.IEnum;

/**
 * Created by ixfosa on 2021/8/22 20:03
 */
public enum ISexEnum implements IEnum<Integer> {
    MAN(0, "男"),
    WOMAN(1, "女");

    @EnumValue  // 存入数据库的字段上加上@EnumValue注解
    private final Integer code;
    private final String desc;

    ISexEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    @Override
    public Integer getValue() {
        return this.code;
    }
}
