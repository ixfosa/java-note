di的实现有两种：
    1.在spring的配置文件中， 使用标签和属性完成，叫做基于XML的di实现
    2.使用spring中的注解，完成属性赋值， 叫做基于注解的id实现

注入分类
    bean 实例在调用无参构造器创建对象后，就要对 bean 对象的属性进行初始化。
    初始化是由容器自动完成的，称为注入。
    根据注入方式的不同，常用的有两类：set 注入、构造注入。

set 注入
    set 注入也叫设值注入是指，通过 setter 方法传入被调用者的实例。这种注入方式简单、
    直观，因而在 Spring 的依赖注入中大量使用。

构造注入
    构造注入是指，在构造调用者实例的同时，完成被调用者的实例化。即，使用构造器设
    置依赖关系。
