## Spring MVC 的工作流程

SpringMVC 中重要组件

1. `DispatcherServlet` : 前端控制器,接收所有请求(如果配置/不包 含 jsp)
2. `HandlerMapping`: 解析请求格式的.判断希望要执行哪个具体 的方法.
3. `HandlerAdapter`: 负责调用具体的方法
4. `ViewResovler`:视图解析器.解析结果,准备跳转到具体的物理视图



Spring MVC 框架主要由 DispatcherServlet、处理器映射、控制器、视图解析器、视图组成，其工作原理如图所示

![SpringMVC工作原理图](images\SpringMVC工作原理图.png)

Spring MVC 的工作流程如下：

1. 客户端请求提交到 DispatcherServlet。
2. 由 DispatcherServlet 控制器寻找一个或多个 HandlerMapping，找到处理请求的 Controller。
3. DispatcherServlet 将请求提交到 Controller。
4. Controller 调用业务逻辑处理后返回 ModelAndView。
5. DispatcherServlet 寻找一个或多个 ViewResolver 视图解析器，找到 ModelAndView 指定的视图。
6. 视图负责将结果显示到客户端。



## SpringMVC 环境搭建

### 所需jar文件

+ commons-logging-1.2.jar
+ servlet-api.jar
+ spring-aop-5.1.3.RELEASE.jar
+ spring-beans-5.1.3.RELEASE.jar
+ spring-context-5.1.3.RELEASE.jar
+ spring-core-5.1.3.RELEASE.jar
+ spring-expression-5.1.3.RELEASE.jar
+ spring-web-5.1.3.RELEASE.jar
+ spring-webmvc-5.1.3.RELEASE.jar



### 以xml的方式

1. 在 web.xml 中配置前端控制器 DispatcherServlet

   ```xml
   <?xml version="1.0" encoding="UTF-8"?>
   <web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
            xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
            xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd"
            version="4.0">
   
       <!-- 部署 DispatcherServlet -->
       <servlet>
           <servlet-name>springmvc</servlet-name>
           <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
           <!-- 修改配置文件路径和名称 -->
           <init-param>
               <param-name>contextConfigLocation</param-name>
               <param-value>classpath:springmvc.xml</param-value>
           </init-param>
           <!-- 表示容器再启动时立即加载servlet -->
           <load-on-startup>1</load-on-startup>
       </servlet>
       
       <servlet-mapping>
           <servlet-name>springmvc</servlet-name>
           <!-- 处理所有URL -->
           <url-pattern>/</url-pattern>
       </servlet-mapping>
   </web-app>
   ```

   > 如果不配置`<init-param>`会在 /WEB-INF/`<servlet-name>-servlet.xml`

   

2. 在 src 下新建 springmvc.xml

   ```xml
   <?xml version="1.0" encoding="UTF-8"?>
   <beans xmlns="http://www.springframework.org/schema/beans"
          xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
          xsi:schemaLocation="
           http://www.springframework.org/schema/beans
           http://www.springframework.org/schema/beans/spring-beans.xsd">
   
       <!--控制器类-->
       <!--<bean id="hello" class="com.ixfosa.controller.DemoController" />-->
       
       <!--<bean class="org.springframework.web.servlet.handler.SimpleUrlHandlerMapping">
           <property name="urlMap">
               <map>
                   <entry key="hello" value-ref="hello" />
               </map>
           </property>
       </bean>-->
       
       <!-- 控制器类，映射到"/hello" -->
       <!-- 以这种方式可以不配置SimpleUrlHandlerMapping -->
       <bean name="/hello" class="com.ixfosa.controller.DemoController" />
   </beans>
   ```

3. 编写控制器类

   ```java
   public class DemoController implements Controller {
       @Override
       public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
           
           System.out.println("Hello SpringMVC!!!");
           
           // web/hello.jsp
           ModelAndView modelAndView = new ModelAndView("hello.jsp");
           return modelAndView;
       }
   }
   ```

### 以注解的方式

1. 在 web.xml 中配置前端控制器 DispatcherServlet

   ```xml
   <?xml version="1.0" encoding="UTF-8"?>
   <web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
            xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
            xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd"
            version="4.0">
       <servlet>
           <servlet-name>springmvc</servlet-name>
           <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
           <init-param>
               <param-name>contextConfigLocation</param-name>
               <param-value>classpath:springmvc.xml</param-value>
           </init-param>
           <load-on-startup>1</load-on-startup>
       </servlet>
   
       <servlet-mapping>
           <servlet-name>springmvc</servlet-name>
           <url-pattern>/</url-pattern>
       </servlet-mapping>
   </web-app>
   ```

2. 在 src 下新建 springmvc.xml

   ```xml
   <?xml version="1.0" encoding="UTF-8"?>
   <beans xmlns="http://www.springframework.org/schema/beans"
          xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
          xmlns:context="http://www.springframework.org/schema/context"
          xmlns:mvc="http://www.springframework.org/schema/mvc"
          xsi:schemaLocation="
           http://www.springframework.org/schema/beans
           http://www.springframework.org/schema/beans/spring-beans.xsd
           http://www.springframework.org/schema/context
           http://www.springframework.org/schema/context/spring-context.xsd
           http://www.springframework.org/schema/mvc
           http://www.springframework.org/schema/mvc/spring-mvc.xsd">
   
       <!-- 扫描注解 -->
       <context:component-scan base-package="com.ixfosa.controller" />
   
       <!-- 注解驱动 -->
       <!-- org.springframework.web.servlet.mvc.annotation.DefaultAnnotationHandlerMapping -->
       <!-- org.springframework.web.servlet.mvc.annotation.AnnotationMethodHandlerAdapter -->
       <mvc:annotation-driven />
   
       <bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
           <!--前缀-->
           <property name="prefix" value="/" />
           <!--后缀-->
           <property name="suffix" value=".jsp" />
       </bean>
   </beans>
   ```

3. 编写控制器类

   ```java
   // @Controller
   /*
       在 Spring MVC 中使用 org.springframework.stereotype.Controller 注解类型声明某类的实例是一个控制器。
       在 Spring MVC 中使用扫描机制找到应用中所有基于注解的控制器类，所以，为了让控制器类被 Spring MVC 框架扫描到，需要在配置文件中声明 spring-context，并使用 <context：component-scan/> 元素指定控制器类的基本包（请确保所有控制器类都在基本包及其子包下）。*/
   
   @Controller
   public class DemoController {
       
       // RequestMapping 注解类型
       /*
       	使用 org.springframework.web.bind.annotation.RequestMapping 注解类型将请求与处理方法一一对应。
       	注解的 value 属性将请求 URI 映射到方法，value 属性是 RequestMapping 注解的默认属性，如果只有一个 value 属性，则可以省略该属性。*/
       
     
       // @RequestMapping(value = "hello")
       @RequestMapping("hello")
       public String hello() {
           System.out.println("hello ixfosa!!!");
   
           // 配置了视图解析器
           // InternalResourceViewResolver
           return "hello";
           
           // return "hello.jsp";  // 没有配置 InternalResourceViewResolver
           // 默认都以 forward 的方式
       }
       
   // 请求处理方法常见的返回类型
   /*
       最常见的返回类型就是代表逻辑视图名称的 String 类型，例如前面教程中的请求处理方法。除了 String 类型以外，还有 ModelAndView、Model、View 以及其他任意的 Java 类型。*/
   }
   ```



## 参数获取

把内容写到方法(HandlerMethod)参数中，SpringMVC 只要有这个内 容，注入内容

### 字符编码过滤器

```xml
<!-- 字符编码过滤器 -->
<filter>
    <filter-name>encoding</filter-name>
    <filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
    <init-param>
        <param-name>encoding</param-name>
        <param-value>utf-8</param-value>
     </init-param>
 </filter>

<filter-mapping>
    <filter-name>encoding</filter-name>
        <url-pattern>/*</url-pattern>
</filter-mapping>
```



