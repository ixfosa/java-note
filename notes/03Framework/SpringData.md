## Spring-Data 概述

### Spring Data 概述

1. Spring Data: Spring 的一个子项目。使命是为数据访问提供熟悉且一致的基于Spring的编程模型，同时仍保留底层数据存储的特殊特性。用于简化数据库访问，支持NoSQ和关系数据库存储。其主要目标是使数据库的访问变得方便快捷。

2. Spring Data 项目所支持数据存储：

   + NoSQL存储：
     + MongoDB（文档数据库）
     +  Neo4j （图形数据库）
     +  Redis（键/值存储）
     + Hbase（列族数据库）
   + 关系数据存储技术：
     + JDBC
     + `JPA`

   

### ORM概述

1. ORM（Object-Relational Mapping）是一种思想

   + O代表的是Objcet
   + R代表的是Relative
   + M代表的是Mapping

2. 主要目的：操作实体类就相当于操作数据库表

3. 建立两个映射关系：

   + 实体类和表的映射关系
   + 实体类中属性和表中字段的映射关系

4. 作用：减少重复性代码。主要实现程序对象到关系数据库数据的映射。

5. 常见ORM框架

   + Mybatis（ibatis）
   + Hibernate
   + Jpa

   

### hibernate与JPA的概述

1. `Hibernate`是一个开放源代码的对象关系映射框架，它对JDBC进行了非常轻量级的对象封装，它将POJO与数据库表建立映射关系，是一个全自动的orm框架，hibernate可以自动生成SQL语句，自动执行，使程序员可以使用`对象编程思维`来操纵数据库



2. `JPA`的全称是Java Persistence API， 即Java 持久化API，是SUN公司推出的一套基于ORM的规范，内部是由一系列的接口和抽象类构成。

   JPA通过JDK 5.0注解描述`对象－关系表`的映射关系，并将运行期的实体对象持久化到数据库中。

   

### JPA与hibernate的关系

1. `JPA`规范本质上就是一种`ORM`规范，注意不是ORM框架——因为JPA并未提供ORM实现，它只是制订了一些规范，提供了一些编程的API接口，但具体实现则由服务厂商来提供实现。     
2. Hibernate 在 3.2 以后根据 JPA 规范提供了一套操作持久层的 API

3. JPA和Hibernate的关系就像JDBC和JDBC驱动的关系，JPA是规范，Hibernate除了作为ORM框架之外，它也是一种JPA实现。如果使用JPA规范进行数据库操作，底层需要hibernate作为其实现类完成数据持久化工作。



## Spring 整合 Hibernate

### 相关jar文件

[jar文件](https://sourceforge.net/projects/hibernate/files/hibernate-orm/5.4.31.Final/)

1. Hibernate 必须包

   + antlr-2.7.7.jar      言转换工具，Hibernate 利用它实现 HQL 到 SQL 的转换
   + byte-buddy-1.10.21.jar
   + classmate-1.5.1.jar
   + dom4j-2.1.3.jar        dom4j XML解析器
   + FastInfoset-1.2.15.jar
   + hibernate-commons-annotations-5.1.2.Final.jar
   + hibernate-core-5.4.31.Final.jar         Hibernate 的核心包
   + istack-commons-runtime-3.0.7.jar
   + jandex-2.2.3.Final.jar
   + javassist-3.27.0-GA.jar              代码生成工具，Hibernate 利用它在运行时扩展 Java 类
   + javax.activation-api-1.2.0.jar
   + javax.persistence-api-2.2.jar
   + jaxb-api-2.3.1.jar
   + jaxb-runtime-2.3.1.jar
   + jboss-logging-3.4.1.Final.jar
   + jboss-transaction-api_1.2_spec-1.1.1.Final.jar
   + stax-ex-1.8.jar
   + txw2-2.3.1.jar

   

2. Log4j 日志文件核心 JAR

   + commons-logging-1.2.jar

   

3. 数据库连接池

   + druid-1.1.10.jar

   

4. mysql数据库驱动

   + mysql-connector-java-5.1.7-bin.jar



5. spring相关包
   + spring-aop-5.1.3.RELEASE.jar
   + spring-aspects-5.1.3.RELEASE.jar
   + spring-beans-5.1.3.RELEASE.jar
   + spring-context-5.1.3.RELEASE.jar
   + spring-core-5.1.3.RELEASE.jar
   + spring-expression-5.1.3.RELEASE.jar
   + spring-jdbc-5.1.3.RELEASE.jar
   + spring-orm-5.1.3.RELEASE.jar
   + spring-test-5.1.3.RELEASE.jar
   + spring-tx-5.1.3.RELEASE.jar



### 配置文件

#### jdbc.properties

```properties
jdbc.url=jdbc:mysql://localhost:3306/springdata?useUnicode=true&&characterEncoding=utf8
jdbc.username=root
jdbc.password=ixfosa
jdbc.maxActive=30
```

#### applicationContext

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context" xmlns:tx="http://www.springframework.org/schema/tx"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
       http://www.springframework.org/schema/beans/spring-beans.xsd
       http://www.springframework.org/schema/context
       https://www.springframework.org/schema/context/spring-context.xsd
       http://www.springframework.org/schema/tx
       http://www.springframework.org/schema/tx/spring-tx.xsd">

    <!-- 配置读取properties文件的工具类 -->
    <context:property-placeholder location="classpath:jdbc.properties" />

    <!-- 配置c3p0数据库连接池 -->
    <bean id="dataSource" class="com.alibaba.druid.pool.DruidDataSource">
        <property name="url" value="${jdbc.url}" />
        <property name="username" value="${jdbc.username}" />
        <property name="password" value="${jdbc.password}" />
        <property name="maxActive" value="${jdbc.maxActive}" />
    </bean>

    <!-- 配置Hibernate的SeesionFactory -->
    <bean id="sessionFactory" class="org.springframework.orm.hibernate5.LocalSessionFactoryBean">
        <property name="dataSource" ref="dataSource" />
        <!-- hibernateProperties属性：配置与hibernate相关的内容，如显示sql语句，开启正向工程 -->
        <property name="hibernateProperties">
            <props>
                <prop key="hibernate.show_sql">true</prop>
                <prop key="hibernate.hbm2ddl.auto">update</prop>
            </props>
        </property>
        <property name="packagesToScan" value="top.ixfosa.pojo" />
    </bean>

    <!-- 配置Hibernate的事务管理器 -->
    <bean id="txManager" class="org.springframework.orm.hibernate5.HibernateTransactionManager">
        <property name="sessionFactory" ref="sessionFactory" />
    </bean>
    <!-- 配置开启注解事务处理 -->
    <tx:annotation-driven transaction-manager="txManager" />

    <!-- 配置springIOC的注解扫描 -->
    <context:component-scan base-package="top.ixfosa" />

    <bean id="hibernateTemplate" class="org.springframework.orm.hibernate5.HibernateTemplate">
        <property name="sessionFactory" ref="sessionFactory" />
    </bean>
</beans>
```

### Hibernate-crud操作

#### 领域模型

```java
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

    // setter...
    // getter...
    // toString...
}
```



#### 数据持久

```java
public interface UserDao {
    void insUser(User user);
    void updUser(User user);
    void delUser(User user);
    User selUserById(Integer id);
}



@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private HibernateTemplate hibernateTemplate;


    @Override
    public void insUser(User user) {
        this.hibernateTemplate.save(user);
    }

    @Override
    public void updUser(User user) {
        this.hibernateTemplate.update(user);
    }

    @Override
    public void delUser(User user) {
        this.hibernateTemplate.delete(user);
    }

    @Override
    public User selUserById(Integer id) {
        return this.hibernateTemplate.get(User.class, id);
    }
}
```



#### 测试代码

 ```java
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class UserDaoImplTest {

    @Autowired
    private UserDao userDao;

    @Test
    @Transactional   // 在测试类对于事务提交方式默认的是回滚。
    @Rollback(false) // 取消自动回滚
    public void testInsUser() {
        User user = new User();
        user.setName("奥特曼");
        user.setAge(99);
        // Hibernate: insert into user (age, name) values (?, ?)
        this.userDao.insUser(user);
    }

    @Test
    @Transactional   // 在测试类对于事务提交方式默认的是回滚。
    @Rollback(false) // 取消自动回滚
    public void testUpdUser() {
        User user = new User();
        user.setId(2);
        user.setName("大龙虾");
        user.setAge(22);
        // Hibernate: update user set age=?, name=? where id=?
        this.userDao.updUser(user);
    }

    @Test
    @Transactional   // 在测试类对于事务提交方式默认的是回滚。
    @Rollback(false) // 取消自动回滚
    public void testDelUser() {
        User user = new User();
        user.setId(1);
        // Hibernate: delete from user where id=?
        this.userDao.delUser(user);
    }

    @Test
    public void testSelUser() {
        // Hibernate: select user0_.id as id1_0_0_, user0_.age as age2_0_0_,
        // user0_.name as name3_0_0_ from user user0_ where user0_.id=?
        User user = this.userDao.selUserById(2);
        System.out.println(user);
    }
}
 ```



### Hibernate-HQL查询

#### HQL概述

1. 简述：

   `HQL`（Hibernate Query Language）是 Hibernate 查询语言的简称，它是一种面向对象的查询语言，与 SQL 查询语言有些类似，但它使用的是类、对象和属性的概念，而没有表和字段的概念。

   

2. HQL 查询与 SQL 查询相比，具有以下优点。
   - 直接针对实体类和属性进行查询，不用再编写繁琐的 SQL 语句。
   - 查询结果直接保存在 List 集合中，不用再次封装。
   - 针对不同的数据库会自动生成不同的 SQL 语句。



3. 在 Hibernate 提供的几种检索方式中，HQL 是官方推荐的查询语言，也是使用最频繁的一种检索方式，其具有以下主要功能。

   - 在查询语句中设定各种查询条件。
   - 支持投影查询，即仅检索出对象的部分属性。
   - 提供内置聚集函数，如 sum()、min() 和 max()。
   - 支持分组查询，允许使用 group by 和 having 关键字。
   - 支持分页查询。
   - 支持子查询，即嵌套查询。
   - 支持动态绑定参数。

   

4. HQL 的语法格式与 SQL 非常相似，并且在 Hibernate 中专门为 HQL 提供了一个 Query 查询接口执行各种复杂的查询语句。HQL 的完整语法格式如下所示：

   ```sql
   [select/update/delete...]
   from...
   [where...][group by...][having...][order by...][asc/desc]
   // HQL 查询与 SQL 查询非常类似。
   ```

   通常情况下，当检索表中的所有数据时，查询语句中可以省略 select 关键字，其示例如下所示：

   ```java
   String hql="from User";
   // User 表示类名，而不是表名，因此需要区分大小写，而 from 关键字不区分大小写。
   ```

#### 领域模型

```java
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

    // setter...
    // getter...
    // toString...
}
```



#### 数据持久

```java
public interface UserDao {
    List<User> selUserByName(String name);
}



