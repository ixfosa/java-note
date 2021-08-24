-- sale 销售表
CREATE TABLE `sale` (
     `id` int(11) NOT NULL ,
     `gid` int(11) NOT NULL,
     `nums` int(11) DEFAULT NULL,
     PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- goods 商品表
CREATE TABLE `goods` (
     `id` int(11) NOT NULL ,
     `name` char(100) DEFAULT NULL,
     `nums` int(11) DEFAULT NULL,
     `price` float DEFAULT NULL,
     PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;




定义了五个事务隔离级别常量
    这些常量均是以 ISOLATION_开头。即形如 ISOLATION_XXX。
    ➢ DEFAULT：采用 DB 默认的事务隔离级别。MySql 的默认为 REPEATABLE_READ； Oracle
        默认为 READ_COMMITTED。
    ➢ READ_UNCOMMITTED：读未提交。未解决任何并发问题。
    ➢ READ_COMMITTED：读已提交。解决脏读，存在不可重复读与幻读。
    ➢ REPEATABLE_READ：可重复读。解决脏读、不可重复读，存在幻读
    ➢ SERIALIZABLE：串行化。不存在并发问题。


定义了七个事务传播行为常量(掌握)
    所谓事务传播行为是指，处于不同事务中的方法在相互调用时，执行期间事务的维护情
    况。如，A 事务中的方法 doSome()调用 B 事务中的方法 doOther()，在调用执行期间事务的
    维护情况，就称为事务传播行为。事务传播行为是加在方法上的。
        事务传播行为常量都是以 PROPAGATION_ 开头，形如 PROPAGATION_XXX。
        PROPAGATION_REQUIRED
        PROPAGATION_REQUIRES_NEW
        PROPAGATION_SUPPORTS
        PROPAGATION_MANDATORY
        PROPAGATION_NESTED
        PROPAGATION_NEVER
        PROPAGATION_NOT_SUPPORTED


PROPAGATION_REQUIRED：
    指定的方法必须在事务内执行。若当前存在事务，就加入到当前事务中；若当前没有事
    务，则创建一个新事务。这种传播行为是最常见的选择，也是 Spring 默认的事务传播行为。
    如该传播行为加在 doOther()方法上。若 doSome()方法在调用 doOther()方法时就是在事
    务内运行的，则 doOther()方法的执行也加入到该事务内执行。若 doSome()方法在调用
    doOther()方法时没有在事务内执行，则 doOther()方法会创建一个事务，并在其中执行。


PROPAGATION_SUPPORTS
    指定的方法支持当前事务，但若当前没有事务，也可以以非事务方式执行。


PROPAGATION_REQUIRES_NEW
    总是新建一个事务，若当前存在事务，就将当前事务挂起，直到新事务执行完毕。


定义了默认事务超时时限
    常量 TIMEOUT_DEFAULT 定义了事务底层默认的超时时限，sql 语句的执行时长。
    注意，事务的超时时限起作用的条件比较多，且超时的时间计算点较复杂。所以，该
    值一般就使用默认值即可。