web.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd"
         version="4.0">

    <servlet>
        <servlet-name>ixfosa</servlet-name>
        <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
        <init-param>
            <param-name>contextConfigLocation</param-name>
            <param-value>classpath:springmvc.xml</param-value>
        </init-param>
    </servlet>

    <servlet-mapping>
        <servlet-name>ixfosa</servlet-name>
        <url-pattern>/</url-pattern>
    </servlet-mapping>
    
    <!-- 字符编码过滤器 -->
    <filter>
        <filter-name>encoding</filter-name>
        <filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
        <init-param>
            <param-name>encoding</param-name>
            <param-value>utf-8</param-value>
        </init-param>
    </filter>

    <filter-mapping>
        <filter-name>encoding</filter-name>
        <url-pattern>/*</url-pattern>
    </filter-mapping>
</web-app>
```

springmvc.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:mvc="http://www.springframework.org/schema/mvc"
       xsi:schemaLocation="
        http://www.springframework.org/schema/beans
        http://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/context
        http://www.springframework.org/schema/context/spring-context.xsd
        http://www.springframework.org/schema/mvc
        http://www.springframework.org/schema/mvc/spring-mvc.xsd">

    <!-- 扫描注解 -->
    <context:component-scan base-package="com.ixfosa.controller" />

    <!-- 注解驱动 -->
    <mvc:annotation-driven />

    <bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
        <property name="prefix" value="/" />
        <property name="suffix" value=".jsp" />
    </bean>
</beans>
```



### 基本数据类型参数

```jsp
<form action="demo1" method="post">
    学号：<input type="number" name="id" />
    用户名：<input type="text" name="username" />
    密码：<input type="password" name="password" />
    性别：<input type="radio" name="sex" value="男" />男
    <input type="radio" name="sex" value="女"/>女
    <input type="submit" value="submit" />
</form>
```

```java
@Controller
public class DemoController {

    @RequestMapping("demo1")
    					// @RequestParam(required = true) 果强制要求必须有某个参数
    public String demo1(@RequestParam(required = true) int id,
        				String username,
                        // 如果请求参数名和方法参数名不对应使用@RequestParam()
                        @RequestParam("password") String pwd,
                        // 如果方法参数是基本数据类型(不是封装类)可以通过
                        // @RequestParam(defaultValue = "") 设置默认值
                        // 防止没有参数时 500
                        @RequestParam(defaultValue = "男") String sex) {
        				
        System.out.println("username: " +username);
        System.out.println("pwd: " + pwd);
        System.out.println("sex: " + sex);
        return "hello";
    }
}
```

### 对象类型参数

请求参数名和对象中属性名对应(get/set 方法)

```jsp
<form action="demo2" method="post">
    学号：<input type="number" name="id" />
    用户名：<input type="text" name="username" />
    密码：<input type="password" name="password" />
    性别：<input type="radio" name="sex" value="男" />男
        <input type="radio" name="sex" value="女"/>女
        <input type="submit" value="submit" />
</form>
```

```java
package com.ixfosa.controller;

import com.ixfosa.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class DemoController {
    
    @RequestMapping("demo2")
    public String demo2(User user) {
        System.out.println(user);
        return "hello";
    }   
}

// bean
public class User {
    private int id;
    private String username;
    private String password;
    private String sex;
    
    // setter,getter...
}
```

### 多个同名参数的获取方式

复选框传递的参数就是多个同名参数

1. HandlerMethod参数为`List`时

   

```jsp
<form action="demo3" method="post">
    学号：<input type="number" name="id" />
    用户名：<input type="text" name="username" />
    密码：<input type="password" name="password" />
    性别：<input type="radio" name="sex" value="男" />男
    <input type="radio" name="sex" value="女"/>女<br />
    爱好：<input type="checkbox" name="hobby[]" value="吃">吃
    <input type="checkbox" name="hobby[]" value="喝">喝
    <input type="checkbox" name="hobby[]" value="拉">拉
    <input type="checkbox" name="hobby[]" value="撒">撒
    <input type="submit" value="submit" />
</form>
```

```java
public class User3 {
    private int id;
    private String username;
    private String password;
    private String sex;
}


@RequestMapping("demo3")
public String demo3(User3 user3, @RequestParam("hobby[]") List<String> hobby) {
    System.out.println(user3);
    System.out.println(hobby);
    return "hello";
}
```

 

----

2. 对象中维护 `List `时

```jsp
<form action="demo4" method="post">
    学号：<input type="number" name="id" />
    用户名：<input type="text" name="username" />
    密码：<input type="password" name="password" />
    性别：<input type="radio" name="sex" value="男" />男
    	<input type="radio" name="sex" value="女"/>女<br />
    爱好：<input type="checkbox" name="hobby" value="吃">吃
        <input type="checkbox" name="hobby" value="喝">喝
        <input type="checkbox" name="hobby" value="拉">拉
        <input type="checkbox" name="hobby" value="撒">撒
        <input type="submit" value="submit" />
</form>
```

```java
public class User4 {
    private int id;
    private String username;
    private String password;
    private String sex;
    private List<String> hobby;
}

@RequestMapping("demo4")
public String demo4(User4 user4) {
    System.out.println(user4);
    return "hello";
}
```



---

3. HandlerMethod参数为 `Array` 时

```jsp
<form action="demo5" method="post">
    学号：<input type="number" name="id" />
    用户名：<input type="text" name="username" />
    密码：<input type="password" name="password" />
    性别：<input type="radio" name="sex" value="男" />男
   		 <input type="radio" name="sex" value="女"/>女<br />
    爱好：<input type="checkbox" name="hobby" value="吃">吃
        <input type="checkbox" name="hobby" value="喝">喝
        <input type="checkbox" name="hobby" value="拉">拉
        <input type="checkbox" name="hobby" value="撒">撒
        <input type="submit" value="submit" />
</form>
```

```java
public class User5 {
    private int id;
    private String username;
    private String password;
    private String sex;
}

@RequestMapping("demo5")
public String demo5(User5 user5, String[] hobby) {
    System.out.println(user5);
    System.out.println(Arrays.toString(hobby));
    return "hello";
}
```



---

4. 对象中维护 `Array` 时

```jsp
<form action="demo6" method="post">
    学号：<input type="number" name="id" />
    用户名：<input type="text" name="username" />
    密码：<input type="password" name="password" />
    性别：<input type="radio" name="sex" value="男" />男
    	<input type="radio" name="sex" value="女"/>女<br />
    爱好：<input type="checkbox" name="hobby" value="吃">吃
        <input type="checkbox" name="hobby" value="喝">喝
        <input type="checkbox" name="hobby" value="拉">拉
        <input type="checkbox" name="hobby" value="撒">撒
        <input type="submit" value="submit" />
</form>
```

```java
public class User6 {
    private int id;
    private String username;
    private String password;
    private String sex;
    private String[] hobby;
}

@RequestMapping("demo6")
public String demo6(User6 user6) {
    System.out.println(user6);
    return "hello";
}
```

### 对象.属性 格式

```jsp
<form action="demo7" method="post">
    学号：<input type="number" name="user4.id" />
    用户名：<input type="text" name="user4.username" />
    密码：<input type="password" name="user4.password" />
    性别：<input type="radio" name="user4.sex" value="男" />男
    	<input type="radio" name="user4.sex" value="女"/>女<br />
    爱好：<input type="checkbox" name="user4.hobby" value="吃">吃
        <input type="checkbox" name="user4.hobby" value="喝">喝
        <input type="checkbox" name="user4.hobby" value="拉">拉
        <input type="checkbox" name="user4.hobby" value="撒">撒
        <input type="submit" value="submit" />
</form>
```

```java
public class User7 {
    private User4 user4;
}

public class User4 {
    private int id;
    private String username;
    private String password;
    private String sex;
    private List<String> hobby;
}

@RequestMapping("demo7")
public String demo7(User7 user7) {
    System.out.println(user7.getUser4());
    return "hello";
}
```

### 集合对象类型参数

```java
<form action="demo8" method="post">
      学号：<input type="number" name="user4[0].id" />
      用户名：<input type="text" name="user4[0].username" />
      密码：<input type="password" name="user4[0].password" />
      性别：<input type="radio" name="user4[0].sex" value="男" />男
           <input type="radio" name="user4[0].sex" value="女"/>女<br />
      爱好：<input type="checkbox" name="user4[0].hobby" value="吃">吃
           <input type="checkbox" name="user4[0].hobby" value="喝">喝
           <input type="checkbox" name="user4[0].hobby" value="拉">拉
           <input type="checkbox" name="user4[0].hobby" value="撒">撒<br>

        学号：<input type="number" name="user4[1].id" />
        用户名：<input type="text" name="user4[1].username" />
        密码：<input type="password" name="user4[1].password" />
        性别：<input type="radio" name="user4[1].sex" value="男" />男
            <input type="radio" name="user4[1].sex" value="女"/>女<br />
        爱好：<input type="checkbox" name="user4[1].hobby" value="吃">吃
            <input type="checkbox" name="user4[1].hobby" value="喝">喝
            <input type="checkbox" name="user4[1].hobby" value="拉">拉
            <input type="checkbox" name="user4[1].hobby" value="撒">撒<br>

      <input type="submit" value="submit" />
</form>
```

```java
public class User8 {
    private List<User4> user4;
}

public class User4 {
    private int id;
    private String username;
    private String password;
    private String sex;
    private List<String> hobby;
}

@RequestMapping("demo8")
public String demo8(User8 user8) {
    System.out.println(user8.getUser4());
    return "hello";
}
```

### 数组对象类型参数

```jsp
<form action="demo8" method="post">
      学号：<input type="number" name="user4[0].id" />
      用户名：<input type="text" name="user4[0].username" />
      密码：<input type="password" name="user4[0].password" />
      性别：<input type="radio" name="user4[0].sex" value="男" />男
           <input type="radio" name="user4[0].sex" value="女"/>女<br />
      爱好：<input type="checkbox" name="user4[0].hobby" value="吃">吃
           <input type="checkbox" name="user4[0].hobby" value="喝">喝
           <input type="checkbox" name="user4[0].hobby" value="拉">拉
           <input type="checkbox" name="user4[0].hobby" value="撒">撒<br>

        学号：<input type="number" name="user4[1].id" />
        用户名：<input type="text" name="user4[1].username" />
        密码：<input type="password" name="user4[1].password" />
        性别：<input type="radio" name="user4[1].sex" value="男" />男
            <input type="radio" name="user4[1].sex" value="女"/>女<br />
        爱好：<input type="checkbox" name="user4[1].hobby" value="吃">吃
            <input type="checkbox" name="user4[1].hobby" value="喝">喝
            <input type="checkbox" name="user4[1].hobby" value="拉">拉
            <input type="checkbox" name="user4[1].hobby" value="撒">撒<br>

      <input type="submit" value="submit" />
</form>
```

```java
public class User9 {
    private User4[] user4;
}

public class User4 {
    private int id;
    private String username;
    private String password;
    private String sex;
    private List<String> hobby;
}

@RequestMapping("demo9")
public String demo9(User9 user9) {
    System.out.println(Arrays.toString(user9.getUser4()));
    return "hello";
}
```

### restful 传值方式

```java
<a href="demo10?id=98&name=ixfosa">传统链接带参</a><br>
<a href="demo11/98/ixfosa">rustful风格</a>
```

```java
@RequestMapping("demo10")
public String demo10(int id, String name) {
    System.out.println("id: " + id + " " + "name: " + name);
    return "hello";
}


// 在@RequestMapping 中一定要和请求格式对应
// {名称} 中名称自定义名称
// @PathVariable 获取@RequestMapping 中内容,默认按照方法参数名称去寻找
@RequestMapping("demo11/{id}/{name}")
public String demo11(@PathVariable int id, @PathVariable("name") String username) {
    System.out.println("id: " + id + " " + "username: " + username);
    return "hello";
}
```



## 跳转方式

SpringMVC 默认跳转方式`请求转发`

跳转方式：设置返回值字符串内容

1. 添加 redirect:资源路径 重定向
2. 添加 forward:资源路径 或省略forward: 转发

```java
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:mvc="http://www.springframework.org/schema/mvc"
       xsi:schemaLocation="
        http://www.springframework.org/schema/beans
        http://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/context
        http://www.springframework.org/schema/context/spring-context.xsd
        http://www.springframework.org/schema/mvc
        http://www.springframework.org/schema/mvc/spring-mvc.xsd">
    <context:component-scan base-package="com.ixfosa.controller" />

    <mvc:annotation-driven />

    <bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
        <property name="prefix" value="/WEB-INF/" />
        <property name="suffix" value=".jsp" />
    </bean>
</beans>
```

### 转发（forward）

客户浏览器发送 http 请求，Web 服务器接受此请求，调用内部的一个方法在容器内部完成请求处理和转发动作，将目标资源发送给客户；在这里转发的路径必须是同一个 Web 容器下的 URL，其不能转向到其他的 Web 路径上，中间传递的是自己的容器内的 request。

```java
@Controller
@RequestMapping("hello")
public class HelloController {

        @RequestMapping("hellolong")
    public String helloLong() {
        
        System.out.println("hello long...");

        // 转发（默认）走视图解析器
        // return "hello";

        // 明确指出了 forward，redirect 就不走视图解析器
        return "forward:/WEB-INF/hello.jsp";     // 转发（默认）

    }
}
```

### 重定向（redirect）

客户浏览器发送 http 请求，Web 服务器接受后发送 302 状态码响应及对应新的 location 给客户浏览器，客户浏览器发现是 302 响应，则自动再发送一个新的 http 请求，请求 URL 是新的 location 地址，服务器根据此请求寻找资源并发送给客户。

```java
@Controller
@RequestMapping("hello")
public class HelloController {

    @RequestMapping("hellolong")
    public String helloLong() {
        System.out.println("hello long...");

        // 转发（默认）走视图解析器
        // return "hello";

        // 明确指出了 forward，redirect 就不走视图解析器
        return "forward:/WEB-INF/hello.jsp";     // 转发（默认）

    }

    @RequestMapping("hellozhong")
    public String helloZhong() {

        System.out.println("hello zhong...");

        // 重定向是客户端的，而转发是服务端内部的。
        // 重定向是让客户端去访问重定向的地址，而WEB-INF下的文件是不能通过外部访问的！
        // return "redirect:/WEB-INF/hello.jsp";

        // 间接跳转
        return "redirect:/hello/hellolong";
    }
}

```



## 数据响应

1. 在方法上只有`@RequestMapping` 时,无论方法返回值是什么认为需 要跳转
2. 在方法上添加`@ResponseBody`(恒不跳转)
   1. 如果返回值满足 key-value 形式(对象或 map)
      + 把响应头设置为 `application/json;charset=utf-8`
      + 把转换后的内容输出流的形式响应给客户端
   2. 如果返回值不满足 key-value,例如返回值为 String
      + `把相应头设置为 text/html`
      + 把方法返回值以流的形式直接输出.
      + 3 如果返回值包含中文,出现中文乱码

带入jar

+ commons-logging-1.2.jar
+ jackson-annotations-2.9.8.jar
+ jackson-core-2.9.8.jar
+ jackson-databind-2.9.8.jar
+ servlet-api.jar
+ spring-aop-5.1.3.RELEASE.jar
+ spring-beans-5.1.3.RELEASE.jar
+ spring-context-5.1.3.RELEASE.jar

+ spring-core-5.1.3.RELEASE.jar
+ spring-expression-5.1.3.RELEASE.jar
+ spring-web-5.1.3.RELEASE.jar
+ spring-webmvc-5.1.3.RELEASE.jar

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:mvc="http://www.springframework.org/schema/mvc"
       xsi:schemaLocation="
        http://www.springframework.org/schema/beans
        http://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/context
        http://www.springframework.org/schema/context/spring-context.xsd
        http://www.springframework.org/schema/mvc
        http://www.springframework.org/schema/mvc/spring-mvc.xsd">
    
    <context:component-scan base-package="com.ixfosa.controller" />

    <mvc:annotation-driven />
</beans>
```

```java
public class User {
    private int id;
    private String name;
    private int age;
    private char gender;
}
```

```java
@Controller
public class HelloController {
    // produces 表示响应头中 Content-Type 取值.
    @RequestMapping(value = "demo1", produces = "text/html;charset=utf-8")
    @ResponseBody
    public String demo1() {
        System.out.println("demo1...");
        return "小佛";
    }

    @RequestMapping(value = "demo2")
    @ResponseBody
    public void demo2(HttpServletResponse response) throws IOException {
        System.out.println("demo2...");
        // produces = "text/html;charset=utf-8" 无效
        response.setHeader("content-type", "text/html;charset=utf-8");
        PrintWriter writer = response.getWriter();
        writer.write("大龙虾。。。");
        writer.flush();
        writer.close();
    }

    @RequestMapping(value = "demo3", produces = "application/json;charset=utf-8")
    @ResponseBody
    public Object demo3() {
        System.out.println("demo3...");
        User user = new User(1, "大龙虾", 22, '女');
        return user;
    }

    @RequestMapping(value = "demo4", produces = "application/json;charset=utf-8")
    @ResponseBody
    public Object demo4() {
        User user1 = new User(1, "大龙虾", 22, '女');
        User user2 = new User(1, "大龙虾", 22, '女');
        User user3 = new User(1, "大龙虾", 22, '女');
        List<User> list = new ArrayList<>();
        list.add(user1);
        list.add(user2);
        list.add(user2);
        return list;
    }

    @RequestMapping(value = "demo5")
    @ResponseBody
    public Object demo5() {
        int[] ints = {1, 2, 3};
        return ints;
    }

    @RequestMapping(value = "demo6")
    @ResponseBody
    public Object demo6() {
        User user1 = new User(1, "大龙虾", 22, '女');
        User user2 = new User(1, "大龙虾", 22, '女');
        User user3 = new User(1, "大龙虾", 22, '女');
        Map<String, User> map = new HashMap<>();
        map.put("user1", user1);
        map.put("user2", user2);
        map.put("user3", user3);
        return map;
    }
}
```



## 类型,格式转换器

### 内置的类型转换器

在 Spring MVC 框架中，对于常用的数据类型，开发者无须创建自己的类型转换器，因为 Spring MVC 框架有许多内置的类型转换器用于完成常用的类型转换。Spring MVC 框架提供的内置类型转换包括以下几种类型。

1. 标量转换器

| 名称                           | 作用                                                         |
| ------------------------------ | ------------------------------------------------------------ |
| StringToBooleanConverter       | String 到 boolean 类型转换                                   |
| ObjectToStringConverter        | Object 到 String 转换，调用 toString 方法转换                |
| StringToNumberConverterFactory | String 到数字转换（例如 Integer、Long 等）                   |
| NumberToNumberConverterFactory | 数字子类型（基本类型）到数字类型（包装类型）转换             |
| StringToCharacterConverter     | String 到 Character 转换，取字符串中的第一个字符             |
| NumberToCharacterConverter     | 数字子类型到 Character 转换                                  |
| CharacterToNumberFactory       | Character 到数字子类型转换                                   |
| StringToEnumConverterFactory   | String 到枚举类型转换，通过 Enum.valueOf 将字符串转换为需要的枚举类型 |
| EnumToStringConverter          | 枚举类型到 String 转换，返回枚举对象的 name 值               |
| StringToLocaleConverter        | String 到 java.util.Locale 转换                              |
| PropertiesToStringConverter    | java.util.Properties 到 String 转换，默认通过 ISO-8859-1 解码 |
| StringToPropertiesConverter    | String 到 java.util.Properties 转换，默认使用 ISO-8859-1 编码 |

2. 集合、数组相关转换器

| 名称                            | 作用                                                         |
| ------------------------------- | ------------------------------------------------------------ |
| ArrayToCollectionConverter      | 任意数组到任意集合（List、Set）转换                          |
| CollectionToArrayConverter      | 任意集合到任意数组转换                                       |
| ArrayToArrayConverter           | 任意数组到任意数组转换                                       |
| CollectionToCollectionConverter | 集合之间的类型转换                                           |
| MapToMapConverter               | Map之间的类型转换                                            |
| ArrayToStringConverter          | 任意数组到 String 转换                                       |
| StringToArrayConverter          | 字符串到数组的转换，默认通过“，”分割，且去除字符串两边的空格（trim） |
| ArrayToObjectConverter          | 任意数组到 Object 的转换，如果目标类型和源类型兼容，直接返回源对象；否则返回数组的第一个元素并进行类型转换 |
| ObjectToArrayConverter          | Object 到单元素数组转换                                      |
| CollectionToStringConverter     | 任意集合（List、Set）到 String 转换                          |
| StringToCollectionConverter     | String 到集合（List、Set）转换，默认通过“，”分割，且去除字符串两边的空格（trim） |
| CollectionToObjectConverter     | 任意集合到任意 Object 的转换，如果目标类型和源类型兼容，直接返回源对象；否则返回集合的第一个元素并进行类型转换 |
| ObjectToCollectionConverter     | Object 到单元素集合的类型转换                                |



> 类型转换是在`视图与控制器`相互传递数据时发生的。Spring MVC 框架对于基本类型（例如 int、long、float、double、boolean 以及 char 等）已经做好了基本类型转换。
>
> 注意：在使用内置类型转换器时，请求参数输入值与接收参数类型要兼容，否则会报 400 错误。请求参数类型与接收参数类型不兼容问题需要学习输入校验后才可解决。

### 自定义类型转换器(Converter)

当 Spring MVC 框架内置的类型转换器不能满足需求时，开发者可以开发自己的类型转换器。

实现步骤：

+ 创建实体类。

  ```java
  public class User {
      private int id;
      private String name;
      private Date birthday;
  }
  ```

  

- 创建控制器类。

  ```java
  @Controller
  public class TypeController {
  
      @RequestMapping(value = "typeConverter", produces = "application/json;charset=utf-8")
      @ResponseBody
      public Object typeConverter(User user) {
          User u = new User(user.getId(), user.getName(), user.getBirthday());
          return u;
      }
  }
  ```

  

- 创建自定义类型转换器类。

  定义类型转换器类需要实现 `Converter<S,T>` 接口，重写 convert(S) 接口方法。

  ```java
  public class StringToDateConverter implements Converter<String, Date> {
  
      @Override
      public Date convert(String s) {
  
          if ((s == null)) {
              throw new RuntimeException("请传入数据");
          }
  
          DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
          try {
              return dateFormat.parse(s);
          } catch (ParseException e) {
              throw new RuntimeException("数据类型转换错误");
          }
      }
  }
  ```

  

- 注册类型转换器。

  ```xml
  <?xml version="1.0" encoding="UTF-8"?>
  <beans xmlns="http://www.springframework.org/schema/beans"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xmlns:context="http://www.springframework.org/schema/context"
         xmlns:mvc="http://www.springframework.org/schema/mvc"
         xsi:schemaLocation="
          http://www.springframework.org/schema/beans
          http://www.springframework.org/schema/beans/spring-beans.xsd
          http://www.springframework.org/schema/context
          http://www.springframework.org/schema/context/spring-context.xsd
          http://www.springframework.org/schema/mvc
          http://www.springframework.org/schema/mvc/spring-mvc.xsd">
      <context:component-scan base-package="com.ixfosa.controller" />
  
      <mvc:annotation-driven />
      
      
      <!-- 配置注解驱动,使用自定义日期转换器 -->
      <mvc:annotation-driven conversion-service="stringToDateConverter"/>
      <!--org.springframework.context.support.ConversionServiceFactoryBean-->
      <bean id="stringToDateConverter" class="org.springframework.format.support.FormattingConversionServiceFactoryBean">
          <property name="converters">
              <set>
                  <bean class="com.ixfosa.typeconverter.StringToDateConverter" />
              </set>
          </property>
      </bean>
  
  </beans>
  ```

  

- 创建相关视图。

  ```jsp
  <form action="typeConverter" method="post">
      学号：<input type="number" name="id">
      用户名：<input type="text" name="name" />
      生日：<input type="date" name="birthday" />
      <input type="submit" value="submit" />
  </form>
  ```



### 内置的格式化转换器

Spring MVC框架的 `Formatter<T>` 与` Converter<S，T>` 一样，也是一个可以将一种数据类型转换成另一种数据类型的接口。不同的是，Formatter<T> 的源数据类型必须是 String 类型，而 Converter<S，T> 的源数据类型是任意数据类型。

在 Web 应用中由 HTTP 发送的请求数据到控制器中都是以 String 类型获取，因此在 Web 应用中选择 Formatter<T> 比选择 Converter<S，T> 更加合理。

Spring MVC 提供了几个内置的格式化转换器，具体如下。

- NumberFormatter：实现 Number 与 String 之间的解析与格式化。

- CurrencyFormatter：实现 Number 与 String 之间的解析与格式化（带货币符号）。

- PercentFormatter：实现 Number 与 String 之间的解析与格式化（带百分数符号）。

- DateFormatter：实现 Date 与 String 之间的解析与格式化。

  

### 数据格式化(Formatter)

自定义格式化转换器就是编写一个实现` org.springframework.format.Formatter` 接口的 Java 类。该接口声明如下：

```java
public interface Formatter<T>  // T 表示由字符串转换的目标数据类型。
```

该接口有 parse 和 print 两个接口方法，自定义格式化转换器类必须覆盖它们。

```java
// parse 方法的功能是利用指定的 Locale 将一个 String 类型转换成目标类型
public T parse(String s,java.util.Locale locale)
    
// print 方法与之相反，用于返回目标对象的字符串表示。
public String print(T object,java.util.Locale locale)
```

实现步骤：

+ 创建实体类。

  ```java
  public class User {
      private int id;
      private String name;
      private Date birthday;
  }
  ```

- 创建控制器类；

  ```java
  @Controller
  public class FormatCoteroller {
  
      @RequestMapping(value = "format", produces = "application/json;charset=utf-8")
      @ResponseBody
      public Object format(User user) {
          return new User(user.getId(), user.getName(), user.getBirthday());
      }
  }
  ```

  

- 创建自定义格式化转换器类；

  ```java
  public class StringToDateFormatter implements Formatter<Date> {
      SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
      @Override
      public Date parse(String s, Locale locale) throws ParseException {
          return dateFormat.parse(s);
      }
  
      @Override
      public String print(Date date, Locale locale) {
          return dateFormat.format(date);
      }
  }
  ```

  

- 注册格式化转换器；

  ```xml
  <?xml version="1.0" encoding="UTF-8"?>
  <beans xmlns="http://www.springframework.org/schema/beans"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xmlns:context="http://www.springframework.org/schema/context"
         xmlns:mvc="http://www.springframework.org/schema/mvc"
         xsi:schemaLocation="
          http://www.springframework.org/schema/beans
          http://www.springframework.org/schema/beans/spring-beans.xsd
          http://www.springframework.org/schema/context
          http://www.springframework.org/schema/context/spring-context.xsd
          http://www.springframework.org/schema/mvc
          http://www.springframework.org/schema/mvc/spring-mvc.xsd">
      <context:component-scan base-package="com.ixfosa.controller" />
  
      <mvc:annotation-driven />
  
      <!--注册MyFormatter-->
      <bean id="formattingConversion" class="org.springframework.format.support.FormattingConversionServiceFactoryBean">
          <property name="formatters">
              <bean class="com.ixfosa.format.StringToDateFormatter" />
          </property>
      </bean>
  </beans>
  ```

  

- 创建相关视图。

  ```jsp
  <form action="format" method="post">
      学号：<input type="number" name="id">
      用户名：<input type="text" name="name" />
      生日：<input type="date" name="birthday" />
      <input type="submit" value="submit" />
  </form>
  ```



## 作用域传值

### JSP 九大内置对象复习

+ `out`对象：用于向客户端、浏览器输出数据。

  + 类型：PrintWriter
  + 获取：response.getWri ter()

+ `request`对象：封装了来自客户端、浏览器的各种信息。

  + 类型：HttpSevletRequ est
  + 获取：方法参数

+ `response`对象：封装了服务器的响应信息。

  + 类型：HttpServletResponse

  + 获取：方法参数

+ `session`对象：用来保存会话信息。也就是说，可以实现在同一用户的不同请求之间共享数

  + 类型：HttpSession
  + 获取：request.getSession()

+ application对象：代表了当前应用程序的上下文。可以在不同的用户之间共享信息。

  + 类型：ServletContext
  + 获取：getServletContext(); request.getServletContext();

+ config对象：封装了应用程序的配置信息。

  + 类型：ServletConfig
  + 获取：配置信息

+ exception对象：封装了jsp程序执行过程中发生的异常和错误信息。类型：Exception

+ page对象：指向了当前jsp程序本身。类型：Object

+ pageContext对象：提供了对jsp页面所有对象以及命名空间的访问。类型：PageContext

  

### JSP 作用域复习

+ page范围：只在一个页面保留数据（javax.servlet.jsp.PageContext(抽象类)）

+ `request`范围：只在一个请求中保存数据（javax.servlet.httpServletRequest）

+ `Session`范围：在一次会话中保存数据，仅供单个用户使用(javax.servlet.http.HttpSession)

+ Application范围：在整个服务器中保存数据，全部用户共享(javax.servlet.ServletContext)

  

### 使用原生 Servlet

```java
@Controller
public class DemoController {

    @RequestMapping("demo1")
    public String demo1(HttpServletRequest request, HttpSession sessionParam) {
        // request 作用域
        request.setAttribute("request", "request");

        // session 作用域
        HttpSession session = request.getSession();
        session.setAttribute("session1", "通过request获取");
        sessionParam.setAttribute("session2", "通过handleMethod参数获取");

        // appliaction 作用域
        ServletContext application = request.getServletContext();
        application.setAttribute("application", "application");
        return "index1.jsp";
    }
}
```

```jsp
<body>
    request:${requestScope.request}<br>
    session1:${sessionScope.session1}<br>
    session2:${sessionScope.session2}<br>
    application:${applicationScope.application}
</body>
```

### 使用 Map 集合

1. 把 map 中内容放在 request 作用域中
2. spring 会对 map 集合通过 BindingAwareModelMap 进行实例 化

```java
    @RequestMapping("demo2")
    public String demo2(Map<String, Object> map) {
        // class org.springframework.validation.support.BindingAwareModelMap
        System.out.println(map.getClass());
        map.put("map", "value");
        return "index2.jsp";
    }
```

```jsp
<body>
    map:${requestScope.map}
</body>
```



### 使用Model接口

```java
@RequestMapping("demo3")
public String demo3(Model model) {
    model.addAttribute("model", "model value");
    return "index3.jsp";
}
```

```jsp
// 把内容最终放入到 request 作用域中.
<body>
    model:${requestScope.model}
</body>
```

### 使用ModelAndView类

```java
@RequestMapping("demo4")
public Object demo4() {
    System.out.println("hhhhhhhh");
    ModelAndView modelAndView = new ModelAndView("/index4.jsp");
    modelAndView.addObject("modelAndView", "modelAndView value");
    return modelAndView;
}
```

```jsp
<body>
    ModelAndView:${requestScope.modelAndView}
</body>
```

## 文件上传下载

### 文件上传

#### 文件上传

1. 基于 apache 的`commons-fileupload.jar `完成文件上传.

2. MultipartFile接口

   + 在 Spring MVC 框架中上传文件时将文件相关信息及操作封装到 `MultipartFile` 对象中，因此只需要使用 MultipartFile 类型声明模型类的一个属性即可对被上传文件进行操作。该接口具有如下方法。

     | 名称                              | 作用                                    |
     | --------------------------------- | --------------------------------------- |
     | byte[] getBytes()                 | 以字节数组的形式返回文件的内容          |
     | String getContentType()           | 返回文件的内容类型                      |
     | InputStream getInputStream()      | 返回一个InputStream，从中读取文件的内容 |
     | String getName()                  | 返回请求参数的名称                      |
     | String getOriginalFillename()     | 返回客户端提交的原始文件名称            |
     | long getSize()                    | 返回文件的大小，单位为字节              |
     | boolean isEmpty()                 | 判断被上传文件是否为空                  |
     | void transferTo(File destination) | 将上传文件保存到目标目录下              |

   + 在上传文件时需要在配置文件中使用 Spring 的 org.springframework.web.multipart.commons.`CommonsMultipartResolver` 类配置 MultipartResolver 用于文件上传。

3. 表单数据类型分类

   + 在的 `enctype` 属性控制表单类型
   + 默认值 `application/x-www-form-urlencoded`,普通表单数据.(少 量文字信息)
   + `text/plain` 大文字量时使用的类型.邮件,论文
   + `multipart/form-data` 表单中包含二进制文件内容



#### 单文件上传

```java
package com.ixfosa.controller;


import com.ixfosa.pojo.FileDomain;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;


@Controller
public class DemoController {

    @RequestMapping("demo1")
    public String demo1(MultipartFile file, String description) throws IOException {
        System.out.println(description);
        String filename = file.getOriginalFilename();

        String suffix = filename.substring(filename.lastIndexOf("."));

        // 判断上传文件类型
        if (suffix.equalsIgnoreCase(".png")) {
            UUID uuid = UUID.randomUUID();

            FileUtils.copyInputStreamToFile(file.getInputStream(),
                    new File("E:/" + uuid + suffix));

            return "show.jsp";
        } else {
            return "error.jsp";
        }
    }
    

    @RequestMapping("demo2")
    public String demo2(FileDomain fileDomain, HttpServletRequest request) {
        String realpath = request.getServletContext()
                .getRealPath("uploadfiles");
        //D:\code\IdeaProjects\springmvc\out\artifacts\springmvc08_war_exploded\uploadfiles
        System.out.println(realpath);
        String fileName = fileDomain.getFile().getOriginalFilename();
        File targetFile = new File(realpath, fileName);
        if (!targetFile.exists()) {
            targetFile.mkdirs();
        }

        // 上传
        try {
            fileDomain.getFile().transferTo(targetFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "show.jsp";
    }
}


public class FileDomain {
    private String description;
    private MultipartFile file;
}
```

```java
<body>
    ${fileDomain.description }
    <br>
    <!-- fileDomain.getFile().getOriginalFilename()-->
    ${fileDomain.file.originalFilename }
</body>
```

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:mvc="http://www.springframework.org/schema/mvc"
       xsi:schemaLocation="
        http://www.springframework.org/schema/beans
        http://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/context
        http://www.springframework.org/schema/context/spring-context.xsd
        http://www.springframework.org/schema/mvc
        http://www.springframework.org/schema/mvc/spring-mvc.xsd">

    <context:component-scan base-package="com.ixfosa.controller" />

    <mvc:annotation-driven />

    <!-- 配置MultipartResolver，用于上传文件，使用spring的CommonsMultipartResolver -->
    <bean id="multipartResolver" class="org.springframework.web.multipart.commons.CommonsMultipartResolver">
        <property name="maxUploadSize" value="5000000" />
        <property name="defaultEncoding" value="UTF-8" />
    </bean>
</beans>
```



#### 多文件上传

```jsp
      <form action="demo3" method="post" enctype="multipart/form-data">
        选择文件1：<input type="file" name="multipartFileList"><br>
        文件描述1：<input type="text" name="descriptionList"><br>
  
        选择文件2：<input type="file" name="multipartFileList"><br>
        文件描述2：<input type="text" name="descriptionList"><br>
  
        选择文件3：<input type="file" name="multipartFileList"><br>
        文件描述3：<input type="text" name="descriptionList"><br>
        <input type="submit" value="submit">
      </form>
```

```java
public class MultiFileDomain {
    private List<String> descriptionList;
    private List<MultipartFile> multipartFileList;
}

@Controller
public class DemoController {

    @RequestMapping("demo3")
    public String demo3(MultiFileDomain multiFileDomain, HttpServletRequest req) {
        String realpath = req.getServletContext().getRealPath("uploadfiles");

        File targetDir = new File(realpath);
        if (!targetDir.exists()) {
            targetDir.mkdirs();
        }

        List<MultipartFile> fileList = multiFileDomain.getMultipartFileList();
        for (MultipartFile multipartFile : fileList) {
            String fileName = multipartFile.getOriginalFilename();
            File targetFile = new File(realpath, fileName);

            try {
                multipartFile.transferTo(targetFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "showMulti.jsp";
    }
}
```



### 文件下载

1. 访问资源时相应头如果没有设置 `Content-Disposition`,浏览器默认按 照 inline 值进行处理
   + inline 能显示就显示,不能显示就下载.
2. 只需要修改相应头中 `Context-Disposition=”attachment;filename=文件名”`
   + `attachment` 下载,以附件形式下载.
   + `filename=`值就是下载时显示的下载文件名

3. 实现文件下载有以下两种方法：

   - 通过超链接实现下载。

     通过超链接实现下载固然简单，但暴露了下载文件的真实位置，并且只能下载存放在 Web 应用程序所在的目录下的文件。

   - 利用程序编码实现下载。

     利用程序编码实现下载可以增加安全访问控制，还可以从任意位置提供下载的数据，可以将文件存放到 Web 应用程序以外的目录中，也可以将文件保存到数据库中。

#### 超链接

web/files/文件

```jsp
    <a href="files/1.jpg">图片</a>     // 直接显示 图片 inline
    <a href="files/jsp.md">md</a>     // 404
    <a href="files/jsp.rar">rar</a>   // 下载，浏览器无法解析的就下载
```



#### 程序编码

```java
package com.ixfosa.controller;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by ixfosa on 2021/4/11 16:00
 */

