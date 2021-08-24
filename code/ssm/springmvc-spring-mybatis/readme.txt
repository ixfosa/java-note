
SSM： SpringMVC + Spring + MyBatis.

SpringMVC:视图层，界面层，负责接收请求，显示处理结果的。
Spring：业务层，管理service，dao，工具类对象的。
MyBatis：持久层， 访问数据库的

用户发起请求--SpringMVC接收--Spring中的Service对象--MyBatis处理数据

SSM整合也叫做SSI (IBatis也就是mybatis的前身)， 整合中有容器。
    1.第一个容器SpringMVC容器， 管理Controller控制器对象的。
    2.第二个容器Spring容器，管理Service，Dao,工具类对象的

把使用的对象交给合适的容器创建，管理。 把Controller还有web开发的相关对象
交给springmvc容器， 这些web用的对象写在springmvc配置文件中


service，dao对象定义在spring的配置文件中，让spring管理这些对象。

springmvc容器和spring容器是有关系的，关系已经确定好了
springmvc容器是spring容器的子容器， 类似java中的继承。 子可以访问父的内容
在子容器中的Controller可以访问父容器中的Service对象， 就可以实现controller使用service对象

