使用 Spring 的事务注解管理事务
    通过@Transactional 注解方式，可将事务织入到相应 public 方法中，实现事务管理。

@Transactional 的所有可选属性如下所示：
    ➢ propagation：用于设置事务传播属性。该属性类型为 Propagation 枚举，默认值为
        Propagation.REQUIRED。

    ➢ isolation：用于设置事务的隔离级别。该属性类型为 Isolation 枚举，默认值为
        Isolation.DEFAULT。

    ➢ readOnly：用于设置该方法对数据库的操作是否是只读的。该属性为 boolean，默认值
        为 false。

    ➢ timeout：用于设置本操作与数据库连接的超时时限。单位为秒，类型为 int，默认值为
        -1，即没有时限
        。
    ➢ rollbackFor：指定需要回滚的异常类。类型为 Class[]，默认值为空数组。当然，
        若只有一个异常类时，可以不使用数组。

    ➢ rollbackForClassName：指定需要回滚的异常类类名。类型为 String[]，
        默认值为空数组。当然，若只有一个异常类时，可以不使用数组。

    ➢ noRollbackFor：指定不需要回滚的异常类。类型为 Class[]，默认值为空数组。
        当然，若只有一个异常类时，可以不使用数组。

    ➢ noRollbackForClassName：指定不需要回滚的异常类类名。类型为 String[]，
        默认值为空 数组。当然，若只有一个异常类时，可以不使用数组。


    需要注意的是，@Transactional 若用在方法上，只能用于 public 方法上。对于其他非 public
方法，如果加上了注解@Transactional，虽然 Spring 不会报错，但不会将指定事务织入到该
方法中。因为 Spring 会忽略掉所有非 public 方法上的@Transaction 注解。


若@Transaction 注解在类上，则表示该类上所有的方法均将在执行时织入事务。

