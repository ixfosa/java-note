package top.ixfosa.pojo.one2one;



import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by ixfosa on 2021/5/24 15:22
 */

@Entity
@Table(name = "user")
public class User implements Serializable {

    @Id
    //strategy=GenerationType.IDENTITY 自增长
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private Integer age;

    @OneToOne(cascade = CascadeType.PERSIST)
    // @JoinColumn：就是维护一个外键
    @JoinColumn(name = "role_id")
    private Role role;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", role=" + role +
                '}';
    }
}
