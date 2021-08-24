package top.ixfosa.pojo.many2many;



import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

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

    /**
     * 配置用户到角色的多对多关系
     * 配置多对多的映射关系
     * 1.声明表关系的配置
     *
     * @ManyToMany(targetEntity = Role.class)  //多对多
     * targetEntity：代表对方的实体类字节码
     * 2.配置中间表（包含两个外键）
     * @JoinTable name : 中间表的名称
     * joinColumns：配置当前对象在中间表的外键
     * @JoinColumn的数组 name：外键名
     * referencedColumnName：参照的主表的主键名
     * inverseJoinColumns：配置对方对象在中间表的外键
     */
    @ManyToMany(cascade = CascadeType.ALL)
    // @JoinTable:配置中间表信息
    // joinColumns:建立当前表在中间表中的外键字段
    // inverseJoinColumns，对方对象在中间表的外键
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<Role> roles = new HashSet<>();

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

    public Set<Role> getRole() {
        return roles;
    }

    public void setRole(Set<Role> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