@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private HibernateTemplate hibernateTemplate;


    @Override
    public List<User> selUserByName(String name) {
        // getCurrentSession:当前 session 必须要有事务边界，且只能处理唯一
        // 的一个事务。当事务提交或者回滚后 session 自动失效

        // openSession:每次都会打开一个新的 session.加入每次使用多次。则获
        // 得的是不同 session 对象。使用完毕后我们需要手动的调用 colse 方法关闭 session
        Session session = this.hibernateTemplate.getSessionFactory()
            .getCurrentSession();
        Query query = session.createQuery("from User where name = :username");

        Query queryTemp = query.setString("username", name);
        return queryTemp.list();
    }
}
```

#### 测试代码

```java
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class UserDaoImplTest {

    @Autowired
    private UserDao userDao;

    @Test
    @Transactional
    public void testSelUserByName() {
        List<User> userList = this.userDao.selUserByName("大龙虾");
        for (User user : userList) {
            System.out.println(user);
        }
    }
}
```



### Hibernate-SQL查询

#### 本地SQL概述

本地 SQL 检索方式就是使用本地数据库的 SQL 查询语句进行查询。在 Hibernate 中，SQL 查询是通过 SQLQuery 接口表示的，该接口是 Query 接口的子接口，因此可以调用 Query 接口的方法。

使用本地 SQL 检索方式检索对象的示例代码，代码如下所示：

```java
SQLQuery sqlQuery = session.createSQLQuery("select * from t_user");
```

#### 领域模型

```java
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

    // setter...
    // getter...
    // toString...
}
```

#### 数据持久

```java
public interface UserDao {
    List<User> selUserByNameUseSQL(String name);
}


@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private HibernateTemplate hibernateTemplate;


    @Override
    public List<User> selUserByNameUseSQL(String name) {
        Session session = this.hibernateTemplate.getSessionFactory()
            .getCurrentSession();

        String sql = "select * from user where name = ?";
        NativeQuery sqlQuery = session.createSQLQuery(sql);
        Query query = sqlQuery.addEntity(User.class).setString(1, name);
        return query.list();
    }
}
```



#### 测试代码

```java
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class UserDaoImplTest {

    @Autowired
    private UserDao userDao;


    @Test
    @Transactional
    public void testSelUserByName() {
        List<User> userList = this.userDao.selUserByNameUseSQL("大龙虾");
        for (User user : userList) {
            System.out.println(user);
        }
    }
}
```



### Hibernate-QBC查询

#### QBC概述

1. `QBC`（Query By Criteria）是 Hibernate 提供的另一种检索对象的方式，它主要由 `Criteria` 接口、Criterion 接口和`Expression` 类组成，并且支持在运行时动态生成查询语句。

2. QBC 查询主要由 Criteria 接口完成，该接口由 Session 对象创建，Criterion 是 Criteria 的查询条件，在 Criteria 中提供了 `add`（Criterion criterion）方法添加查询条件。



#### 领域模型

```java
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

    // setter...
    // getter...
    // toString...
}
```



#### 数据持久

```java
public interface UserDao {
    List<User> selUserByNameUseQBC(String name);
}

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private HibernateTemplate hibernateTemplate;


    @Override
    public List<User> selUserByNameUseQBC(String name) {
        Session session = this.hibernateTemplate.getSessionFactory()
            .getCurrentSession();

        Criteria criteria = session.createCriteria(User.class);
        criteria.add(Restrictions.eq("name", name));

        return criteria.list();
    }
}
```



#### 测试代码

```java
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class UserDaoImplTest {

    @Autowired
    private UserDao userDao;


    @Test
    @Transactional
    public void testSelUserByName() {
        List<User> userList = this.userDao.selUserByNameUseQBC("大龙虾");
        for (User user : userList) {
            System.out.println(user);
        }
    }
}
```

## Hibernate-jpa

1. Spring Data JPA：Spring Data JPA 是 spring data 项目下的一个模块。提供了一套基于 JPA 标准操作数据库的简化方案。底层默认的是依赖 Hibernate JPA 来实现的。 
2. Spring Data JPA 的技术特点：我们只需要定义接口并集成 Spring Data JPA 中所提供的接 口就可以了。不需要编写接口实现类。



### 相关jar文件

1. Hibernate 必须包

   + antlr-2.7.7.jar      言转换工具，Hibernate 利用它实现 HQL 到 SQL 的转换
   + byte-buddy-1.10.21.jar
   + classmate-1.5.1.jar
   + dom4j-2.1.3.jar        dom4j XML解析器
   + FastInfoset-1.2.15.jar
   + hibernate-commons-annotations-5.1.2.Final.jar
   + hibernate-core-5.4.31.Final.jar         Hibernate 的核心包
   + istack-commons-runtime-3.0.7.jar
   + jandex-2.2.3.Final.jar
   + javassist-3.27.0-GA.jar              代码生成工具，Hibernate 利用它在运行时扩展 Java 类
   + javax.activation-api-1.2.0.jar
   + javax.persistence-api-2.2.jar
   + jaxb-api-2.3.1.jar
   + jaxb-runtime-2.3.1.jar
   + jboss-logging-3.4.1.Final.jar
   + jboss-transaction-api_1.2_spec-1.1.1.Final.jar
   + stax-ex-1.8.jar
   + txw2-2.3.1.jar

   

2. Log4j 日志文件核心 JAR

   + commons-logging-1.2.jar

   

3. 数据库连接池

   + druid-1.1.10.jar

   

4. mysql数据库驱动

   + mysql-connector-java-5.1.7-bin.jar

   

5. hibernate-entitymanager-5.4.3.Final.jar



### 常用注解的说明

+  @Entity：作用：指定当前类是实体类。



+ @Table： 作用：指定实体类和表之间的对应关系。

  + 属性： name：指定数据库表的名称    

  

+ @Id：作用：指定当前字段是主键。



+ @GeneratedValue：作用：指定主键的生成方式。

  +   属性：  strategy ：指定主键生成策略。

  

+ @Column：作用：指定实体类属性和数据库表之间的对应关系

  + 属性：

    + name：指定数据库表的列名称。
    + unique：是否唯一 
    + nullable：是否可以为空 
    + inserttable：是否可以插入 
    + updateable：是否可以更新 
    + columnDefinition: 定义建表时创建此列的DDL 
    + secondaryTable: 从表名。如果此列不建在主表上（默认建在主表），该属性定义该列所在从表的名字搭建开发环境

    

### 主键生成策略

通过 annotation（注解）来映射hibernate实体的,基于annotation的hibernate主键标识为`@Id`, 其生成规则由`@GeneratedValue`设定的.这里的@id和@GeneratedValue都是JPA的标准用法。

JPA提供的四种标准用法为TABLE,SEQUENCE,IDENTITY,AUTO。

#### IDENTITY-自动生成

```java
// IDENTITY:主键由数据库自动生成（主要是自动增长型）
@Id  
@GeneratedValue(strategy = GenerationType.IDENTITY) 
```



#### SEQUENCE-序列生成主键

```java
// SEQUENCE：根据底层数据库的序列来生成主键，条件是数据库支持序列。
@Id  
@GeneratedValue(strategy = GenerationType.SEQUENCE,generator="payablemoney_seq") 
@SequenceGenerator(name="payablemoney_seq", sequenceName="seq_payment")  

// @SequenceGenerator源码中的定义
@Target({TYPE, METHOD, FIELD})   
@Retention(RUNTIME)  
public @interface SequenceGenerator {  
    //表示该表主键生成策略的名称，它被引用在@GeneratedValue中设置的“generator”值中
    String name();  
    //属性表示生成策略用到的数据库序列名称。
    String sequenceName() default "";  
    //表示主键初识值，默认为0
    int initialValue() default 0;  
    //表示每次主键值增加的大小，例如设置1，则表示每次插入新记录后自动加1，默认为50
    int allocationSize() default 50;  
}
```



#### AUTO-程序控制

```java
// AUTO：主键由程序控制
@Id  
@GeneratedValue(strategy = GenerationType.AUTO) 
```

#### TABLE-特定的数据库表保存

```java
// AUTO：主键由程序控制
@Id  
@GeneratedValue(strategy = GenerationType.AUTO) 


// TABLE：使用一个特定的数据库表格来保存主键
@Id  
@GeneratedValue(strategy = GenerationType.TABLE, generator="payablemoney_gen")  
@TableGenerator(name = "pk_gen",  
                table="tb_generator",  
                pkColumnName="gen_name",  
                valueColumnName="gen_value",  
                pkColumnValue="PAYABLEMOENY_PK",  
                allocationSize=1  
               ) 

//@TableGenerator的定义：
@Target({TYPE, METHOD, FIELD})   
@Retention(RUNTIME)  
public @interface TableGenerator {  
    //表示该表主键生成策略的名称，它被引用在@GeneratedValue中设置的“generator”值中
    String name();  
    //表示表生成策略所持久化的表名，例如，这里表使用的是数据库中的“tb_generator”。
    String table() default "";  
    //catalog和schema具体指定表所在的目录名或是数据库名
    String catalog() default "";  
    String schema() default "";  
    //属性的值表示在持久化表中，该主键生成策略所对应键值的名称。例如在“tb_generator”中将“gen_name”作为主键的键值
    String pkColumnName() default "";  
    //属性的值表示在持久化表中，该主键当前所生成的值，它的值将会随着每次创建累加。例如，在“tb_generator”中将“gen_value”作为主键的值 
    String valueColumnName() default "";  
    //属性的值表示在持久化表中，该生成策略所对应的主键。例如在“tb_generator”表中，将“gen_name”的值为“CUSTOMER_PK”。 
    String pkColumnValue() default "";  
    //表示主键初识值，默认为0。 
    int initialValue() default 0;  
    //表示每次主键值增加的大小，例如设置成1，则表示每次创建新记录后自动加1，默认为50。
    int allocationSize() default 50;  
    UniqueConstraint[] uniqueConstraints() default {};  
} 

