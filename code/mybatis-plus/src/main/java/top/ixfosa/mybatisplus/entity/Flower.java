package top.ixfosa.mybatisplus.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author ixfosa
 * @since 2021-08-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Flower implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 花名
     */
    private String name;

    /**
     * 价格
     */
    private Float price;

    /**
     * 原产地
     */
    private String production;


}
