## 基本概念 

监听器： 主要是用来监听特定对象的创建或销毁、属性的变化的！

+ 是一个实现特定接口的普通java类

+ Servlet中监听的对象

  + `request` 监听器
  + `session `相关监听器
  + `servletContext` 监听器

+ 监听器接口：
  监听对象创建/销毁的监听器接口

  ```java
  Interface ServletRequestListener     // 监听request对象的创建或销毁
  Interface HttpSessionListener        // 监听session对象的创建或销毁
  Interface ServletContextListener     // 监听servletContext对象的创建或销毁
  ```

+ 监听对象属性的变化

  ```java
  Interface ServletRequestAttributeListener // 监听request对象属性变化: 添加、移除、修改
  Interface HttpSessionAttributeListener    // 监听session对象属性变化: 添加、移除、修改
  Interface ServletContextAttributeListener // 监听servletContext对象属性变化
  ```

+ session相关监听器

  ```java
  Interface HttpSessionBindingListener      // 监听对象绑定到session上的事件	
  Interface HttpSessionActivationListener   // 监听session序列化及反序列化的事件
  ```



## 生命周期监听器

声明周期监听器： 监听对象的创建、销毁的过程！
监听器开发步骤：

1. 写一个普通java类，实现相关接口；
2. 配置(web.xml)



### ServletRequestListener

+ ServletRequestListener 接口用于监听ServletRequest 对象的创建和销毁。

  + Request 对象被创建时，监听器的requestInitialized方法将会被调用。

  + Request对象被销毁时，监听器的requestDestroyed方法将会被调用。

+ ServletRequest域对象创建和销毁的时机
  + 创建：用户每一次访问，都会创建一个reqeust
  + 销毁：当前访问结束，request对象就会销毁

```java
/**
 *  监听request对象的创建或销毁
 */
public class MyRequestListener implements ServletRequestListener{

	// 对象销毁
	@Override
	public void requestDestroyed(ServletRequestEvent sre) {
		// 获取request中存放的数据
		Object obj = sre.getServletRequest().getAttribute("cn");
		System.out.println(obj);
		System.out.println("MyRequestListener.requestDestroyed()");
	}

	// 对象创建
	@Override
	public void requestInitialized(ServletRequestEvent sre) {
		System.out.println("MyRequestListener.requestInitialized()");
	}
}
```

```xml
<!-- 监听request对象创建、销毁 -->
<listener>
	<listener-class>cn.ixfosa.life.MyRequestListener</listener-class>
</listener>
```



### HttpSessionListener

+ HttpSessionListener接口用于监听HttpSession的创建和销毁

  + 创建一个Session时，sessionCreated(HttpSessionEvent se) 方法将会被调用。
  + 销毁一个Session时，sessionDestroyed (HttpSessionEvent se) 方法将会被调用。

  

+ Session域对象创建和销毁的时机创建：

  + 用户每一次访问时，服务器创建session
  + 销毁：如果用户的session 30分钟没有使用，服务器就会销毁session，在web.xml里面也可以配置session失效时间

```java
/**
 * 监听Session对象创建、销毁
 */
public class MySessionListener implements HttpSessionListener{

	// 创建
	@Override
	public void sessionCreated(HttpSessionEvent se) {
		System.out.println("MySessionListener.sessionCreated()");
	}

	// 销毁
	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		System.out.println("MySessionListener.sessionDestroyed()");
	}

}

```

```xml
<!-- session的最大活跃时间 -->
<session-config>
    <session-timeout>60</session-timeout>
</session-config>

<!-- 监听session对象创建、销毁 -->
<listener>
    <listener-class>cn.ixfosa.life.MySessionListener</listener-class>
</listener>
```



### ServletContextListener

+ ServletContextListener 接口用于监听 ServletContext 对象的创建和销毁事件。

+ 当 ServletContext 对象被创建时，激发contextInitialized (ServletContextEvent sce)方法

+ 当 ServletContext 对象被销毁时，激发contextDestroyed(ServletContextEvent sce)方法。

> ServletContext域对象何时创建和销毁：
>
> + 创建：服务器启动针对每一个web应用创建servletcontext
>
> + 销毁：服务器关闭前先关闭代表每一个web应用的servletContext

```java
/**
 * 监听ServletContext对象创建或销毁
 */
public class MyServletContextListener implements ServletContextListener{

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("MyServletContextListener.contextDestroyed()");
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("MyServletContextListener.contextInitialized()");
	}
}
```

```xml
<!-- 监听servletContext对象创建、销毁 -->
<listener>
    <listener-class>cn.itcast.a_life.MyServletContextListener</listener-class>
</listener>
```



## 属性监听器

属性监听器：处理被监听对象中的属性的增加，删除和替换的事件

+ 监听:  `request` / `session` / `servletContext`对象属性的变化

  ```java
  ServletContextAttributeListener
  HttpSessionAttributeListener 
  ServletRequestAttributeListener
  ```