//这里应用表tb_generator，定义为 ：
CREATE TABLE  tb_generator (  
    id NUMBER NOT NULL,  
    gen_name VARCHAR2(255) NOT NULL,  
    gen_value NUMBER NOT NULL,  
    PRIMARY KEY(id)  
)
```



### 属性字段映射配置

```java
// 所有的注解都是使用JPA的规范提供的注解，
// 所以在导入注解包的时候，一定要导入javax.persistence下的
@Entity  // 声明实体类
@Table(name = "user")   // 建立实体类和表的映射关系
public class User implements Serializable {

    @Id  // 声明主键
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 配置主键的生成策略，自增长
    @Column(name = "id")
    private Integer id;
	
    // 指定和表中name字段的映射关系
    @Column(name = "name")
    private String name;

    // 指定和表中age字段的映射关系
    @Column(name = "age")
    private Integer age;

    // setter...
    // getter...
    // toString...
}
```



### 核心配置文件

在java工程的src路径下创建一个名为`META-INF`的文件夹，在此文件夹下创建一个名为`persistence.xml` 的配置文件

```java
<?xml version="1.0" encoding="UTF-8"?>
<persistence xmlns="http://java.sun.com/xml/ns/persistence" version="2.0">

    <!-- 需要配置persistence-unit节点
         持久化单元：
            name：持久化单元名称
            transaction-type：事务管理的方式
                    JTA：分布式事务管理
                    RESOURCE_LOCAL：本地事务管理
    -->
    <persistence-unit name="myJpa" transaction-type="RESOURCE_LOCAL">
        <!-- 配置JPA规范的服务提供商 -->
        <provider>org.hibernate.jpa.HibernatePersistenceProvider</provider>

        <!-- 可选配置：配置jpa实现方的配置信息 -->
        <properties>
            <!-- 数据库信息
              用户名，javax.persistence.jdbc.user
              密码，  javax.persistence.jdbc.password
              驱动，  javax.persistence.jdbc.driver
              数据库地址   javax.persistence.jdbc.url
          -->
            <property name="javax.persistence.jdbc.driver" value="com.mysql.jdbc.Driver"/>
            <property name="javax.persistence.jdbc.url" value="jdbc:mysql://localhost:3306/springdata?useUnicode=true&amp;characterEncoding=utf8"/>
            <property name="javax.persistence.jdbc.user" value="root"/>
            <property name="javax.persistence.jdbc.password" value="ixfosa"/>


            <!-- 配置jpa实现方(hibernate)的配置信息
                 显示sql           ：   false|true
                 自动创建数据库表    ：  hibernate.hbm2ddl.auto
                        create     : 程序运行时创建数据库表（如果有表，先删除表再创建）
                        update     ：程序运行时创建表（如果有表，不会创建表）
                        none       ：不会创建表
        
              -->
            <property name="hibernate.show_sql" value="true" />
            <property name="hibernate.format_sql" value="true" />
            <property name="hibernate.hbm2ddl.auto" value="update" />
        </properties>
    </persistence-unit>
</persistence>
```



### 的API介绍

#### Persistence对象

`Persistence` 对象主要作用是用于获取 `EntityManagerFactory`对象的 。通过调用该类的`createEntityManagerFactory`静态方法，根据配置文件中持久化单元名称创建`EntityManagerFactory`。

```java
加载配置文件创建工厂（实体管理器工厂）对象
String unitName = "myJpa";
EntityManagerFactory factory = Persistence.createEntityManagerFactory(unitName);
```



#### EntityManagerFactory

`EntityManagerFactory` 接口主要用来创建 `EntityManager` 实例

```java
//创建实体管理类
String unitName = "myJpa";
EntityManagerFactory factory = Persistence.createEntityManagerFactory(unitName);
EntityManager em = factory.createEntityManager();
```

由于 `EntityManagerFactory` 是一个线程安全的对象（即多个线程访问同一个EntityManagerFactory 对象不会有线程安全问题），并且EntityManagerFactory 的创建极其浪费资源，所以在使用JPA编程时，我们可以对EntityManagerFactory 的创建进行优化，只需要做到一个工程只存在一个EntityManagerFactory 即可



#### EntityManager

在 JPA 规范中, `EntityManager` 是完成持久化操作的核心对象。实体类作为普通 java对象，只有在调用 EntityManager 将其持久化后才会变成持久化对象。EntityManager对象在一组实体类与底层数据源之间进行 O/R 映射的管理。它可以用来管理和更新 Entity Bean, 根椐主键查找 Entity Bean, 还可以通过JPQL语句查询实体。

可以通过调用EntityManager的方法完成获取事务，以及持久化数据库的操作

方法说明：

```java
getTransaction : 获取事务对象
persist ： 保存操作
merge ： 更新操作
remove ： 删除操作
find/getReference ： 根据id查询
```



#### EntityTransaction

在 JPA 规范中, `EntityTransaction` 是完成事务操作的核心对象

```java
begin：开启事务
commit：提交事务
rollback：回滚事务
```



### 抽取工具类

```java
public final class JPAUtil {
    // JPA的实体管理器工厂：相当于Hibernate的SessionFactory
    private static EntityManagerFactory factory;

    // // 使用静态代码块赋值
    static {
        // 注意：该方法参数必须和persistence.xml中persistence-unit标签name属性取值一致
        factory = Persistence.createEntityManagerFactory("myJpa");
    }

    // 使用管理器工厂生产一个管理器对象
    public static EntityManager getEntityManager() {
        return factory.createEntityManager();
    }
}
```



### JPA进行crud

#### 保存

```java
public class SaveTest {

    /**
     * 测试jpa的保存
     *      案例：保存一个客户到数据库中
     *  Jpa的操作步骤
     *     1.加载配置文件创建工厂（实体管理器工厂）对象
     *     2.通过实体管理器工厂获取实体管理器
     *     3.获取事务对象，开启事务
     *     4.完成增删改查操作
     *     5.提交事务（回滚事务）
     *     6.释放资源
     */
    @Test
    public void testSave() {
        // 1.加载配置文件创建工厂（实体管理器工厂）对象
        EntityManagerFactory factory =
                Persistence.createEntityManagerFactory("myJpa");

        // 2.通过实体管理器工厂获取实体管理器
        EntityManager manager = factory.createEntityManager();

        // 3.获取事务对象，开启事务
        EntityTransaction tx = manager.getTransaction();
        tx.begin();

        User user = new User();
        // javax.persistence.PersistenceException: org.hibernate.PersistentObjectException:
        // 主键自增策略 @GeneratedValue(strategy=GenerationType.IDENTITY):
        // 1-设置主键自增时，必须在数据库中设置主键自增和在对应的实体类中设置对应的主键自增，只要有哪一边没有设置，就会报该异常
        // 2-在设置了主键自增了，保存数据时，自己设置了主键，则也会报PersistenceException
        // user.setId(5);
        user.setName("菊花");
        user.setAge(123);

        // 4.完成增删改查操作：保存一个客户到数据库中
        manager.persist(user);

        // 5.提交事务（回滚事务）
        tx.commit();

        //6.释放资源
        manager.close();
    }
}
```



#### 修改

```java
public class UpdateTest {

    @Test
    public void testUpdate() {

        // 1.通过工具类获取entityManager
        EntityManager manager = JPAUtil.getEntityManager();

        // 2.开启事务
        EntityTransaction transaction = manager.getTransaction();
        transaction.begin();

        // 3.更新操作
        // 3.1查询客户
        User user = manager.find(User.class, 5);
        // 3.2更新客户
        user.setName("黑菊花");
        manager.merge(user);

        // 4.提交事务
        transaction.commit();

        // 5.释放资源
        manager.close();
    }
}
```



#### 删除

```java
public class RemoveTest {

    @Test
    public void testRemove() {
        // 1.通过工具类获取entityManager
        EntityManager manager = JPAUtil.getEntityManager();

        // 2.开启事务
        EntityTransaction transaction = manager.getTransaction();
        transaction.begin();

        // 3.删除操作
        // 3.1根据id查询客户
        User user = manager.find(User.class, 5);
        // 3.2删除客户
        manager.remove(user);

        // 4.提交事务
        transaction.commit();

        // 5.释放资源
        manager.close();
    }
}
```

#### 根据id查询

```java
public class FindTest {

    @Test
    public void testFind() {

        // 1.通过工具类获取entityManager
        EntityManager manager = JPAUtil.getEntityManager();


        // 2.查找操作
        User user = manager.find(User.class, 2);
        System.out.println(user);

        // 3.释放资源
        manager.close();
    }
}
```



### JPA进行复杂查询

#### 查询全部

```java
public class FindAllTest {

    @Test
    public void testFindAll1() {
        EntityManager em = JPAUtil.getEntityManager();

        String jpql = "from User";

        TypedQuery<User> query = em.createQuery(jpql, User.class);
        List<User> resultList = query.getResultList();

        for (User user : resultList) {
            System.out.println(user);
        }
    }

    @Test
    public void testFindAll2() {
        EntityManager em = JPAUtil.getEntityManager();

        String sql = "select * from user";

        Query nativeQuery = em.createNativeQuery(sql, User.class);
        List<User> resultList = nativeQuery.getResultList();

        for (User user : resultList) {
            System.out.println(user);
        }
    }

    @Test
    public void testFindAll3() {

        EntityManager em = JPAUtil.getEntityManager();

        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<User> query = builder.createQuery(User.class);

        Root<User> root = query.from(User.class);

        Predicate predicate = builder.equal(root.get("name"), "大龙虾");

        query.where(predicate);

        TypedQuery<User> typedQuery = em.createQuery(query);

        List<User> resultList = typedQuery.getResultList();

        for (User user : resultList) {
            System.out.println(user);
        }
    }
}
```



#### 分页查询

```java
public class PagedTest {

    @Test
    public void testPaged() {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        String jpql = "from User";
        Query query = em.createQuery(jpql, User.class);

        // 起始索引
        query.setFirstResult(1);
        // 每页查询的条数
        query.setMaxResults(1);

        List<User> resultList = query.getResultList();

        for (Object user : resultList) {
            System.out.println(user);
        }
        tx.commit();
        em.close();
    }
}
```



#### 条件查询

```java
public class ConditionTest {

