package top.ixfosa.domain;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * Created by ixfosa on 2021/5/31 16:21
 */


public class User implements Serializable {

    /**
     * @NotNull：不能为null，但可以为empty
     * @NotEmpty：不能为null，而且长度必须大于0
     * @NotBlank：只能作用在String上，不能为null，而且调用trim()后，长度必须大于0
     */
    @NotBlank(message = "用户名不能为空")   // 非空校验
    @Length(min = 2, max = 6, message = "最小长度为2位，最大长度为6位")
    private String name;

    @NotEmpty(message = "密码不能为空")
    @Length(min = 6, max = 32)
    private String password ;

    @Max(value = 150)
    public Integer age;

    @Email
    private String email;

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