@Controller
public class HelloController {

    @RequestMapping("demo1")
    public void demo1(String fileName, HttpServletRequest req, HttpServletResponse res) throws IOException {
        // 设置响应流中文件进行下载
        res.setHeader("Content-Disposition", "attachment;filename=" + fileName);
        // 把二进制流放入到响应体中.
        ServletOutputStream outputStream = res.getOutputStream();
        String path = req.getServletContext().getRealPath("files");
        System.out.println(path);

        File file = new File(path, fileName);
        byte[] bytes = FileUtils.readFileToByteArray(file);
        outputStream.write(bytes);
        outputStream.flush();
        outputStream.close();
    }

    // 显示要下载的文件	
    @RequestMapping("show")
    public String show(HttpServletRequest req, Model model) {
        String realPath = req.getServletContext().getRealPath("files");
        System.out.println("realpath:" + realPath);
        File dir = new File(realPath);
        File[] files = dir.listFiles();
        ArrayList<String> fileName = new ArrayList<>();
        for (File file : files) {
            fileName.add(file.getName());
        }
        System.out.println();
        model.addAttribute("files", fileName);
        return "show.jsp";
    }


    @RequestMapping("download")
    public void download(String fileName, HttpServletRequest req, HttpServletResponse res) throws IOException {
        System.out.println(fileName);
        // 设置下载文件使用的报头
        res.setHeader("Content-Disposition", "attachment;filename=" + fileName);

        String realPath = req.getServletContext().getRealPath("files");

        // 读入文件
        FileInputStream in = new FileInputStream(realPath + "//" + fileName);

        // 得到响应对象的输出流，用于向客户端输出二进制数据
        ServletOutputStream out = res.getOutputStream();
        int len = 0;
        byte[] buf = new byte[1024];

        while ((len = in.read(buf)) != -1) {
            out.write(buf, 0, len);
        }

        out.flush();
        in.close();
        out.close();
    }
}
```

```java
<body>
    <ul>
        <c:forEach items="${files}" var="filename">
            <li>
                <a href="download?fileName=${filename}">${filename}</a>
            </li>
        </c:forEach>
    </ul>