    @Test
    public void testCondition() {

        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        //String jpql = "from User where name = :name";
        String jpql = "from User where name like :name ";
        TypedQuery<User> query = em.createQuery(jpql, User.class);

        query.setParameter("name", "%龙%");

        List<User> resultList = query.getResultList();

        for (User user : resultList) {
            System.out.println(user);
        }
        
        tx.commit();
        em.close();
    }
}
```



#### 排序查询

```java
public class OrdersTest {
    @Test
    public void testOrders() {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        String jpql = "from User order by id desc";
        TypedQuery<User> query = em.createQuery(jpql, User.class);
        List<User> resultList = query.getResultList();
        for (User user : resultList) {
            System.out.println(user);
        }
        tx.commit();
        em.close();
    }
}
```



#### 统计查询

```java
public class CountTest {
    @Test
    public void testCount() {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        String jpql = "select count(id) from User";

        Query query = em.createQuery(jpql);

        Object result = query.getSingleResult();

        System.out.println(result);

        tx.commit();
        em.close();
    }
}
```



## Spring 整合 Hibernate JPA

### 添加相关的 jar 包

在原来的基础上添加 HIbernateJPA 相关的 jar 包

+ hibernate-entitymanager-5.4.3.Final.jar



### 配置文件

1. jdbc.properties

```properties
jdbc.url=jdbc:mysql://localhost:3306/springdata?useUnicode=true&&characterEncoding=utf8
jdbc.username=root
jdbc.password=ixfosa
jdbc.maxActive=30
```

2. applicationContext

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context" xmlns:tx="http://www.springframework.org/schema/tx"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
       http://www.springframework.org/schema/beans/spring-beans.xsd
       http://www.springframework.org/schema/context
       https://www.springframework.org/schema/context/spring-context.xsd
       http://www.springframework.org/schema/tx
       http://www.springframework.org/schema/tx/spring-tx.xsd">

    <!-- 配置读取properties文件的工具类 -->
    <context:property-placeholder location="classpath:jdbc.properties" />

    <!-- 配置 druid 数据库连接池 -->
    <bean id="dataSource" class="com.alibaba.druid.pool.DruidDataSource">
        <property name="url" value="${jdbc.url}" />
        <property name="username" value="${jdbc.username}" />
        <property name="password" value="${jdbc.password}" />
        <property name="maxActive" value="${jdbc.maxActive}" />
    </bean>

    <!-- Spring 整合 JPA 配置 EntityManagerFactory-->
    <bean id="entityManagerFactory" class="org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean">
        <property name="dataSource" ref="dataSource" />
        <property name="jpaVendorAdapter">
            <bean class="org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter">
                <!-- hibernate 相关的属性的注入 -->
                <!-- 配置数据库类型 -->
                <property name="database" value="MYSQL" />
                <!-- 正向工程 自动创建表 -->
                <property name="generateDdl" value="true"/>
                <property name="showSql" value="true"/>
            </bean>
        </property>
        <!-- 扫描实体的包 -->
        <property name="packagesToScan">
            <list>
                <value>top.ixfosa.pojo</value>
            </list>
        </property>
    </bean>

    <!-- 配置Hibernate的事务管理器 -->
    <bean id="txManager" class="org.springframework.orm.jpa.JpaTransactionManager">
        <property name="entityManagerFactory" ref="entityManagerFactory" />
    </bean>
    <!-- 配置开启注解事务处理 -->
    <tx:annotation-driven transaction-manager="txManager" />

    <!-- 配置springIOC的注解扫描 -->
    <context:component-scan base-package="top.ixfosa" />

</beans>
```

### 领域模型

```java
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

    // setter...
    // getter...
    // toString...
}
```



### 数据持久

```java
public interface UserDao {

    void insUser(User user);
    void updUser(User user);
    void delUser(User user);
    User selUserById(Integer id);

    List<User> selUserByNameUseHQL(String name);

    List<User> selUserByNameUseSQL(String name);

    List<User> selUserByNameUseQBC(String name);
}
```

```java
@Repository
public class UserDaoImpl implements UserDao {

    /**
     * PersistenceContext: JPA中的注解，，称为持久化上下文，它一般包含有当前事务范围内的
     * 被管理的实体对象(Entity)的数据。每个EntityManager，都会跟一个PersistenceContext相关联。
     * PersistenceContext中存储的是实体对象的数据，而关系数据库中存储的是记录。
     * persistencecontext中保存的对象如果做做修改和更新操作的话EntityManager都会跟踪到。
     *
     * 主要作用:就是它能够根据实体类的要求根据实体类状态的变化，能够做出不同的应对以及做持久化操作。
     */
    @PersistenceContext(name = "entityManagerFactory")
    private EntityManager entityManager;

    @Override
    public void insUser(User user) {
        this.entityManager.persist(user);
    }

    @Override
    public void updUser(User user) {
        this.entityManager.merge(user);
    }

    @Override
    public void delUser(User user) {
        User u = this.selUserById(user.getId());
        this.entityManager.remove(u);
    }

    @Override
    public User selUserById(Integer id) {
        return this.entityManager.find(User.class, id);
    }

    @Override
    public List<User> selUserByNameUseHQL(String name) {
        String hql = "from User where name = :name";
        return this.entityManager.createQuery(hql, User.class).
                setParameter("name", name)
                .getResultList();
    }

    @Override
    public List<User> selUserByNameUseSQL(String name) {
        String sql = "select * from user where name = ?";
        return this.entityManager.createNativeQuery(sql, User.class)
                .setParameter(1, name)
                .getResultList();
    }

    @Override
    public List<User> selUserByNameUseQBC(String name) {
        // CriteriaBuilder 对象：创建一个 CriteriaQuery,创建查询条件。
        CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<User> query = builder.createQuery(User.class);
        // CriteriaQuery 对象：执行查询的 Criteria 对象
        // select * from user
        Root<User> root = query.from(User.class);
        Predicate cate = builder.equal(root.get("name"), name);
        // select * from t_users where username = 大龙虾
        query.where(cate);

        // 执行查询
        TypedQuery<User> typedQuery = this.entityManager.createQuery(query);

        return typedQuery.getResultList();
    }
}

```



### 测试代码

```java
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class UserDaoImplTest {

    @Autowired
    private UserDao userDao;


    @Test
    @Transactional   // 在测试类对于事务提交方式默认的是回滚。
    @Rollback(false) // 取消自动回滚
    public void testInsUser() {
        User user = new User();
        user.setName("奥特曼");
        user.setAge(99);
        // Hibernate: insert into user (age, name) values (?, ?)
        this.userDao.insUser(user);
    }

    @Test
    @Transactional   // 在测试类对于事务提交方式默认的是回滚。
    @Rollback(false) // 取消自动回滚
    public void testUpdUser() {
        User user = new User();
        user.setId(4);
        user.setName("凹凸曼");
        user.setAge(123);
        this.userDao.updUser(user);
    }

    @Test
    @Transactional   // 在测试类对于事务提交方式默认的是回滚。
    @Rollback(false) // 取消自动回滚
    public void testDelUser() {
        User user = new User();
        user.setId(4);
        this.userDao.delUser(user);
    }

    @Test
    public void testSelUser() {
        User user = this.userDao.selUserById(2);
        System.out.println(user);
    }

    @Test
    @Transactional
    public void testSelUserByNameUserHQL() {
        List<User> userList = this.userDao.selUserByNameUseHQL("大龙虾");
        for (User user : userList) {
            System.out.println(user);
        }
    }

    @Test
    @Transactional
    public void testSelUserByNameUseSQL() {
        List<User> userList = this.userDao.selUserByNameUseSQL("大龙虾");
        for (User user : userList) {
            System.out.println(user);
        }
    }

    
    @Test
    @Transactional
    public void testSelUserByNameUseQBC() {
        List<User> userList = this.userDao.selUserByNameUseQBC("大龙虾");
        for (User user : userList) {
            System.out.println(user);
        }
    }
}
```



## Spring整合JPA

### 核心配置文件

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:tx="http://www.springframework.org/schema/tx"
       xmlns:jpa="http://www.springframework.org/schema/data/jpa" xmlns:aop="http://www.springframework.org/schema/aop"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
       http://www.springframework.org/schema/beans/spring-beans.xsd
       http://www.springframework.org/schema/context
       https://www.springframework.org/schema/context/spring-context.xsd
       http://www.springframework.org/schema/tx
       http://www.springframework.org/schema/tx/spring-tx.xsd
       http://www.springframework.org/schema/data/jpa
       https://www.springframework.org/schema/data/jpa/spring-jpa.xsd http://www.springframework.org/schema/aop http://www.springframework.org/schema/aop/spring-aop.xsd">

    <!-- 配置读取properties文件的工具类 -->
    <context:property-placeholder location="classpath:jdbc.properties" />

    <!-- 配置 druid 数据库连接池 -->
    <bean id="dataSource" class="com.alibaba.druid.pool.DruidDataSource">
        <property name="url" value="${jdbc.url}" />
        <property name="username" value="${jdbc.username}" />
        <property name="password" value="${jdbc.password}" />
        <property name="maxActive" value="${jdbc.maxActive}" />
    </bean>

    <!-- 创建entityManagerFactory对象交给spring容器管理-->
    <bean id="entityManagerFactoty" class="org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean">
        <property name="dataSource" ref="dataSource" />
        <!--配置的扫描的包（实体类所在的包） -->
        <property name="packagesToScan" value="top.ixfosa.pojo" />
        <!-- jpa的实现厂家 -->
        <property name="persistenceProvider">
            <bean class="org.hibernate.jpa.HibernatePersistenceProvider"/>
        </property>

        <!--jpa的供应商适配器 -->
        <property name="jpaVendorAdapter">
            <bean class="org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter">
                <!--配置是否自动创建数据库表 -->
                <property name="generateDdl" value="false" />
                <!--指定数据库类型 -->
                <property name="database" value="MYSQL" />
                <!--数据库方言：支持的特有语法 -->
                <property name="databasePlatform" value="org.hibernate.dialect.MySQLDialect" />
                <!--是否显示sql -->
                <property name="showSql" value="true" />
            </bean>
        </property>

        <!--jpa的方言 ：高级的特性 -->
        <property name="jpaDialect" >
            <bean class="org.springframework.orm.jpa.vendor.HibernateJpaDialect" />
        </property>
    </bean>

    <!--整合spring dataJpa-->
    <jpa:repositories base-package="top.ixfosa.dao" transaction-manager-ref="transactionManager"
                      entity-manager-factory-ref="entityManagerFactoty" ></jpa:repositories>

    <!--配置事务管理器 -->
    <bean id="transactionManager" class="org.springframework.orm.jpa.JpaTransactionManager">
        <property name="entityManagerFactory" ref="entityManagerFactoty"></property>
    </bean>

    <!-- txAdvice-->
    <tx:advice id="txAdvice" transaction-manager="transactionManager">
        <tx:attributes>
            <tx:method name="save*" propagation="REQUIRED"/>
            <tx:method name="insert*" propagation="REQUIRED"/>
            <tx:method name="update*" propagation="REQUIRED"/>
            <tx:method name="delete*" propagation="REQUIRED"/>
            <tx:method name="get*" read-only="true"/>
            <tx:method name="find*" read-only="true"/>
            <tx:method name="*" propagation="REQUIRED"/>
        </tx:attributes>
    </tx:advice>

    <!--aop-->
    <aop:config>
        <aop:pointcut id="pointcut" expression="execution(* top.ixfosa.service.*.*(..))" />
        <aop:advisor advice-ref="txAdvice" pointcut-ref="pointcut" />
    </aop:config>

    <!-- 配置包扫描-->
    <context:component-scan base-package="top.ixfosa" />
