package top.ixfosa.mybatisplus.entity;

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
public class Myuser implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String name;

    private Integer age;

    private Integer sex;


}