</body>
```

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:mvc="http://www.springframework.org/schema/mvc"
       xsi:schemaLocation="
        http://www.springframework.org/schema/beans
        http://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/context
        http://www.springframework.org/schema/context/spring-context.xsd
        http://www.springframework.org/schema/mvc
        http://www.springframework.org/schema/mvc/spring-mvc.xsd">
    <context:component-scan base-package="com.ixfosa.controller" />

    <mvc:annotation-driven />

    <bean id="multipartResolver" class="org.springframework.web.multipart.commons.CommonsMultipartResolver"></bean>

    <mvc:resources mapping="/files/**" location="/files/" />
</beans>
```

## MVC拦截器

### 配置及使用

1. 发送`请求`时被拦截器拦截,在控制器的前后添加额外功能
   + 跟 AOP 区分开.AOP 在特定方法前后扩充(对 ServiceImpl)
   + 拦截器,请求的拦截.针对点是控制器方法.(对 Controller)

2. SpringMVC 拦截器和 Filter 的区别
   + 拦截器只能拦截器 Controller
   + Filter 可以拦截任何请求

3. 定义一个拦截器可以通过两种方式：
   + 实现 `HandlerInterceptor` 接口或继承 HandlerInterceptor 接口的实现类来定义；
   + 实现 `WebRequestInterceptor` 接口或继承 WebRequestInterceptor 接口的实现类来定义。