</beans>
```

### 注解配置映射关系

```java
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

    // setter...
    // getter...
    // toString...
}
```

### 基本CRUD操作

```java
/**
 * JpaRepository<实体类类型，主键类型>：用来完成基本CRUD操作
 * JpaSpecificationExecutor<实体类类型>：用于复杂查询（分页等查询操作）
 */
public interface UserDao extends JpaRepository<User, Integer> {

}
```



```java
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class UserDaoTest {

    @Autowired
    private UserDao userDao;

    @Test
    public void testSave() {
        User user = new User();
        user.setName("麻瓜");
        user.setAge(1);
        userDao.save(user);
    }

    /**
     * 修改客户：调用save(obj)方法
     *    对于save方法的解释：如果执行此方法是对象中存在id属性，
     *    即为更新操作会先根据id查询，再更新
     *    如果执行此方法中对象中不存在id属性，即为保存操作
     */
    @Test
    public void testUpdate() {
        User user = userDao.findOne(9);
        user.setName("哈哈");
        userDao.save(user);
    }

    @Test
    public void testDelete() {
        userDao.delete(9);
    }

    @Test
    public void testFindById() {
        User user = userDao.findOne(8);
        System.out.println(user);
    }
}
```

### JPA中重要接口

#### Repository

+ public interface Repository<T, ID extends Serializable> {

  ```java
  public interface Repository<T, ID extends Serializable> {
  }
  ```

#### CrudRepository

+ public interface CrudRepository<T, ID extends Serializable> extends Repository<T, ID> 

  ```java
  public interface CrudRepository<T, ID extends Serializable> extends Repository<T, ID> {
      <S extends T> S save(S var1);
  
      <S extends T> Iterable<S> save(Iterable<S> var1);
  
      T findOne(ID var1);
  
      boolean exists(ID var1);
  
      Iterable<T> findAll();
  
      Iterable<T> findAll(Iterable<ID> var1);
  
      long count();
  
      void delete(ID var1);
  
      void delete(T var1);
  
      void delete(Iterable<? extends T> var1);
  
      void deleteAll();
  }
  ```

#### PagingAndSortingRepository

+ public interface PagingAndSortingRepository<T, ID extends Serializable> extends CrudRepository<T, ID> 

  ```java
  public interface PagingAndSortingRepository<T, ID extends Serializable> extends CrudRepository<T, ID> {
      Iterable<T> findAll(Sort var1);
  
      Page<T> findAll(Pageable var1);
  }
  ```

#### JpaRepository

+ public interface JpaRepository<T, ID extends Serializable> extends PagingAndSortingRepository<T, ID>

  ```java
  public interface JpaRepository<T, ID> extends PagingAndSortingRepository<T, ID>, QueryByExampleExecutor<T> {
      
      List<T> findAll();
  
      List<T> findAll(Sort var1);
  
      List<T> findAllById(Iterable<ID> var1);
  
      <S extends T> List<S> saveAll(Iterable<S> var1);
  
      void flush();
  
      <S extends T> S saveAndFlush(S var1);
  
      <S extends T> List<S> saveAllAndFlush(Iterable<S> var1);
  
      /** @deprecated */
      @Deprecated
      default void deleteInBatch(Iterable<T> entities) {
          this.deleteAllInBatch(entities);
      }
  
      void deleteAllInBatch(Iterable<T> var1);
  
      void deleteAllByIdInBatch(Iterable<ID> var1);
  
      void deleteAllInBatch();
  
      /** @deprecated */
      @Deprecated
      T getOne(ID var1);
  
      T getById(ID var1);
  
      <S extends T> List<S> findAll(Example<S> var1);
  
      <S extends T> List<S> findAll(Example<S> var1, Sort var2);
  }
  ```

#### JpaSpecificationExecutor

+ org.springframework.data.jpa.repository.JpaSpecificationExecutor

```java
public interface JpaSpecificationExecutor<T> {
    T findOne(Specification<T> var1);

    List<T> findAll(Specification<T> var1);

    Page<T> findAll(Specification<T> var1, Pageable var2);

    List<T> findAll(Specification<T> var1, Sort var2);

    long count(Specification<T> var1);
}
```



继承结构

```java
spring-data-commons.jar:    Repository(标识接口)
                                ^
                                |
                            CrudRepository(简单的增删改查方法)
                                ^
                                |
                    PagingAndSortingRepository(基本的增删改查外，还提供了排序和分页的方法)
    							^
    							|
spring-data-jpa.jar:       JpaRepository(对父接口返回值做 适当适配)
    
    				JpaSpecificationExecutor (单独，条件查询及分页处理)         