+ 同一个事件在这三个接口中对应的方法名称完全相同，只是接受的参数类型不同。 

  + attributeAdded 方法

    + 当向被监听器对象中增加一个属性时，web容器就调用事件监听器的 attributeAdded 方法进行相应，这个方法接受一个事件类型的参数，监听器可以通过这个参数来获得正在增加属性的域对象和被保存到域中的属性对象

    ```java
    public void attributeAdded(ServletContextAttributeEvent scae) 
    public void attributeReplaced(HttpSessionBindingEvent  hsbe) 
    public void attributeRmoved(ServletRequestAttributeEvent srae)
    ```

  + attributeRemoved 方法

    + 当删除被监听对象中的一个属性时，web 容器调用事件监听器的这个方法进行相应

    ```java
    public void attributeRemoved(ServletContextAttributeEvent scae) 
    public void attributeRemoved (HttpSessionBindingEvent  hsbe) 
    public void attributeRemoved (ServletRequestAttributeEvent srae)
    ```

  + attributeReplaced 方法

    + 当监听器的域对象中的某个属性被替换时，web容器调用事件监听器的这个方法进行相应

    ```java
    public void attributeReplaced(ServletContextAttributeEvent scae) 
    public void attributeReplaced (HttpSessionBindingEvent  hsbe) 
    public void attributeReplaced (ServletRequestAttributeEvent srae)
    ```



演示：HttpSessionAttributeListener

```java
/**
 * 监听session对象属性的变化
 */
public class MySessionAttrListener implements HttpSessionAttributeListener {

	// 属性添加
	@Override
	public void attributeAdded(HttpSessionBindingEvent se) {
		// 先获取session对象
		HttpSession session = se.getSession();
		// 获取添加的属性
		Object obj = session.getAttribute("userName");
		// 测试
		System.out.println("添加的属性：" + obj);
	}

	// 属性移除
	@Override
	public void attributeRemoved(HttpSessionBindingEvent se) {
		System.out.println("属性移除");
	}

	// 属性被替换
	@Override
	public void attributeReplaced(HttpSessionBindingEvent se) {
		// 获取sesison对象
		HttpSession session = se.getSession();
		
		// 获取替换前的值
		Object old = se.getValue();
		System.out.println("原来的值：" + old);
		
		// 获取新值
		Object obj_new = session.getAttribute("userName");
		System.out.println("新值：" + obj_new);
		
	}
}
```

```java
<!-- 属性监听器 -->
<listener>
    <listener-class>cn.ixfosa.attr.MySessionAttrListener</listener-class>
</listener>
```

```java
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="cn.ixfosa.session.Admin"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
  </head>
  
  <body>
  	test!
  	<%
  		request.setAttribute("cn","China");
  		session.invalidate();  //销毁session
  		
  		session.setAttribute("userName","Jack");
  		session.removeAttribute("userName");
  		session.setAttribute("userName","Jack_new");
  		
  	%>
  </body>
</html>
```



## 其他监听器: Session相关

### HttpSessionBindingListener

HttpSessionBindingListener 监听对象 绑定 / 解除 绑定到sesison上的事件

+ 实现了HttpSessionBindingListener接口的 JavaBean 对象可以感知自己被绑定到 Session 中和从 Session 中删除的事件
+ 当对象被绑定到 HttpSession 对象中时，web 服务器调用该对象的 `void valueBound(HttpSessionBindingEvent event)`方法
+ 当对象从 HttpSession 对象中解除绑定时，web 服务器调用该对象的 `voidvalueUnbound(HttpSessionBindingEvent event)`方法



HttpSessionBindingListener 监听器，和 声明周期、属性监听器区别

+ 不用再web.xml配置
+ 因为监听的对象是自己创建的

```java
/**
 * 监听此对象绑定到session上的过程，需要实现session特定接口
 */
public class Admin implements HttpSessionBindingListener {

	private int id;
	private String name;
	
	public Admin() {
		super();
	}
	public Admin(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	
	// 构造函数
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	// 对象放入session
	@Override
	public void valueBound(HttpSessionBindingEvent event) {
		System.out.println("Admin对象已经放入session");
	}
	// 对象从session中移除
	@Override
	public void valueUnbound(HttpSessionBindingEvent event) {
		System.out.println("Admin对象从session中移除！");
	}
}
```



### HttpSessionActivationLstener

+ 实现了HttpSessionActivationListener 接口的 JavaBean 对象可以感知自己被活化和钝化的事件
+ 当绑定到 HttpSession 对象中的对象将要随 HttpSession 对象被钝化之前，web 服务器调用如下方法`sessionWillPassivate(HttpSessionBindingEvent event) `方法
+ 当绑定到 HttpSession 对象中的对象将要随 HttpSession 对象被活化之后，web 服务器调用该对象的 `voidsessionDidActive(HttpSessionBindingEvent event)`方法