4. 实现自定义拦截器的步骤

   1. 新建类实现 HandlerInterceptor

      ```java
      package com.ixfosa.interceptor;
      
      import org.springframework.lang.Nullable;
      import org.springframework.web.servlet.HandlerInterceptor;
      import org.springframework.web.servlet.ModelAndView;
      
      import javax.servlet.http.HttpServletRequest;
      import javax.servlet.http.HttpServletResponse;
      
      /**
       * Created by ixfosa on 2021/4/14 18:04
       */
      public class HelloInterceptor implements HandlerInterceptor {
      
          // 在进入控制器之前执行
          // 如果返回值为false,阻止进入控制器
          // 控制代码
          @Override
          public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
              System.out.println("handler: " + handler);
              System.out.println("preHandle...");
              return true;
          }
      
          // 控制器执行完成,进入到jsp之前执行.
          // 日志记录.
          // 敏感词语过滤
          @Override
          public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
              System.out.println("to: " + modelAndView.getViewName());
              String word = modelAndView.getModel().get("model").toString();
              System.out.println("model的值: " + word);
              String newWord = word.replace("青菜", "辣条");
              modelAndView.getModel().put("model", newWord);
              System.out.println("postHandle...");
          }
      
          // jsp执行完成后执行
          // 记录执行过程中出现的异常.
          // 可以把异常记录到日志中
          @Override
          public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
              System.out.println("afterCompletion: " + ex.getMessage());
          }
      }
      
      ```

   2. 编写Contrlloer

      ```java
      @Controller
      public class HelloController {
          @RequestMapping("hello")
          public String hello(Model model) {
              System.out.println("hello...");
              model.addAttribute("model", "我爱吃青菜！！！");
              return "index.jsp";
          }
      }
      ```

   3.  编写jsp

      ```jsp
        <body>
          ${model}  <%--我爱吃辣条！！！--%>
        </body>
      ```

   4. springmvc配置文件

      ```java
      <?xml version="1.0" encoding="UTF-8"?>
      <beans xmlns="http://www.springframework.org/schema/beans"
             xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
             xmlns:context="http://www.springframework.org/schema/context"
             xmlns:mvc="http://www.springframework.org/schema/mvc"
             xsi:schemaLocation="
              http://www.springframework.org/schema/beans
              http://www.springframework.org/schema/beans/spring-beans.xsd
              http://www.springframework.org/schema/context
              http://www.springframework.org/schema/context/spring-context.xsd
              http://www.springframework.org/schema/mvc
              http://www.springframework.org/schema/mvc/spring-mvc.xsd">
      
          <context:component-scan base-package="com.ixfosa.controller" />
      
          <mvc:annotation-driven />
      
         <!-- <mvc:interceptors>
              <bean class="com.ixfosa.interceptor.HelloInterceptor" />
          </mvc:interceptors>-->
      
          <!-- 配置拦截器 -->
          <mvc:interceptors>
              <!-- 配置一个全局拦截器，拦截所有请求 -->
              <bean class="com.ixfosa.interceptor.HelloInterceptor" />
      
              <!-- 配置拦截器作用的路径 -->
              <mvc:interceptor>
                  <!-- 配置拦截器作用的路径 -->
                  <mvc:mapping path="/hello" />
      
                  <!-- 配置不需要拦截作用的路径 -->
                  <mvc:exclude-mapping path="/demo" />
      
                  <!-- 定义<mvc:interceptor>元素中，表示匹配指定路径的请求才进行拦截 -->
                  <bean class="com.ixfosa.interceptor.HelloInterceptor" />
              </mvc:interceptor>
          </mvc:interceptors>
      </beans>
      ```

   > 1. <mvc：interceptor> 元素中定义的是指定路径的拦截器，其子元素 <mvc：mapping> 用于配置拦截器作用的路径，该路径在其属性 path 中定义。
   >
   > 2. path 的属性值“/**”表示拦截所有路径，“/gotoTest”表示拦截所有以“/gotoTest”结尾的路径。如果在请求路径中包含不需要拦截的内容，可以通过 <mvc：exclude-mapping> 子元素进行配置。
   >
   > 3. 需要注意的是，<mvc：interceptor> 元素的子元素必须按照 <mvc：mapping.../>、<mvc：exclude-mapping.../>、<bean.../> 的顺序配置。

   

   

### 拦截器栈

1. 多个拦截器同时生效时,组成了拦截器栈

2. 顺序:先进后出.

3. 执行顺序和在 springmvc.xml 中配置顺序有关

4. 设置先配置拦截器 A 在配置拦截器 B 执行顺序为

   ```java
   preHandle(A) --> preHandle(B) --> 控制器方法 --> postHandle(B) --> postHanle(A) --> JSP --> afterCompletion(B) --> afterCompletion(A)
   ```

```java
// 拦截器 A 
public class A implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("A preHandle...");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("A postHandle...");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("A afterCompletion...");
    }
}


// 拦截器 B
public class B implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("b preHandle...");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("b postHandle...");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("b afterCompletion...");
    }
}

```

```xml
<mvc:interceptors>
    <bean class="com.ixfosa.interceptor.A" />
    <bean class="com.ixfosa.interceptor.B" />
</mvc:interceptors>
```

```java
@Controller
public class DemoController {
    @RequestMapping("demo")
    public void demo() {
        System.out.println("demo...");
    }
}

A preHandle...
b preHandle...
demo...
b postHandle...
A postHandle...
b afterCompletion...
A afterCompletion...
```



## 统一异常处理

Spring MVC 统一异常处理有以下 3 种方式：

- 使用 Spring MVC 提供的简单异常处理器 `SimpleMappingExceptionResolver`。
- 实现 Spring 的异常处理接口 `HandlerExceptionResolver` 自定义自己的异常处理器。
- 使用 `@ExceptionHandler` 注解实现异常处理

对于 Unchecked Exception 而言，由于代码不强制捕获，往往被忽略，如果运行期产生了 Unchecked Exception，而代码中又没有进行相应的捕获和处理，则可能不得不面对 404、500 等服务器内部错误提示页面，所以在 web.xml 文件中添加了全局异常 404 处理。具体代码如下：

```xml
<error-page>
    <error-code>404</error-code>
    <location>/WEB-INF/jsp/404.jsp</location>
</error-page>
```



### SimpleMappingExceptionResolver

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xmlns:context="http://www.springframework.org/schema/context"
    xsi:schemaLocation="
        http://www.springframework.org/schema/beans
        http://www.springframework.org/schema/beans/spring一beans.xsd
        http://www.springframework.org/schema/context
        http://www.springframework.org/schema/context/spring-context.xsd">
    
    <!-- 使用扫描机制扫描包 -->
    <context:component-scan base-package="controller" />
    <context:component-scan base-package="service" />
    <context:component-scan base-package="dao" />
    
    <!-- 配置视图解析器 -->
    <bean
        class="org.springframework.web.servlet.view.InternalResourceViewResolver"
        id="internalResourceViewResolver">
        <!--前缀 -->
        <property name="prefix" value="/WEB-INF/jsp/" />
        <!-- 后缀 -->
        <property name="suffix" value=".jsp" />
    </bean>
    
    <!--SimpleMappingExceptionResolver（异常类与 View 的对应关系） -->
    <bean class="org.springframework.web.servlet.handler.SimpleMappingExceptionResolver">
        <!-- 定义默认的异常处理页面，当该异常类型注册时使用 -->
        <property name="defaultErrorView" value="error"></property>
        <!-- 定义异常处理页面用来获取异常信息的变量名，默认名为exception -->
        <property name="exceptionAttribute" value="ex"></property>
        
        <!-- 定义需要特殊处理的异常，用类名或完全路径名作为key，异常页名作为值 -->
        <property name="exceptionMappings">
            <props>
                <prop key="exception.MyException">my-error</prop>
                <prop key="java.sql.SQLException">sql-error</prop>
                <!-- 在这里还可以继续扩展对不同异常类型的处理 -->
            </props>
        </property>
    </bean>
</beans>
```

### HandlerExceptionResolver

org.springframework.web.servlet.`HandlerExceptionResolver` 接口用于解析请求处理过程中所产生的异常。开发者可以开发该接口的实现类进行 Spring MVC 应用的异常统一处理。

```java
public class MyExceptionHandler implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest arg0,
            HttpServletResponse arg1, Object arg2, Exception arg3) {
        
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("ex", arg3);
        // 根据不同错误转向不同页面（统一处理），即异常与View的对应关系
        if (arg3 instanceof MyException) {
            return new ModelAndView("my-error", model);
        } else if (arg3 instanceof SQLException) {
            return new ModelAndView("sql-error", model);
        } else {
            return new ModelAndView("error", model);
        }
    }
}
```

需要将实现类 MyExceptionHandler 在配置文件中托管给 Spring MVC 框架才能进行异常的统一处理，配置代码为 `<bean class="exception.MyExceptionHandler"/>`。

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xmlns:context="http://www.springframework.org/schema/context"
    xsi:schemaLocation="
        http://www.springframework.org/schema/beans
        http://www.springframework.org/schema/beans/spring一beans.xsd
        http://www.springframework.org/schema/context
        http://www.springframework.org/schema/context/spring-context.xsd">
    <!-- 使用扫描机制扫描包 -->
   
    <context:component-scan base-package="controller" />
    <context:component-scan base-package="service" />
    <context:component-scan base-package="dao" />
    <!-- 配置视图解析器 -->
    <bean
        class="org.springframework.web.servlet.view.InternalResourceViewResolver"
        id="internalResourceViewResolver">
        <!--前缀 -->
        <property name="prefix" value="/WEB-INF/jsp/" />
        <!-- 后缀 -->
        <property name="suffix" value=".jsp" />
    </bean>
    <!--托管MyExceptionHandler-->
    <bean class="exception.MyExceptionHandler"/>
</beans>
```

### @ExceptionHandler注解异常处理

`@ExceptionHandler `注解声明统一处理异常时不需要配置任何信息

```java
public class BaseController {
    /** 基于@ExceptionHandler异常处理 */
    @ExceptionHandler
    public String exception(HttpServletRequest request, Exception ex) {
        request.setAttribute("ex", ex);
        // 根据不同错误转向不同页面，即异常与view的对应关系
        if (ex instanceof SQLException) {
            return "sql-error";
        } else if (ex instanceof MyException) {
            return "my-error";
        } else {
            return "error";
        }
    }
}
```

@ExceptionHandler 注解声明统一处理异常时不需要配置任何信息

```java
@Controller
public class TestExceptionController extends BaseController{
    // ...
}
```



## 对 Date 类型转换

在 springmvc.xml 中配置,代码中不需要做任何修改

+ 必须额外导入 joda-time.jar
+ 时间类型 java.util.Date

```jsp
<form action="demo" method="post">
    <input type="date" name="time">
    <input type="submit" value="submit">