```

#### SimpleJpaRepository



### 内部原理剖析

public class SimpleJpaRepository<T, ID extends Serializable> implements `JpaRepository<T, ID>`, `JpaSpecificationExecutor<T>` 

通过jdk生成的动态代理对象`SimpleJpaRepository`

```java
@RunWith(value = SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class Apptest {
    @PersistenceContext(name = "transactionManager")
    private EntityManager em;

    @Autowired
    private UserDao userDao;

    @Test
    public void test() {

        // org.springframework.data.jpa.repository.support.SimpleJpaRepository@30e6a763
        System.out.println(this.userDao);
        // class com.sun.proxy.$Proxy29 代理对象 是基于 JDK 的动态代理方式创建的
        System.out.println(this.userDao.getClass());

        JpaRepositoryFactory factory = new JpaRepositoryFactory(em);
        // getRepository(UsersDao.class);
        
        // 可以帮助我们为接口生成实现类。而这个实现类是 SimpleJpaRepository 的对象
        // 要求：该接口必须要是继承 Repository 接口
        // public class SimpleJpaRepository<T, ID extends Serializable> 
        // implements JpaRepository<T, ID>, JpaSpecificationExecutor<T>
        UserDao dao = factory.getRepository(UserDao.class);
        System.out.println(dao);
        System.out.println(dao.getClass());
    }
}
```

### Repository 接口

1. Repository 接口是 Spring Data JPA 中为我我们提供的所有接口中的顶层接口

2. Repository 提供了两种查询方式的支持 

   + 基于方法名称命名规则查询
   + 基于@Query 注解查询

   

#### 方法命名规则查询

按照Spring Data JPA 定义的规则，查询方法以`findBy`开头，涉及条件查询时，条件的属性用条件关键字连接，要注意的是：条件属性首字母需大写。框架在进行方法名解析时，会先把方法名多余的前缀截取掉，然后对剩下部分进行解析。

> 规则：findBy(关键字)+属性名称(属性名称的首字母大写)+查询条件(首字母大写) ...

| **Keyword**       | **Sample**                                | **JPQL**                                                     |
| ----------------- | ----------------------------------------- | ------------------------------------------------------------ |
| And               | findByLastnameAndFirstname                | …  where x.lastname = ?1 and x.firstname = ?2                |
| Or                | findByLastnameOrFirstname                 | …  where x.lastname = ?1 or x.firstname = ?2                 |
| Is,Equals         | findByFirstnameIs,  findByFirstnameEquals | …  where x.firstname = ?1                                    |
| Between           | findByStartDateBetween                    | …  where x.startDate between ?1 and ?2                       |
| LessThan          | findByAgeLessThan                         | …  where x.age < ?1                                          |
| LessThanEqual     | findByAgeLessThanEqual                    | …  where x.age ⇐ ?1                                          |
| GreaterThan       | findByAgeGreaterThan                      | …  where x.age > ?1                                          |
| GreaterThanEqual  | findByAgeGreaterThanEqual                 | …  where x.age >= ?1                                         |
| After             | findByStartDateAfter                      | …  where x.startDate > ?1                                    |
| Before            | findByStartDateBefore                     | …  where x.startDate < ?1                                    |
| IsNull            | findByAgeIsNull                           | …  where x.age is null                                       |
| IsNotNull,NotNull | findByAge(Is)NotNull                      | …  where x.age not null                                      |
| Like              | findByFirstnameLike                       | …  where x.firstname like ?1                                 |
| NotLike           | findByFirstnameNotLike                    | … where  x.firstname not like ?1                             |
| StartingWith      | findByFirstnameStartingWith               | …  where x.firstname like ?1 (parameter bound with appended %) |
| EndingWith        | findByFirstnameEndingWith                 | …  where x.firstname like ?1 (parameter bound with prepended %) |
| Containing        | findByFirstnameContaining                 | …  where x.firstname like ?1 (parameter bound wrapped in %)  |
| OrderBy           | findByAgeOrderByLastnameDesc              | …  where x.age = ?1 order by x.lastname desc                 |
| Not               | findByLastnameNot                         | …  where x.lastname <> ?1                                    |
| In                | findByAgeIn(Collection ages)              | …  where x.age in ?1                                         |
| NotIn             | findByAgeNotIn(Collection age)            | …  where x.age not in ?1                                     |
| TRUE              | findByActiveTrue()                        | …  where x.active = true                                     |
| FALSE             | findByActiveFalse()                       | …  where x.active = false                                    |
| IgnoreCase        | findByFirstnameIgnoreCase                 | …  where UPPER(x.firstame) = UPPER(?1)                       |



#### 基于方法命名规则查询

```java
public interface UserDaoRepository extends Repository<User, Integer> {
    List<User> findByNameIs(String name);

    List<User> findByNameLike(String name);

    List<User> findByNameAndAgeGreaterThan(String name, Integer age);
}
```

```java
@RunWith(value = SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class RepositorTest {

    @Autowired
    private UserDaoRepository userDao;


    @Test
    public void testRepositor() {
        List<User> userList = userDao.findByNameIs("大龙虾");
        for (User user : userList) {
            System.out.println(user);
        }

        List<User> userList1 = userDao.findByNameLike("%龙%");
        for (User user : userList1) {
            System.out.println(user);
        }

        List<User> userList2 = userDao.findByNameAndAgeGreaterThan("大龙虾", 1);
        for (User user : userList2) {
            System.out.println(user);
        }
    }
}
```



#### 基于@Query 注解的查询

##### 通过 JPQL 语句查询

`JPQL`：通过 Hibernate 的 HQL 演变过来的。他和 HQL 语法及其相似。

```java
/**
 * JpaRepository<实体类类型，主键类型>：用来完成基本CRUD操作
 * JpaSpecificationExecutor<实体类类型>：用于复杂查询（分页等查询操作）
 */

public interface UserDaoRepository extends Repository<User, Integer> {

    // 使用JPQL
    @Query("from User where name = ?")
    List<User> queryByNameUseJPQL(String name);

    @Query("from User where name like :name")
    List<User> queryByLikeNameUseJPQL(@Param("name") String name);

    @Query("from User where name = ? and age >= ?")
    List<User> queryByNameAndAgeUseJPQL(String name, Integer age);
}
```

```java
@RunWith(value = SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class RepositorTest {

    @Autowired
    private UserDaoRepository userDao;


    @Test
    public void testRepositorUseQueryJPQL() {
        List<User> userList = userDao.queryByNameUseJPQL("大龙虾");
        for (User user : userList) {
            System.out.println(user);
        }

       List<User> userList1 = userDao.queryByLikeNameUseJPQL("%龙%");
       for (User user : userList1) {
           System.out.println(user);
       }

       List<User> userList2 = userDao.queryByNameAndAgeUseJPQL("大龙虾", 1);
       for (User user : userList2) {
           System.out.println(user);
       }

        userDao.updateUserNameByIdUseJPQL("大麻瓜", 8);
    }
}
```



##### 通过 SQL 语句查询

```java
/**
 * JpaRepository<实体类类型，主键类型>：用来完成基本CRUD操作
 * JpaSpecificationExecutor<实体类类型>：用于复杂查询（分页等查询操作）
 */

public interface UserDaoRepository extends Repository<User, Integer> {

    // 使用 SQL
    @Query(value = "select * from user where name = ?", nativeQuery = true)
    List<User> queryByNameUseSQL(String name);

    @Query(value = "select * from user where name like ?", nativeQuery = true)
    List<User> queryByLikeNameUseSQL(String name);

    @Query(value = "select * from user where name = ? and age >= ?", nativeQuery = true)
    List<User> queryByNameAndAgeUseSQL(String name, Integer age);
}

```

```java
@RunWith(value = SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class RepositorTest {

    @Autowired
    private UserDaoRepository userDao;


    @Test
    public void testRepositorUseQuerySQL() {
        List<User> userList = userDao.queryByNameUseSQL("大龙虾");
        for (User user : userList) {
            System.out.println(user);
        }

        List<User> userList1 = userDao.queryByLikeNameUseSQL("%龙%");
        for (User user : userList1) {
            System.out.println(user);
        }

        List<User> userList2 = userDao.queryByNameAndAgeUseSQL("大龙虾", 1);
        for (User user : userList2) {
            System.out.println(user);
        }
    }
}
```



##### @Query 注解完成数据更新

```java
/**
 * JpaRepository<实体类类型，主键类型>：用来完成基本CRUD操作
 * JpaSpecificationExecutor<实体类类型>：用于复杂查询（分页等查询操作）
 */

public interface UserDaoRepository extends Repository<User, Integer> {

    // 使用JPQL
    @Query("from User where name = ? and age >= ?")
    List<User> queryByNameAndAgeUseJPQL(String name, Integer age);
}
```

```java
@RunWith(value = SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class RepositorTest {

    @Autowired
    private UserDaoRepository userDao;


    @Test
    public void testRepositorUseQueryJPQL() {
        userDao.updateUserNameByIdUseJPQL("大麻瓜", 8);
    }
}
```



### CrudRepository接口

```java
public interface UserDaoCrudRepository extends CrudRepository<User, Integer> {
    
}
```

```java
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class CrudRepositoryTest {

    @Autowired
    private UserDaoCrudRepository userDao;

    // 添加单条数据
    @Test
    public void testSave() {
        User user = new User();
        user.setName("fo");
        user.setAge(23);
        this.userDao.save(user);
    }

    // 批量添加数据
    @Test
    public void testSave2() {
        User user1 = new User();
        user1.setName("周杰伦");
        user1.setAge(30);

        User user2 = new User();
        user2.setName("林俊杰");
        user2.setAge(30);

        List<User> users = new ArrayList<>();
        users.add(user1);
        users.add(user2);
        this.userDao.save(users);
    }

    // 根据 ID 查询单条数据
    @Test
    public void testFindOne() {
        User user = this.userDao.findOne(2);
        System.out.println(user);
    }

    // 查询全部数据
    @Test
    public void testFindAll() {
        List<User> userList = (List<User>) this.userDao.findAll();
        for (User user : userList) {
            System.out.println(user);
        }
    }

    // 删除数据
    @Test
    public void testDelete() {

        // 删除单条数据
        this.userDao.delete(10);

        // 删除多条数据
        User user1 = new User();
        user1.setId(6);
        User user2 = new User();
        user2.setId(7);
        User user3 = new User();
        user3.setId(8);
        ArrayList<User> users = new ArrayList<>();
        users.add(user1);
        users.add(user2);
        users.add(user3);
        this.userDao.delete(users);
    }

    // 更新数据 方式一
    @Test
    public void testUpdate1() {
        User user = this.userDao.findOne(13);
        user.setName("王菲");
        this.userDao.save(user);
    }

    // 更新数据 方式二
    @Test
    @Transactional
    @Rollback(false)
    public void testUpdate2() {
        User user = this.userDao.findOne(14);
        // 持久化状态的
        user.setName("李小龙");
    }
}
```



### PagingAndSortingRepository

```java
public interface UserDaoPagingAndSortingRepository extends PagingAndSortingRepository<User, Integer> {
    
}
```

```java
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class PagingAndSortingRepositoryTest {
    @Autowired
    private UserDaoPagingAndSortingRepository userDao;

    // 分页处理
    @Test
    public void testPaged() {
        // page:当前页的索引。注意索引都是从 0 开始的。
        int page = 0;
        // size:每页显示 3
        int size = 2;

        PageRequest pageRequest = new PageRequest(page, size);

        Page<User> userPage = this.userDao.findAll(pageRequest);
        for (User user : userPage) {
            System.out.println(user);
        }

        System.out.println("数据的总条数：" + userPage.getTotalElements());
        System.out.println("总页数：" + userPage.getTotalPages());

        List<User> userList = userPage.getContent();
        for (User user : userList) {
            System.out.println(user);
        }
    }

    // 对单列做排序处理
    @Test
    public void testOrder1() {
        // Sort:该对象封装了排序规则以及指定的排序字段(对象的属性来表示)
        // direction:排序规则
        // properties:指定做排序的属性
        Sort sort = new Sort(Sort.Direction.DESC, "age");
        List<User> userList = (List<User>) this.userDao.findAll(sort);
        for (User user : userList) {
            System.out.println(user);
        }
    }

    // 多列的排序处理
    @Test
    public void testOrder2() {
        Sort.Order order1 = new Sort.Order(Sort.Direction.DESC, "age");
        Sort.Order order2 = new Sort.Order(Sort.Direction.DESC, "id");
        Sort sort = new Sort(order1, order2);

        List<User> userList = (List<User>) this.userDao.findAll(sort);

        for (User user : userList) {
            System.out.println(user);
        }
    }
}
```



### JpaRepository接口

`JpaRepository` 接口是开发时使用的最多的接口。其特点是可以帮助我们将其他接口 的方法的返回值做适配处理。可以使得在开发时更方便的使用这些方法。

```java
public interface UserDao extends JpaRepository<User, Integer> {

}
```

```java
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class JpaRepositoryTest {

    @Autowired
    private UserDaoJpaRepository userDao;

    @Test
    public void test() {
        List<User> userList = this.userDao.findAll();
        for (User user : userList) {
            System.out.println(user);
        }
    }
}
```



### JpaSpecificationExecutor接口

在查询某个实体的时候，给定的条件是不固定的，这时就需要动态构建相应的查询语句，在Spring Data JPA中可以通过`JpaSpecificationExecutor`接口查询。相比JPQL,其优势是类型安全,更加的面向对象。

> 对于JpaSpecificationExecutor，这个接口基本是围绕着Specification接口来定义的。可以简单的理解为，`Specification`构造的就是查询条件。

`JpaSpecificationExecutor`：多条件查询，并且支持分页与排序

```java
//JpaSpecificationExecutor<T>:不能单独使用，需要配合着 jpa 中的其他接口一起使用
public interface UserDaoJpaSpecificationExecutor extends JpaRepository<User, Integer>,JpaSpecificationExecutor<User> {

}
```



#### 单条件查询

```java
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class JpaSpecificationExecutorTest {

    @Autowired
    private UserDaoJpaSpecificationExecutor userDao;

    // 单条件查询
    @Test
    public void test1() {

        /**
         * @return Predicate:定义了查询条件
         * @param Root<T> root:根对象。封装了查询条件的对象
         * @param CriteriaQuery<?> query:定义了一个基本的查询.一般不使用
         * @param CriteriaBuilder cb:创建一个查询条件
         */
        Specification<User> spec = (root, criteriaQuery, criteriaBuilder) -> {
            Predicate predicate = criteriaBuilder.equal(root.get("name"), "大龙虾");
            return predicate;
        };
        List<User> userList = this.userDao.findAll(spec);
        for (User user : userList) {
            System.out.println(user);
        }
    }
}
```



#### 多条件查询

```java
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class JpaSpecificationExecutorTest {

    @Autowired
    private UserDaoJpaSpecificationExecutor userDao;



    // 多条件查询 --- 给定查询条件方式一
    @Test
    public void test2() {
        Specification<User> spec = (root, criteriaQuery, criteriaBuilder) -> {
            ArrayList<Predicate> list = new ArrayList<>();
            list.add(criteriaBuilder.like(root.get("name"), "%龙%"));
            list.add(criteriaBuilder.ge(root.get("age"), 1));
            // 此时条件之间是没有任何关系的。
            Predicate[] predicates = new Predicate[list.size()];
            return criteriaBuilder.and(list.toArray(predicates));
        };
        List<User> userList = this.userDao.findAll(spec);
        for (User user : userList) {
            System.out.println(user);
        }
    }

    // 多条件查询 --- 给定查询条件方式二
    @Test
    public void test3() {
        Specification<User> spec = (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.and(criteriaBuilder.like(root.get("name"), "%龙%"),
                criteriaBuilder.ge(root.get("age"), 1));

        List<User> userList = this.userDao.findAll(spec);
        for (User user : userList) {
            System.out.println(user);
        }
    }
}
```

#### 分页排序

```java
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class JpaSpecificationExecutorTest {

    @Autowired
    private UserDaoJpaSpecificationExecutor userDao;


    // 分页
    // 需求：查询age >= 10，并且做分页处理
    @Test
    public void test4() {
        Specification<User> spec = (root, criteriaQuery, criteriaBuilder) ->
                // criteriaBuilder.ge(root.get("age").as(Integer.class), 10);
                criteriaBuilder.ge(root.get("age"), 10);

        PageRequest pageRequest = new PageRequest(0, 2);
        Page<User> userPage = this.userDao.findAll(pageRequest);

        for (User user : userPage) {
            System.out.println(user);
        }
    }
    
    // 排序
    @Test
    public void test5() {
        Specification<User> spec = (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.like(root.get("name").as(String.class), "%龙%");
                // criteriaBuilder.like(root.get("name"), "%龙%");

        Sort sort = new Sort(Sort.Direction.DESC, "id");

        List<User> userList = this.userDao.findAll(spec, sort);

        for (User user : userList) {
            System.out.println(user);
        }
    }
    
    // 分页与排序
    @Test
    public void test6() {
        Specification<User> spec = (root, criteriaQuery, criteriaBuilder) ->
                // criteriaBuilder.ge(root.get("age").as(Integer.class), 10);
                criteriaBuilder.ge(root.get("age"), 10);

        Sort.Order order1 = new Sort.Order(Sort.Direction.DESC, "id");
        Sort.Order order2 = new Sort.Order(Sort.Direction.ASC, "age");
        Sort sort = new Sort(order2, order1);

        List<User> userList = this.userDao.findAll(spec, sort);

        for (User user : userList) {
            System.out.println(user);
        }
    }
}
```



### 自定义 Repository 接口

1. 创建接口

   ```java
   public interface UserRepository {
       User findUserById(Integer id);
   }
   ```

2. 使用接口

   ```java
   public interface UserDao extends JpaRepository<User, Integer>, UserRepository {
   
   }
   ```

3. 创建接口实现类

   ```java
   public class UserDaoImpl implements UserRepository {
   
       @PersistenceContext(name = "entityManagerFactoty")
       private EntityManager em;
   
       @Override
       public User findUserById(Integer id) {
           return em.find(User.class, id);
       }
   }
   ```

4. 测试代码

   ```java
   @RunWith(SpringJUnit4ClassRunner.class)
   @ContextConfiguration("classpath:applicationContext.xml")
   public class UserDaoTest {
   
       @Autowired
       private UserDao userDao;
   
   
       @Test
       public void test() {
           System.out.println(this.userDao);
           User user = userDao.findUserById(2);
           System.out.println(user);
       }
   }
   ```





### 一对一关联关系

需求：用户与角色的一对一的关联关系 

+ 用户：一方 
+ 角色：一方

#### 映射的注解说明

1. `OneToOne`

   + 作用：作用：建立一对多的关系映射

   + 属性：

     + mappedBy：指定从表实体类中引用主表对象的名称。定义在the owned side(`被拥有方的`)

       > `mappedBy`跟`JoinColumn/JoinTable`总是处于互斥的一方，可以理解为正是由于拥有方中有被拥有方的字段存在，拥有方才拥有了被拥有方。mappedBy这方定义JoinColumn/JoinTable总是失效的，不会建立对应的字段或者表。

       

     + fetch：指定是否采用延迟加载。

       + FetchType.EAGER：急加载。

         > 被定义的属性所关联的数据会马上加载到内存，所以session是否关闭都可以取出来。一般`@ManyToOne`为EAGER。

       + FetchType.LAZY：懒加载

         > 被定义的属性所关联的数据不会马上从数据库中加载，在同一个session中，什么时候要用，就什么时候取(再次访问数据库)。但是在session关闭后，就不能再取了，会报异常：org.hibernate.LazyInitializationException。解决这个异常就需要将LAZY转为EAGER。一般@OneToMany为LAZY
         >
         > 异常`org.hibernate.LazyInitializationException`的解决方法还有另一个，就是在实体类上面加上注解`Proxy(lazy=false)`将默认懒加载关掉

         

     

     + targetEntity：Class类型的属性。定义关系类的类型，默认是该成员属性对应的类类型

     

     + cascade：该属性定义类和类之间的级联关系。定义的级联关系将被容器视为对当前类对象及其关联类对象采取相同的操作， 而且这种关系是递归调用的。

       + 级联操作：指操作一个对象同时操作它的关联对象
       + `CascadeType.MERGE`  级联更新
       + `CascadeType.PERSIST` 级联保存
       + `CascadeType.REFRESH` 级联刷新
       + `CascadeType.REMOVE` 级联删除：
       + `CascadeType.ALL`   包含所有

       

     + optional ：关联是否可选。如果设置为false，则必须始终存在非空关系。

   

#### User实体及映射配置

```java
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
    
    // setter...
    // getter...
    // toString...
}

```

#### Role实体及映射配置

```java
package top.ixfosa.pojo.one2one;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by ixfosa on 2021/5/27 16:42
 */
@Entity
@Table(name = "role")
public class Role implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @OneToOne(mappedBy = "role", cascade = CascadeType.PERSIST)
    private User user;
	
    // setter...
    // getter...
    // toString...
}

```

#### 定义接口

```java
public interface RoleDao extends JpaRepository<Role, Integer> {
    
}

public interface UserDao extends JpaRepository<User, Integer> {
    
}
```

#### 测试代码

```java
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class Ono2OneTest {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    // 添加用户同时添加角色
    @Test
    public void test1() {
        // 创建角色
        Role role = new Role();
        role.setName("admin");

        // 创建用户
        User user = new User();
        user.setName("zhong");
        user.setAge(21);

        // 建立关系
        role.setUser(user);
        user.setRole(role);

        // 保存数据
        // this.userDao.save(user);
        this.roleDao.save(role);
    }
    
        // 根据用户 ID 查询用户，同时查询用户角色
    @Test
    public void test2() {
        User user = this.userDao.findOne(21);
        System.out.println(user);

        Role role = this.roleDao.findOne(2);
        System.out.println(role.getUser());
    }
}
```



#### StackOverflowError

1. 问题：java.lang.StackOverflowError

2. 原因：

   两个类的`toString`反复调用导致的堆栈溢出。在两类中分别重写了 toString 方法,导致查询加载时两类在互相调用对方的toString,形成递归,造成堆栈溢出。

3. 解决：
   1. 在 toString 方法中`任意一方`去除打印的对方信息。
   2. 如果用的是lombok，则不要用`@Data`或者`@ToString`注解。将@Data注解替换成@Getter和@Setter，然后手动重写toString()方法。
   
   





### 一对多关联关系

需求：角色与用户的一对多的关联关系 

+ 用户：多方 
+ 角色：一方

#### 映射的注解说明

1. `@OneToMany`

   + 作用：建立一对多的关系映射

   + 属性：

     + targetEntityClass：指定多的多方的类的字节码

     + mappedBy：指定从表实体类中引用主表对象的名称。

     +  cascade：指定要使用的级联操作

     + fetch：指定是否采用延迟加载

     +  orphanRemoval：是否使用孤儿删除

       > 孤儿删除（孤子删除），只有在`一对多`的环境下才有孤儿删除 。在一对多的关系中,可以将一的一方认为是父方，将多的一方认为是子方。
       >
       > 孤儿删除:在解除了父子关系的时候，将子方记录就直接删除

     

2. `@ManyToOne`

   + 作用：建立多对一的关系
   + 属性：
     + targetEntityClass：指定一的一方实体类字节码
     + cascade：指定要使用的级联操作
     + fetch：指定是否采用延迟加载
     + optional：关联是否可选。如果设置为false，则必须始终存在非空关系。

   > 只有在双向关联时才会使用`mappedBy`属性，只有`OneToOne`、`OneToMany`、`ManyToMany`上才有mappedBy属性，`ManyToOne`不存在该属性。

   

3. `@JoinColumn`

   + 作用：用于定义主键字段和外键字段的对应关系。

   +  属性：

     + name：指定外键字段的名称
     + referencedColumnName：指定引用主表的主键字段名称
     + unique：是否唯一。默认值不唯一
     + nullable：是否允许为空。默认值允许。
     + insertable：是否允许插入。默认值允许。
     + updatable：是否允许更新。默认值允许。
     + columnDefinition：列的定义信息。

     

#### User实体及映射配置

```java
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

    // @ManyToOne : 配置多对一关系
    @ManyToOne(cascade = CascadeType.PERSIST)
    // @JoinColumn：就是维护一个外键
    @JoinColumn(name = "role_id")
    private Role role;
	
    // setter...
    // getter...
    // toString...
}

```



#### Role实体及映射配置

```java
@Entity
@Table(name = "role")
public class Role implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    /**
     * 使用注解的形式配置多表关系
     *      1.声明关系
     *          @OneToMany : 配置一对多关系
     *              targetEntity ：对方对象的字节码对象
     *      2.配置外键
     *              @JoinColumn : 配置外键
     *                  name：外键字段名称
     *                  referencedColumnName：参照的主表的主键字段名称
     *
     *  * 在用户实体类上（一的一方）添加了外键了配置，所以对于用户而言，也具备了维护外键的作用
     */
    /**
     * 放弃外键维护权:  mappedBy：对方配置关系的属性名称
     *
     * cascade : 配置级联（可以配置到设置多表的映射关系的注解上）
     *      CascadeType.all         : 所有
     *                  MERGE       ：更新
     *                  PERSIST     ：保存
     *                  REMOVE      ：删除
     *
     * fetch : 配置关联对象的加载方式
     *          EAGER   ：立即加载
     *          LAZY    ：延迟加载
                注意在多方中的一方的配置没有指定加载方式，而且一方中的多方是懒加载，
                因此在获取一方的时候一方是获取到了，但是再获取多方的时候session已经关闭了，
                这时候会获取不到多方信息
     */
    @OneToMany(mappedBy = "role", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private Set<User> users = new HashSet<>();

		
    // setter...
    // getter...
    // toString...
}

```



#### 定义接口

```java
public interface RoleDao extends JpaRepository<Role, Integer> {
    
}

public interface UserDao extends JpaRepository<User, Integer> {
    
}
```



#### 测试代码

````java
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class Ono2ManyTest {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Test
    public void test1() {
        Role role = new Role();
        role.setName("瓜皮");

        User user = new User();
        user.setName("林逸");
        user.setAge(18);

        User user2 = new User();
        user2.setName("haha");
        user2.setAge(18);

        role.getUsers().add(user);
        role.getUsers().add(user2);

        user.setRole(role);
        user2.setRole(role);

        // this.userDao.save(user);
        this.roleDao.save(role);
    }

    @Test
    public void test2() {
        User user = this.userDao.findOne(22);
        System.out.println(user);
    }

    @Test
    public void test3() {
        Role role = this.roleDao.findOne(8);
        System.out.println(role.getUsers());
    }
}
````





### 多对多关联关系

多对多的表关系建立靠的是`中间表`，其中用户表和中间表的关系是一对多，角色表和中间表的关系也是一对多

需求：角色与用户的多对多的关联关系 

+ 用户：多方 
+ 角色：多方 

#### 映射的注解说明

1. `@ManyToMany`
   + 作用：用于映射多对多关系
   + 属性：
     + cascade：配置级联操作。
     + fetch：配置是否采用延迟加载。
     + targetEntity：配置目标的实体类。映射多对多的时候不用写。

 

2. `@JoinTable`
   + 作用：针对中间表的配置
   + 属性：
     + nam：配置中间表的名称
     + `joinColumns`：中间表的外键字段关联`当前`实体类所对应表的主键字段                         
     + `inverseJoinColumn`：中间表的外键字段关联`对方表`的主键字段

​       

3. `@JoinColumn`
   + 作用：用于定义主键字段和外键字段的对应关系。
   +  属性：
     + name：指定外键字段的名称
     + referencedColumnName：指定引用主表的主键字段名称
     + unique：是否唯一。默认值不唯一
     + nullable：是否允许为空。默认值允许。
     +  insertable：是否允许插入。默认值允许。
     + updatable：是否允许更新。默认值允许。
     + columnDefinition：列的定义信息。



#### User实体及映射配置

```java
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

	// setter...
    // getter...
    // toString...
}
```





#### Role实体及映射配置

```java
@Entity
@Table(name = "role")
public class Role implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    // 放弃对中间表的维护权，解决保存中主键冲突的问题
    @ManyToMany(mappedBy = "roles", cascade = CascadeType.ALL)
    private Set<User> users = new HashSet<>();

    // setter...
    // getter...
    // toString...
}

```



#### 定义接口

```java
public interface RoleDao extends JpaRepository<Role, Integer> {
    
}

public interface UserDao extends JpaRepository<User, Integer> {
    
}
```



#### 测试代码

```java
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class Many2ManyTest {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;
    @Test
    public void test1() {
        // 创建对象
        User user1 = new User();
        user1.setName("用户1");
        Role role1 = new Role();
        role1.setName("角色1");

        User user2 = new User();
        user2.setName("用户2");


        // 建立关联关系
        user1.getRole().add(role1);
        user2.getRole().add(role1);
        role1.getUser().add(user1);
        role1.getUser().add(user2);

        // 保存,多对多放弃维护权：被动的一方放弃
        // this.roleDao.save(role);
        // this.userDao.save(user);

        // 保存一个用户的同时保存用户的关联角色
        this.userDao.save(user1);
        this.userDao.save(user2);
    }

    // 删除用户，同时删除他的关联对象
    // 在多对多的删除时，双向级联删除根本不能配置
    // 如果配了的话，如果数据之间有相互引用关系，可能会清空所有数据
    @Test
    public void test2() {
        User user = this.userDao.findOne(33);
        this.userDao.delete(user);
    }
}
```



## Sprinf-Data-Redis

### jar 文件

+ aopalliance.jar
+ aspectjrt.jar
+ aspectjweaver.jar
+ commons-logging-1.1.1.jar
+ commons-pool2-2.3.jar
+ jedis-2.7.2.jar
+ spring-aop-4.2.0.RELEASE.jar
+ spring-aspects-4.2.0.RELEASE.jar
+ spring-beans-4.2.0.RELEASE.jar
+ spring-context-4.2.0.RELEASE.jar
+ spring-core-4.2.0.RELEASE.jar
+ spring-data-redis-1.6.0.RELEASE.jar
+ spring-expression-4.2.0.RELEASE.jar
+ spring-test-4.2.0.RELEASE.jar
+ spring-tx-4.2.0.RELEASE.jar

### 配置文件

```properties
redis.pool.maxtTotal=20
redis.pool.maxIdle=10
redis.pool.minIdle=5

redis.hostname=127.0.0.1
redis.port=6379
```

```java
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
       http://www.springframework.org/schema/beans/spring-beans.xsd
       http://www.springframework.org/schema/context
       http://www.springframework.org/schema/context/spring-context.xsd">

    <!-- 配置读取properties文件的工具类 -->
    <context:property-placeholder location="classpath:redis.properties" />

    <!-- Jedis连接池 -->
    <bean id="poolConfig" class="redis.clients.jedis.JedisPoolConfig">
        <property name="maxIdle" value="${redis.pool.maxIdle}" />
        <property name="maxTotal" value="${redis.pool.maxtTotal}" />
        <property name="minIdle" value="${redis.pool.minIdle}" />
    </bean>

    <!-- Jedis连接工厂:创建Jedis对象的工厂 -->
    <bean id="jedisConnectionFactory" class="org.springframework.data.redis.connection.jedis.JedisConnectionFactory">
        <!-- IP地址 -->
        <property name="hostName" value="${redis.hostname}" />
        <!-- 端口 -->
        <property name="port" value="${redis.port}" />
        <!-- 连接池 -->
        <property name="poolConfig" ref="poolConfig" />
    </bean>

    <!-- Redis模板对象:是SpringDataRedis提供的用户操作Redis的对象 -->
    <bean id="redisTemplate" class="org.springframework.data.redis.core.RedisTemplate">
        <property name="connectionFactory" ref="jedisConnectionFactory" />

        <!-- 默认的序列化器：序列化器就是根据规则将存储的数据中的key与value做字符串的序列化处理 -->
        <!-- keySerializer、valueSerializer：对应的是Redis中的String类型 -->
        <!-- hashKeySerializer、hashValueSerializer：对应的是Redis中的Hash类型 -->
        <property name="keySerializer">
            <bean class="org.springframework.data.redis.serializer.StringRedisSerializer" />
        </property>
        <property name="valueSerializer">
            <bean class="org.springframework.data.redis.serializer.StringRedisSerializer" />
        </property>
    </bean>
</beans>
```

### 测试代码

```java
存储实体对象@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class AppTest {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    // 添加键值对
    @Test
    public void test1() {
        this.redisTemplate.opsForValue().set("key", "value");
    }

    // 获取redis中的数据
    @Test
    public void test2() {
        String key = (String) this.redisTemplate.opsForValue().get("key");
        System.out.println(key);
    }
}
```



### 存储实体对象

```java
public class User implements Serializable {
    private Integer id;
    private String name;
    private Integer age;
    // setter...
    // getter...
    // toString...
}
```

```java
package top.ixfosa.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import top.ixfosa.pojo.User;

/**
 * Created by ixfosa on 2021/5/28 11:09
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class AppTest {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    // 存储实体对象
    @Test
    public void testSaveObj() {
        User user = new User();
        user.setId(1);
        user.setName("大龙虾");
        user.setAge(22);

        // 更换序列化器
        this.redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
        this.redisTemplate.opsForValue().set("user", user);
    }

    // 读取实体对象
    // 要缓存的JavaBean必须实现Serializable接口，因为Spring会将对象先序列化再存入 Redis
    @Test
    public void testReadObj() {
        // 更换序列化器
        this.redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
        User user = (User) this.redisTemplate.opsForValue().get("user");
        System.out.println(user);
    }
}
```



### JSON格式存储实体对象

导入 jackson 包

+ jackson-annotations-2.8.0.jar
+ jackson-core-2.8.10.jar
+ jackson-databind-2.8.10.jar

```java
public class User implements Serializable {
    private Integer id;
    private String name;
    private Integer age;
    // setter...
    // getter...
    // toString...
}
```

```java
package top.ixfosa.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import top.ixfosa.pojo.User;

/**
 * Created by ixfosa on 2021/5/28 11:09
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class AppTest {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    // 保存 以 JSON 的格式存储实体对象
    @Test
    public void testSaveJSON() {
        User user = new User();
        user.setId(1);
        user.setName("大龙虾");
        user.setAge(22);

        this.redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<>(User.class));
        this.redisTemplate.opsForValue().set("userJson", user);
    }

    // 读取 以 JSON 的格式存储实体对象
    @Test
    public void testReadJSON() {
        this.redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<>(User.class));
        User userJson = (User) this.redisTemplate.opsForValue().get("userJson");
        System.out.println(userJson);
    }
}
```

