package top.ixfosa.model.student;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by ixfosa on 2021/8/26 17:10
 */
@Data
@AllArgsConstructor
public class Student implements Serializable {
    private Integer id;
    private String name;
    private Integer age;
    private Character gender;
}