</form>
```

```java
public class Demo {
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date time;
}


@Controller
public class DemoController {

    @RequestMapping("demo")
    public void demo(Demo demo) {
        System.out.println(demo);
    }
}
```

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:mvc="http://www.springframework.org/schema/mvc"
       xsi:schemaLocation="
        http://www.springframework.org/schema/beans
        http://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/context
        http://www.springframework.org/schema/context/spring-context.xsd
        http://www.springframework.org/schema/mvc
        http://www.springframework.org/schema/mvc/spring-mvc.xsd">

    <context:component-scan base-package="com.ixfosa.controller" />

    <mvc:annotation-driven conversion-service="conversionService" />

    <bean id="conversionService" 
          class="org.springframework.format.support.FormattingConversionServiceFactoryBean">
        <property name="registerDefaultFormatters" value="false" />
        <property name="formatters">
            <set>
                <bean           class="org.springframework.format.number.NumberFormatAnnotationFormatterFactory" />
            </set>
        </property>

        <property name="formatterRegistrars">
            <set>
                <bean 
      class="org.springframework.format.datetime.joda.JodaTimeFormatterRegistrar">
                    <property name="dateFormatter">
                        <bean 
                            class="org.springframework.format.datetime.joda.DateTimeFormatterFactoryBean">
                            <property name="pattern" value="yyyy-MM-dd" />
                        </bean>
                    </property>
                </bean>
            </set>
        </property>
    </bean>
</beans>
```



