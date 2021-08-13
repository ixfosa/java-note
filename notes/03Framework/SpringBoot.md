## Spring-Boot简介

1. Spring Boot 是 Spring 家族中的一个框架，用来简化 Spring 应用程序的创建和 开发过程
2. Spring Boot 的特性
   + 能够快速创建基于 Spring 的应用程序
   + 能够直接使用 main 方法启动内嵌的 Tomcat 服务器运行 Spring Boot 程序，不需要部署 war 包文件
   + 提供约定的 starter POM 来简化 Maven 配置，让 Maven 的配置变得简单
   + 自动化配置，根据项目的 Maven 依赖配置，Spring boot 自动配置 Spring、Spring mvc 等
   + 提供了程序的健康检查等功能
   + 基本可以完全不使用 XML 配置文件，采用注解配置
3. Spring Boot 四大核心
   + 自动配置
   + 起步依赖
   + Actuator
   + 命令行界面
4. Spring Boot的优缺点
   1. 优点
      + 快速构建项目。
      + 对主流开发框架的无配置集成。
      + 项目可独立运行，无须外部依赖Servlet容器。
      + 提供运行时的应用监控。
      + 极大地提高了开发、部署效率。
      + 与云计算]的天然集成。
   2. 缺点
      + 版本迭代速度很快，一些模块改动很大。
      + 由于不用自己做配置，报错时很难定位。
      + 网上现成的解决方案比较少。

> Spring Boot 启动图标: http://patorjk.com/software/taag/#p=display&f=Graffiti&t=ixfosa%0A
>
> + `resource`目录下面放入一个`banner.txt`文件



## 快速搭建及核心配置

### 快速搭建(idea)

1. 选择类型 Spring Initializr

2. 指定 GAV 及 pom 配置信息

   + packaging：`jar`

3. 选择 Spring Boot 版本及依赖会根据选择的依赖自动添加起步依赖并进行自动配置

   + Spring Web 创建的时 web 工程

5. 修改 Content Root 路径及文件所在目录

6. 对 POM.xml 文件进行解释

   ```xml
   <?xml version="1.0" encoding="UTF-8"?>
   <project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
   	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
   	<modelVersion>4.0.0</modelVersion>
   	<parent>
   		<!--继承 SpringBoot 框架的一个父项目，所有自己开发的 Spring Boot 都必须的继承 -->
   		<groupId>org.springframework.boot</groupId>
   		<artifactId>spring-boot-starter-parent</artifactId>
   		<version>2.5.0</version>
   		<relativePath/> <!-- lookup parent from repository -->
   	</parent>
   	<!--当前项目的 GAV 坐标-->
   	<groupId>top.ixfosa.springboot</groupId>
   	<artifactId>ch01-springboot-hello</artifactId>
   	<version>0.0.1</version>
   
   	<!--maven 项目名称，可以删除-->
   	<name>ch01-springboot-hello</name>
   
   	<!--maven 属性配置，可以在其它地方通过${}方式进行引用-->
   	<properties>
   		<java.version>1.8</java.version>
   	</properties>
   
   	<!--SpringBoot框架web项目起步依赖，通过该依赖自动关联其它依赖，不需要一个一个去添加了-->
   	<dependencies>
   		<dependency>
   			<groupId>org.springframework.boot</groupId>
   			<artifactId>spring-boot-starter-web</artifactId>
   		</dependency>
   
   		<dependency>
   			<groupId>org.springframework.boot</groupId>
   			<artifactId>spring-boot-starter-test</artifactId>
   			<scope>test</scope>
   		</dependency>
   	</dependencies>
   
   	<build>
   		<plugins>
   			<!--SpringBoot 提供的打包编译等插件-->
   			<plugin>
   				<groupId>org.springframework.boot</groupId>
   				<artifactId>spring-boot-maven-plugin</artifactId>
   			</plugin>
   		</plugins>
   	</build>
   
   </project>
   ```

### 项目结构

+ .mvn | mvnw | mvnw.cmd：使用脚本操作执行 maven 相关命令，国内使用较少，可删除
+ .gitignore：使用版本控制工具 git 的时候，设置一些忽略提交的内容

+ `static`：存放静态资源，如图片、CSS、JavaScript 等 
+ `templates`：存放 Web 页面的模板文件 
+ `application.properties` / `application.yml`用于存放程序的各种依赖模块的配置信息，比如 服务 端口，数据库连接配置等



### HelloWorld 案例

#### 编写Controller

```java
package top.ixfosa.controller;

@Controller
public class HelloController {

    @RequestMapping("hello")
    @ResponseBody
    public String hello() {
        return "Hello Spring-Boot...";
    }
}
```

#### 启动启动类

```java
package top.ixfosa;

// SpringBoot 启动类
@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
```

> http://localhost:8080/hello



### 案例分析

1. Spring Boot 的父级依赖 spring-boot-starter-parent 配置之后，当前的项目就是 Spring Boot 项目

2. spring-boot-starter-parent 是一个 Springboot 的父级依赖，开发 SpringBoot 程序都需 要继承该父级项目，它用来提供相关的 Maven 默认依赖，使用它之后，常用的 jar 包依赖可以省去 version 配置

3. Spring Boot 提供了哪些默认 jar 包的依赖，可查看该父级依赖的 pom 文件

4. 如果不想使用某个默认的依赖版本，可以通过 pom.xml 文件的属性配置覆盖各个 依赖项，比如覆盖 Spring 版本

   ```java
   <properties>
       <spring-framework.version>5.0.0.RELEASE</ spring-framework.version >
   </properties>
   ```

5. `@SpringBootApplication` 注解是 Spring Boot 项目的核心注解，主要作用是开启 Spring 自动配置，如果在 Application 类上去掉该注解，那么不会启动 SpringBoot 程序

6. main 方法是一个标准的 Java 程序的 main 方法，主要作用是作为项目启动运行的入 口
7. `Controller` 及 `@ResponseBody`依然是我们之前的 Spring MVC，因为 Spring Boot 的里面依然是使用的 Spring MVC + Spring + MyBatis 等框架

8. 启动器存放的位置。启动器可以和 controller 位于同一个包下，或者位于 controller 的上一级 包中，但是不能放到 controller 的`平级`以及`子包`下。

### 核心配置文件

Spring Boot 的核心配置文件用于配置 Spring Boot 程序，名字必须以 `application` 开始

#### 核心配置格式

1. .properties 文件（默认采用该文件）

   ```properties
   #设置内嵌 Tomcat 端口号
   server.port=9090
   #配置项目上下文根
   server.servlet.context-path=/springboot
   ```

   > http://localhost:9090/springboot/hello



2.  .yml 文件

   `yml` 是一种 yaml 格式的配置文件，主要采用一定的空格、换行等格式排版进行配置。 yaml 是一种直观的能够被计算机识别的的数据序列化格式，容易被人类阅读，yaml 类 似于 xml，但是语法比 xml 简洁很多，值与前面的冒号配置项必须要有一个`空格`， yml 后 缀也可以使用 yaml 后缀

   ```yml
   server:
     port: 8181
     servlet:
       context-path: /spring-boot
   ```

   > 注意：当两种格式配置文件同时存在，使用的是`.properties` 配置文件，为了演示 yml，可以 先将其改名，重新运行 Application，查看启动的端口及上下文根



#### 多环境配置

1. 在实际开发的过程中，项目会经历（开发->测试->上线），每个阶段 的配置也会不同，例如：端口、上下文根、数据库等，那么这个时候为了方便在不同的环境 之间切换，SpringBoot 提供了多环境配置

2. 为每个环境创建一个配置文件，命名必须以 `application-环境标识.properties|yml`

   + application.properties | yml ：总配置文件
   + application-dev.properties | yml：开发环境核心配置文件
   + application-product.properties | yml：生产环境核心配置文件
   + application-test.properties | yml ：测试环境核心配置文件

   

   1. application-dev.properties | yml

   ```properties
   #开发环境
   
   #设置内嵌 Tomcat 默认端口号
   server.port=8080
   #设置项目的上下文根
   server.servlet.context-path=/dev
   ```

   ```yml
   #设置开发环境配置
   server:
   	port: 8080 #设置 Tomcat 内嵌端口号
       servlet:
           context-path: /dev #设置上下文根
   ```

   2. application-product.properties | yml

   ```properties
   #生产环境
   
   #配置内嵌 Tomcat 默认端口号
   server.port=80
   #配置项目上下文根
   server.servlet.context-path=/product
   ```

   ```yml
   #设置生产环境配置
   server:
       port: 80
       servlet:
       	context-path: /product
   ```

   3. application-test.properties | yml

   ```properties
   #测试环境
   
   #配置内嵌 Tomcat 端口号
   server.port=8081
   #配置项目的上下文根
   server.servlet.context-path=/test
   ```

   ```yml
   #设置测试环境配置
   server:
       port: 9090
       servlet:
       	context-path: /test
   ```

   

   4. 在总配置文件 application.properties 进行环境的激活

   ```properties
   #SpringBoot 的总配置文件
   
   #激活开发环境
   #spring.profiles.active=dev
   
   #激活测试环境
   #spring.profiles.active=test
   
   #激活生产环境
   spring.profiles.active=product
   ```

   ```yml
   #springboot 总配置文件
   
   #激活开发环境
       #spring:
           # profiles:
           	# active: dev
   
   
   #激活测试环境
       #spring:
           # profiles:
               # active: test
   #激活生产环境
   spring:
        profiles:
            active: product
   ```

   

   > 等号右边的值和配置文件的环境标识名一致，可以更改总配置文件的配置，重新运行 Application，查看启动的端口及上下文根



#### 自定义配置

在 SpringBoot 的核心配置文件中，除了使用内置的配置项之外，还可以在自定义配 置，然后采用如下注解去读取配置的属性值



1. `@Value` 注解

   ```properties
   server.port=8080
   server.servlet.context-path=/
   
   student.name=大龙虾
   websit=www.ixfosa.top
   ```

   ```yml
   server:
   	port: 8080
   	servlet:
   		context-path: /
   
   student:
   	name: 大龙虾
   websit: www.ixfosa.top
   ```

   ```java
   @Controller
   public class HelloController {
   
       @Value("${student.name}")
       private String studentName;
   
       @Value("${websit}")
       private String websit;
   
       @RequestMapping("hello")
       @ResponseBody
       public String hello() {
           return "Hello " + studentName + "<br/>" + "Hello " + websit;
       }
   }
   ```

   

2. `@ConfigurationProperties`

   将整个文件映射成一个对象，用于自定义配置项比较多的情况

   1. 为该类加上 `Component` 和 `ConfigurationProperties` 注解，并在 ConfigurationProperties 注解中添加属性 prefix， 作用可以区分同名配置

      ```java
      在 ConfigInfo 类中使用了 ConfigurationProperties 注解后，IDEA 会出现一个警告，
      不影响程序的执行
          
      点击 open documentnation 跳转到网页，在网页中提示需要加一个依赖，将这
      个依赖拷贝，粘贴到 pom.xml 文件中
          
      <!--解决使用@ConfigurationProperties 注解出现警告问题-->
      <dependency>
          <groupId>org.springframework.boot</groupId>
          <artifactId>spring-boot-configuration-processor</artifactId>
          <optional>true</optional>
      </dependency>
          
      
      @Component
      @ConfigurationProperties(prefix = "student")
      public class ConfigInfo {
          private String name;
          private String websit;
          
          // getter...
          // setter...
      }
      ```

   2. application.properties | yml 配置文件

      ```properties
      
      server.port=8080
      server.servlet.context-path=/
      
      student.name=long
      student.websit=www.ixfosa.top
      ```

      ```yml
      server:
      	port: 8080
      	servlet:
      		context-path: /
      
      student:
      	name: 大龙虾
      	websit: www.ixfosa.top
      ```

   3. 测试类

      ```java
      @Controller
      public class HelloController {
      
          @Autowired
          private ConfigInfo configInfo;
      
          @RequestMapping("hello")
          @ResponseBody
          public String hello() {
              return "Hello " + configInfo.getName() + "<br/>"
                      + "Hello " + configInfo.getWebsit();
          }
      }
      
      ```

   

   如果在 SpringBoot 核心配置文件中有中文信息，会出现乱码：

   + 一般在配置文件中，不建议出现中文（注释除外）

   + 如果有，可以先转化为 ASCII 码

     idea: File -> setings -> Editor -> File Encodiings -> Properties Files(*.properties)    UTF-8   √

   

   如果是从其它地方拷贝的配置文件，一定要将里面的空格删干净

   

## 整合Web开发

### 整合 Servlet

Windows 关闭指定端口的进程

+ netstat -ano | findstr 8080     查找本端口当前使用情况
+  tasklist | findstr  15904         查询当前端口PID为15904的进程
+ taskkill -PID 15904 -F            杀死进程



#### 通过注解注册Servlet 组件

1. 编写 servlet

   ```java
   /**
    *SpringBoot整合Servlet方式一
    *
    *<servlet>
    *	 <servlet-name>HelloServlet</servlet-name>
    *	 <servlet-class>top.ixfosa.servlet.HelloServlet</servlet-class>
    *</servlet>
    *
    *<servlet-mapping>
    *   <servlet-name>HelloServlet</servlet-name>
    *   <url-pattern>/hello</url-pattern>
    *</servlet-mapping>
    *
    */
   @WebServlet("/hello")
   public class HelloServlet extends HttpServlet {
       @Override
       protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
           PrintWriter writer = resp.getWriter();
           writer.println("hello springboot servlet");
       }
   }
   ```

2. 编写启动类

   `@ServletComponentScan`： 在 springBoot 启动时会扫描@WebServlet，并将该类实例化

   ```java
   @SpringBootApplication
   @ServletComponentScan   // 在 springBoot 启动时会扫描@WebServlet，并将该类实例化
   public class Application {
   
       public static void main(String[] args) {
           SpringApplication.run(Application.class, args);
       }
   
   }
   ```

   

#### 通过方法注册Servlet 组件

1. 编写 servlet

   ```java
   public class HelloServlet extends HttpServlet {
       @Override
       protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
           PrintWriter writer = resp.getWriter();
           writer.println("hello springboot servlet");
       }
   }
   ```

2. 编写启动类

   + `ServletRegistrationBean`
   + `/`

   ```java
   @SpringBootApplication
   public class Application {
   
       public static void main(String[] args) {
           SpringApplication.run(Application.class, args);
       }
   
       @Bean
       public ServletRegistrationBean getServletRegistrationBean() {
           ServletRegistrationBean bean = 
               new ServletRegistrationBean(new HelloServlet());
           bean.addUrlMappings("/hello");
           return bean;
       }
   }
   ```



### 整合 Filter

#### 通过注解注册 Filter 组件

1. 编写 Filter
   + `@WebFilter`

```java
@WebFilter(urlPatterns = {"*.do", "*.jsp"})
public class HelloFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("into Filter.....");
        filterChain.doFilter(servletRequest, servletResponse);
        System.out.println("leave Filter.....");
    }

    @Override
    public void destroy() {

    }
}
```

2. 编写启动类

   + `@ServletComponentScan`

   ```java
   @SpringBootApplication
   @ServletComponentScan
   public class Application {
   
       public static void main(String[] args) {
           SpringApplication.run(Application.class, args);
       }
   }
   ```

#### 通过方法注册 Filter 组件

1. 编写 Filter

   ```java
   public class HelloFilter implements Filter {
       @Override
       public void init(FilterConfig filterConfig) throws ServletException {
   
       }
   
       @Override
       public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
           System.out.println("into Filter.....");
           filterChain.doFilter(servletRequest, servletResponse);
           System.out.println("leave Filter.....");
       }
   
       @Override
       public void destroy() {
   
       }
   }
   ```

   

2. 编写启动类

   + `FilterRegistrationBean`

   ```java
   @SpringBootApplication
   public class Application {
   
       public static void main(String[] args) {
           SpringApplication.run(Application.class, args);
       }
   
       //@Bean 是一个方法级别上的注解，主要用在配置类里
       /* 相当于一个
        *      <beans>
        *          <bean id="" class=""/>
        *      </beans>
        * */
   
       // 注册 servlet
       @Bean
       public ServletRegistrationBean getServletRegistrationBean() {
           ServletRegistrationBean bean = 
               new ServletRegistrationBean(new HelloServlet());
           bean.addUrlMappings("/hello.do");
           return bean;
       }
   
       // 注册 Filter
       @Bean
       public FilterRegistrationBean getFilterRegistrationBean() {
           FilterRegistrationBean bean = 
               new FilterRegistrationBean(new HelloFilter());
           // bean.addUrlPatterns(new String[]{"*.do", "*.jsp"});
           bean.addUrlPatterns("/hello.do");
           return bean;
       }
   }
   ```

   

### 整合 Listener

#### 通过注解注册 Listener 组件

1. 编写 Listener

   + `@WebListener`

   ```java
   @WebListener
   public class HelloListener implements ServletContextListener {
       @Override
       public void contextInitialized(ServletContextEvent sce) {
           System.out.println("Listener init.....");
       }
   
       @Override
       public void contextDestroyed(ServletContextEvent sce) {
           System.out.println("listener destroy.....");
       }
   }
   ```

2. 编写启动类

   + `@ServletComponentScan`

   ```java
   @SpringBootApplication
   @ServletComponentScan
   public class Application {
   
       public static void main(String[] args) {
           SpringApplication.run(Application.class, args);
       }
   }
   ```

   

#### 通过方法注册 Listener 组件

1. 编写 Listener

   ```java
   public class HelloListener implements ServletContextListener {
       @Override
       public void contextInitialized(ServletContextEvent sce) {
           System.out.println("Listener init.....");
       }
   
       @Override
       public void contextDestroyed(ServletContextEvent sce) {
           System.out.println("listener destroy.....");
       }
   }
   ```

2. 编写启动类

   + `ServletListenerRegistrationBean`

   + `@Bean`

   ```java
   @SpringBootApplication
   public class Application {
   
       public static void main(String[] args) {
           SpringApplication.run(Application.class, args);
       }
   
       @Bean
       public ServletListenerRegistrationBean getServletListenerRegistrationBean() {
           ServletListenerRegistrationBean bean =
                   new ServletListenerRegistrationBean(new HelloListener());
   
           return bean;
       }
   }
   ```



### 访问静态资源

1. springboot访问静态资源，默认有两个默认目录:

   + Spring-Data下 classpath/static目录 (src/mian/resources)

     > 1. classpath 即WEB-INF下面的classes目录 ，在springboot项目中包含src/main/resource 目录。
     >
     > 2. 修改访问路径：在 properties文件里面设置 spring.resources.static-locations
     > 3. `spring.resources.static-locations` 的默认值是：classpath:/META-INF/resources/,classpath:/resources/,classpath:/static/,classpath:/public/

     

   + ServletContext 根目录下( src/main/webapp )

     > 1. `ServletContext`官方叫servlet上下文。是一个全局的储存信息的空间，服务器开始，其就存在，服务器关闭，其才释放。
     >
     > 2. `ServletContext`以web的已知路径为根路径。
     >
     > 3. ServletContent 根目录就是 `src/main/webapp`



1. classpath/static 的目录

   > 注意目录名称必须是 `static`

```java
|-- src
    |-- main
    	|-- java			Java类
    	|-- resources
    		|-- static		 静态资源目录
    			|-- images   		http://localhost:8080/images/1.jpg
    			|-- index.html      http://localhost:8080/index.html
    		|-- templates    模板文件目录
    		|-- application.properties 核心配置文件
    |-- test	测试代码
```



2. ServletContext 根目录下

   > src/main/webapp 目录名称必须要 `webapp`

```java
|-- src
    |-- main
    	|-- java			Java类
    	|-- resources
    		|-- static		 静态资源目录
    		|-- templates    模板文件目录
    		|-- application.properties 核心配置文件
    	|-- webapp
    		|-- images		http://localhost:8080/images/1.jpg
    		|-- index.html  http://localhost:8080/index.html
    |-- test	测试代码
```

> IDEA SpringBoot访问不到webapp下的内容
>
> + 设置：
>
>   Edit Configurations  —>   Environment —> Working dIrectory 设置成 $MODULE_WORKING_DIR$
>
> + 原因：
>
>   Working directory 是在程序中通过相对路径访问文件的起始点，在 webapp 中需要访问 html（jsp）文件的话，必须将起点设置到当前模块路径下，否则就会找不到这个文件，就会报404错误





### 文件上传下载

#### 文件上传

1.  前端 view

   ```html
   <!-- 单文件上传 -->
   <form method="post" action="/upload" enctype="multipart/form-data">
       文件上传：<input type="file" name="file"><br>
       <input type="submit" value="submit">
   </form>
   
   
   <!-- 多文件上传 -->
   <form method="post" action="/multfile" enctype="multipart/form-data">
       文件上传：<input type="file" name="files"><br>
       <input type="file" name="files"><br>
       <input type="file" name="files"><br>
       <input type="submit" value="submit">
   </form>
   ```

   

2. 编写 Controller

   ```java
   @Controller
   public class FileUploadController {
   
       @Value("${file.upload.filepath}")
       private String filepath;
   
       // 处理单文件上传
       @RequestMapping("upload")
       @ResponseBody
       public String fileUpload(MultipartFile file) throws IOException {
           if (file.isEmpty()) {
               return "false";
           }
   
           String filename = file.getOriginalFilename();
           System.out.println(filename);
   
           // file.transferTo(new File("E:/" + filename));
   
           InputStream in = file.getInputStream();
           OutputStream out = new FileOutputStream(new File("E:/" + filename));
   
           byte[] buf = new byte[4096];
           int len;
   
           while (-1 != (len = in.read(buf))) {
               out.write(buf, 0, len);
           }
           in.close();
           out.close();
           return "ok!";
       }
   
   
       // 处理多文件上传
       @RequestMapping("multfile")
       @ResponseBody
       public String MultFileUpload(List<MultipartFile> files) throws IOException {
   
           System.out.println(files.size());  
           for (MultipartFile file : files) {
               if (!file.isEmpty()) {
                   String filename = file.getOriginalFilename();
                   File dest = new File(filepath + filename);
                   // 判断文件在所在目录是否存在，如果不存在就创建对应的目录。
                   if (!dest.getParentFile().exists()) {
                       dest.getParentFile().mkdir();
                   }
                   file.transferTo(dest);
               }
           }
           return "ok!";
       }
   }
   ```

   > 

3. 编写启动类

   ```java
   @SpringBootApplication
   public class Application {
   
       public static void main(String[] args) {
           SpringApplication.run(Application.class, args);
       }
   }
   ```



4. 核心配置文件

   ```properties
   file.upload.filepath=E:/test/
   ```

   

#### 文件下载

1.  前端 view

   ```java
   <body>
   	<a href="/download?fileName=1.jpg">1.jpg</a>
   </body>
   ```

2. 编写 Controller

   ```java
   @Controller
   public class FileDownloadController {
   
       @RequestMapping("download")
       @ResponseBody
       public String fileDownload(String fileName, HttpServletResponse res) throws IOException {
           // 设置响应流中文件进行下载
           res.setHeader("Content-Disposition", "attachment;filename=" + fileName);
   
           FileInputStream in = new FileInputStream(new File("E:/img/" + fileName));
           ServletOutputStream out = res.getOutputStream();
   
           byte[] buf = new byte[2048];
           int len;
           while (-1 != (len = in.read(buf))) {
               out.write(buf, 0, len);
           }
           out.flush();
           in.close();
           out.close();
   
           return "ok";
       }
   }
   ```

3. 编写启动类

   ```java
   @SpringBootApplication
   public class Application {
   
       public static void main(String[] args) {
           SpringApplication.run(Application.class, args);
       }
   }
   ```



#### 上传文件大小设置

```java
public class MultipartProperties {
    private boolean enabled = true;
    private String location;
    private DataSize maxFileSize = DataSize.ofMegabytes(1L);
    private DataSize maxRequestSize = DataSize.ofMegabytes(10L);
    private DataSize fileSizeThreshold = DataSize.ofBytes(0L);
    private boolean resolveLazily = false;
}
```



1. 根据不同版本，对应的设置值不一样

   + Spring Boot 1.3.x and earlier
     + `multipart.maxFileSize`
     + `multipart.maxRequestSize`
   + Spring Boot 1.4.x and 1.5.x
     - `spring.http.multipart.maxFileSize`
     - `spring.http.multipart.maxRequestSize`
   + Spring Boot 2.x
     - `spring.servlet.multipart.maxFileSize`
     - `spring.servlet.multipart.maxRequestSize`

   

2. 核心配置文件  application.properties 中

   ```java
   # 如果不限制大小，则设置为-1即可
   
   # 设置单个上传文件的大小
   spring.servlet.multipart.maxFileSize=200MB
   
   # 设置一次请求上传文件的总容量
   spring.servlet.multipart.maxRequestSize=200MB
   ```

   

3. Java 代码

   + 一个类，加上`@Configuration`文件进行说明，这是一个配置类。

   ```java
   @Configuration
   public class MultFileConfig {
   
       @Bean
       public MultipartConfigElement getMultipartConfigElement() {
   
           MultipartConfigFactory factory = new MultipartConfigFactory();
   
           /**
            * DataSize
            *      ofKilobytes：KB
            *      ofMegabytes：MB
            *      ofBytes：字节
            */
           
           // 设置单个文件的大小。
           factory.setMaxFileSize(DataSize.ofMegabytes(100));
   
           // 设置总上传的数据大小。
           factory.setMaxRequestSize(DataSize.ofMegabytes(200));
           return factory.createMultipartConfig();
       }
   }
   ```



## 整合视图层技术

### 整合 jsp

1. 修改 pom 文件，添加坐标

   ```xml
   <!--引入 Spring Boot 内嵌的 Tomcat 对 JSP 的解析包，不加解析不了 jsp 页面-->
   <!--如果只是使用 JSP 页面，可以只添加该依赖 -->
   <dependency>
       <groupId>org.apache.tomcat.embed</groupId>
       <artifactId>tomcat-embed-jasper</artifactId>
   </dependency>
   
   <!-- 添加jstl标签库依赖模块 -->
   <dependency>
       <groupId>javax.servlet</groupId>
       <artifactId>jstl</artifactId>
   </dependency>
   
   <!--如果要使用 servlet 添加该以下两个依赖-->
   <!-- servlet 依赖的 jar 包-->
   <dependency>
        <groupId>javax.servlet</groupId>
        <artifactId>javax.servlet-api</artifactId>
   </dependency>
   <dependency>
        <groupId>javax.servlet.jsp</groupId>
        <artifactId>javax.servlet.jsp-api</artifactId>
        <version>2.3.1</version>
   </dependency>
   ```

   ```java
    <!--
        不添加此配置也行
        SpringBoot 要求 jsp 文件必须编译到指定的 META-INF/resources 目录下才能访问，
        否则访问不到
   -->
   <build>
       <resources>
           <resource>
               <directory>src/main/webapp</directory>
               <!--指定编译到META-INF/resources-->
               <targetPath>META-INF/resources</targetPath>
               <!--指定源文件夹中的哪个资源要编译进行-->
               <includes>
                   <include>*.*</include>
               </includes>
           </resource>
       </resources>
   <build>
   ```

   ​	

2. application.properties 中 配置 Spring MVC 视图解析器

   ```properties
   # 配置 SpringMVC 视图解析器
   # ：/ 表示目录为 src/main/webapp
   spring.mvc.view.prefix=/WEB-INF/jsp/
   spring.mvc.view.suffix=.jsp
   ```



3. 创建 Controller

   ```java
   @Controller
   public class UserController {
   
       @RequestMapping("show")
       public String show(Model model) {
           System.out.println("into show...");
           ArrayList<User> list = new ArrayList<>();
           list.add(new User(1, "小佛", 23));
           list.add(new User(2, "大龙虾", 22));
           model.addAttribute("list", list);
           return "show";
       }
   }
   ```

   > 注意配置Working  directory：`$MODULE_WORKING_DIR$`

4. 创建 jsp

   + src/main/webapp/WEB-INF/jsp/show.jsp

   ```jsp
   <%@ page language="java" pageEncoding="utf-8" isELIgnored="false"%>
   <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
   <!DOCTYPE html>
   <html lang="en">
   <head>
       <meta charset="UTF-8">
       <title>jsp</title>
   </head>
   
   <body>
       <table border="1" cellspacing="0" align="center">
           <tr>
               <th>ID</th>
               <th>name</th>
               <th>age</th>
           </tr>
           <c:forEach items="${list}" var="user">
               <tr>
                   <td>${user.id}</td>
                   <td>${user.name}</td>
                   <td>${user.age}</td>
               </tr>
           </c:forEach>
       </table>
   </body>
   </html>
   ```



6. 创建启动类

   ```java
   @SpringBootApplication
   public class Application {
       public static void main(String[] args) {
           SpringApplication.run(Application.class, args);
       }
   }
   ```

   

### 整合 freemarker

1. 修改 pom 添加坐标

   ```xml
   <!-- freemarker 启动器的坐标 -->
   <dependency>
       <groupId>org.springframework.boot</groupId>
       <artifactId>spring-boot-starter-freemarker</artifactId>
   </dependency>
   ```

   

2. 编写视图

   + `templates` 这个名词是 Spring Boot约定的，如果有需要，可以通过配置 `application.yml` 修改

     ```yml
     spring:
       freemarker:
         template-loader-path: classpath:/templates/

   + `.ftl` 是 `Freemarker` 模板文件后缀

   + 使用 `${变量名}` 可以输出 `controller` 中返回的对象

   + 2.2.x 版本后缀改为了`.ftlh`，继续保持以前的.ftl后缀名，可以在application.propertoes中配置

     ```properties
     spring.freemarker.suffix=.ftl
     ```

   ```html
   <!DOCTYPE html>
   <html lang="en">
   <head>
       <meta charset="UTF-8">
       <title>Title</title>
   </head>
   <body>
       <table border="1" cellspacing="0" align="center">
           <tr>
               <th>ID</th>
               <th>name</th>
               <th>age</th>
           </tr>
   
           <#list list as user>
               <tr>
                   <td>${user.id}</td>
                   <td>${user.name}</td>
                   <td>${user.age}</td>
               </tr>
           </#list>
   </table>
   </body>
   ```



3. 创建 Controller

   + freemarker模板文件需要通过  Controller 的 `HandleMethod` 访问

   ```java
   @Controller
   public class UserController {
   
       @RequestMapping("show")
       public String show(Model model) {
           System.out.println("into show...");
           ArrayList<User> list = new ArrayList<>();
           list.add(new User(1, "小佛", 23));
           list.add(new User(2, "大龙虾", 22));
           model.addAttribute("list", list);
           return "show";
       }
   }
   ```



5. 创建启动器

   ```java
   @SpringBootApplication
   public class Application {
   
       public static void main(String[] args) {
           SpringApplication.run(Application.class, args);
       }
   }
   ```

   

### 整合 Thymeleaf

```properties
spring.thymeleaf.cache: false #使用Thymeleaf模板引擎，关闭缓存
```



1. 修改 pom 文件添加坐标

   ```xml
   <!-- thymeleaf 启动器 -->
   <dependency>
       <groupId>org.springframework.boot</groupId>
       <artifactId>spring-boot-starter-thymeleaf</artifactId>
   </dependency>
   ```





2. 创建视图 .html

   + 目录位置：`src/main/resources/templates`
   + `templates`：该目录是安全的。意味着该目录下的内容是不允许外界直接访问的。
   + `Thymelaef ` 是通过特定语法对 html 的标记做渲染。
   + html中加入 `xmlns:th="http://www.thymeleaf.org"` 语法提示

   ```html
   <!DOCTYPE html>
   <html lang="en" xmlns:th="http://www.thymeleaf.org">
   <head>
       <meta charset="UTF-8">
       <title>Thymeleaf</title>
   </head>
   <body>
       <span th:text = HelloSpring-Boot...></span>
       <hr>
       <span th:text="${msg}"></span>
   </body>
   </html>
   ```

   > `th:text`中必须是合法的表达式，如果要 `th:text `显示纯文本，则这些文本需要使用`引号`括起来。不过，下列情况下可以不用写`引号`：( 单双引号都 ok)
   >
   > 文本中只包含字母(A~Z和a~z)，数字(0~9)，中括号（[]），点符号（.），连字符（-）,和下横线（_）。
   >
   > 
   >
   > 不允许`空格`，`逗号`等。
   >
   > 不加 `引号`，`空格` 之不显示，加了引号有 空格 直接  `TemplateProcessingException`(不使用变量时)



3. 编写 Controller

   + Thymeleaf 模板文件需要通过  Controller 的 `HandleMethod` 访问

   ```java
   @Controller
   public class MyController {
   
       @RequestMapping("show")
       public String show(Model model) {
           model.addAttribute("msg", "Hello Thymeleaf...");
           return "show";
       }
   }
   ```



4. 编写启动类

   ```java
   @SpringBootApplication
   public class Application {
   
       public static void main(String[] args) {
           SpringApplication.run(Application.class, args);
       }
   }
   ```



## Thymeleaf基本用法

### 引入提示

在html页面中引入thymeleaf命名空间，即，此时在html模板文件中动态的属性使用th:命名空间修饰 。

```html
<html lang="en" xmlns:th="http://www.thymeleaf.org">
```

### 变量表达式(获取变量值)

获取变量值用`${}`,对于javaBean的话使用`变量名.属性名`方式获取,这点和EL表达式一样

1. `th:text`：在页面中输出值，对内容的原样输出(可解析预定义实体)

   `th:utext`可以进行html标签输出。

```html
<!-- 输出普通字字符串 -->
<span th:text = 'Hello'></span>

<!-- 输出变量 -->
<span th:text="${msg}"></span>

<!-- 输出JavaBean的属性 -->
<span th:text="${user.name}"></span><br>
<!-- 与字符串拼接 -->
<span th:text="'Hello&nbsp;' + ${user.name}"></span>

<!-- 与字符串拼接，多个空格折合一个 -->
<div th:text="'Hello，    '+${user.name}+'!!'"></div>
```



2. `th:value`：可以将一个值放入到 input 标签的 value 中

   ```html
   <input th:value="${user.name}">
   ```

   

### 字符串操作

1. 注意语法：

   + 调用内置对象一定要用`#`
   + 大部分的内置对象都以 s 结尾 strings、numbers、dates

   

| 操作                                                         | 描述                                                         |
| ------------------------------------------------------------ | ------------------------------------------------------------ |
| `${#strings.isEmpty(key)}`                                   | 判断字符串是否为空，如果为空返回 true，否则返回 false        |
| `${#strings.contains(msg,'T')}`                              | 判断字符串是否包含指定的子串，如果包含返回 true，否则返回 false |
| `${#strings.startsWith(msg,'a')}`                            | 判断当前字符串是否以子串开头，如果是返回 true，否则返回 false |
| `${#strings.endsWith(msg,'a')}`                              | 判断当前字符串是否以子串结尾，如果是返回 true，否则返回 false |
| `${#strings.length(msg)}`                                    | 返回字符串的长度                                             |
| `${#strings.indexOf(msg,'h')}`                               | 查找子串的位置，并返回该子串的下标，如果没找到则返回-1       |
| `${#strings.substring(msg,13)} `<br>`${#strings.substring(msg,13,15)}` | 截取子串                                                     |
| `${#strings.toUpperCase(msg)}` <br>`${#strings.toLowerCase(msg)}` | 字符串转大小写。                                             |



### 日期格式化处理

|                                     |                                                |
| ----------------------------------- | ---------------------------------------------- |
| `${#dates.format(key)}`             | 格式化日期，默认的以浏览器默认语言为格式化标准 |
| `${#dates.format(key,'yyy/MM/dd')}` | 按照自定义的格式做日期转换                     |
| `${#dates.year(key)}`               | year：取年                                     |
| `${#dates.month(key)}`              | Month：取月                                    |
| `${#dates.day(key)}`                | Day：取日                                      |



### 条件判断

1. `th:if`

   `th:unless`正好相反，是除去的意思。

   ```html
   <span th:if="${sex} == '男'">
   	性别：男
   </span>
   <span th:if="${sex} == '女'">
   	性别：女
   </span>
   ```

   

2. `th:switch`

   ```html
   <div th:switch="${id}">
       <span th:case="1">ID 为 1</span>
       <span th:case="2">ID 为 2</span>
       <span th:case="3">ID 为 3</span>
   </div>
   ```



### 迭代遍历

1. `th:each`

   ```java
   @Controller
   public class UserController {
   
       @RequestMapping("show")
       public String show(Model model) {
           System.out.println("into show...");
           ArrayList<User> list = new ArrayList<>();
           list.add(new User(1, "小佛", 23));
           list.add(new User(2, "大龙虾", 22));
           model.addAttribute("list", list);
           return "show";
       }
   }
   ```

   ```html
   <table border="1">
       <tr>
           <th>ID</th>
           <th>Name</th>
           <th>Age</th>
       </tr>
       <tr th:each="u : ${list}">
           <td th:text="${u.id}"></td>
           <td th:text="${u.name}"></td>
           <td th:text="${u.age}"></td>
       </tr>
   </table>
   ```



2. `ht:each 状态变量`

   状态变量属性:

   + `index`:当前迭代器的索引 从 0 开始
   + `count`:当前迭代对象的计数 从 1 开始
   + `size`:被迭代对象的长度 
   + `even`/`odd`:布尔值，当前循环是否是偶数/奇数 从 0 开始 
   + `first`:布尔值，当前循环的是否是第一条，如果是返回 true 否则返回 false
   + `last`:布尔值，当前循环的是否是最后一条，如果是则返回 true 否则返回 false

   ```html
   <table border="1">
       <tr>
           <th>ID</th>
           <th>Name</th>
           <th>Age</th>
           <th>Index</th>
           <th>Count</th>
           <th>Size</th>
           <th>Even</th>
           <th>Odd</th>
           <th>First</th>
           <th>lase</th>
       </tr>
           <tr th:each="u,var : ${list}">
           <td th:text="${u.id}"></td>
           <td th:text="${u.name}"></td>
           <td th:text="${u.age}"></td>
           <td th:text="${var.index}"></td>
           <td th:text="${var.count}"></td>
           <td th:text="${var.size}"></td>
           <td th:text="${var.even}"></td>
           <td th:text="${var.odd}"></td>
           <td th:text="${var.first}"></td>
           <td th:text="${var.last}"></td>
       </tr>
   </table>
   ```

   

3. `th:each` 迭代 `Map`

   ```java
   @RequestMapping("/show")
   public String showInfo4(Model model){
       Map<String, User> map = new HashMap<>();
       map.put("u1", new User(1,"张三",20));
       map.put("u2", new User(2,"李四",22));
       map.put("u3", new User(3,"王五",24));
       model.addAttribute("map", map);
       return "show";
   }
   ```

   ```html
   <table border="1">
       <tr>
           <th>ID</th>
           <th>Name</th>
           <th>Age</th>
       </tr>
       <tr th:each="maps : ${map}">
           <td th:text="${maps}"></td>
       </tr>
   </table>
   
   <table border="1">
       <tr>
           <th>ID</th>
           <th>Name</th>
           <th>Age</th>
       </tr>
       <tr th:each="maps : ${map}">
           <td th:each="entry:${maps}"
           	th:text="${entry.value.id}" >
           </td>
           <td th:each="entry:${maps}"
           	th:text="${entry.value.name}">
           </td>
           <td th:each="entry:${maps}"
           	th:text="${entry.value.age}">
           </td>
       </tr>
   </table>
   ```

   

### 域对象操作

1. `HttpServletRequest`

   ```java
   request.setAttribute("req", "HttpServletRequest");
   
   Request:<span th:text="${#httpServletRequest.getAttribute('req')}"></span>
   ```



2. `HttpSession`

   ```java
   request.getSession().setAttribute("sess", "HttpSession");
   
   Session:<span th:text="${session.sess}"></span>
   ```

   

3. `ServletContext`

   ````java
   request.getSession().getServletContext().setAttribute("app", "Application");
   
   Application: <span th:text="${application.app}"></span>
   ````

   

### URL 表达式 

+ `th:href`
+ `th:src`



1. url 表达式语法

   基本语法：`@{}`



2. URL 类型

   + 绝对路径

     ```html
     <a th:href="@{http://www.baidu.com}">绝对路径</a>
     ```

   + 相对路径

     + 相对于当前项目的根

       相对于项目的上下文的相对路径

       ```html
       <a th:href="@{/show}">相对路径</a>
       ```

     + 相对于服务器路径的根

       ```html
       <a th:href="@{~/project/resourcename}">相对于服务器的根</a>
       ```

       

3. 在 url 中实现参数传递

   ```html
   <a th:href="@{/param(id=1,name=ixfosa)}">相对路径-传参</a>
   ```

   ````java
   @RequestMapping("param")
   @ResponseBody
   public Object param(User user) {
       user.setAge(23);
       return user;
   }
   // {"id":3,"name":"ixfosa","age":23}
   ````

   

4. 在 url 中通过 restful 风格进行参数传递

   ```html
   <a th:href="@{/restful/{id}/{name}(id=6, name=zhong)}">相对路径-传参-restful</a>
   ```

   ```java
   @RequestMapping("restful/{id}/{name}")
   @ResponseBody
   public Object restful(User user) {
       user.setAge(21);
       return user;
   }
   // {"id":6,"name":"zhong","age":21}
   ```



###  三元表达式

```html
<tr th:class="${row.even}? 'even' : 'odd'"></tr>
```



## 整合持久层技术

+ SpringBoot 整合 SpringMVC+MyBatis
+ SpringBoot 整合 SpringMVC+JPA



### 整合MyBatis

完成 CRUD

#### 搭建环境(idea)

1. 选择类型 Spring Initializr
2. 指定 GAV 及 pom 配置信息

   + packaging：`jar`
3. 选择 Spring Boot 版本及依赖，会根据选择的依赖自动添加起步依赖并进行自动配置
   + Web：Spring Web 创建的时 web 工程
   + Tempplate Engines：Thymeleaf
   + SQL：Mybatis Framework，MySQL Driver
4. 修改 Content Root 路径及文件所在目录



#### pom文件介绍

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.5.0</version>
        <relativePath/> <!-- lookup parent from repository -->
    </parent>
    <groupId>top.ixfosa.springboot</groupId>
    <artifactId>ch10-springboot-mybatis</artifactId>
    <version>0.0.1</version>
    <name>ch10-springboot-mybatis</name>

    <properties>
        <java.version>1.8</java.version>
    </properties>

    <dependencies>
        <!-- thymeleaf 启动器 -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-thymeleaf</artifactId>
        </dependency>

        <!-- web 启动器 -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>

        <!-- Mybatis启动器 -->
        <dependency>
            <groupId>org.mybatis.spring.boot</groupId>
            <artifactId>mybatis-spring-boot-starter</artifactId>
            <version>2.2.0</version>
        </dependency>

        <!-- mysql数据库驱动 -->
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <scope>runtime</scope>
        </dependency>

        <!-- druid数据库连接池 -->
        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>druid</artifactId>
            <version>1.1.10</version>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>
</project>
```



#### 核心配置文件

```properties
spring.datasource.driverClassName=com.mysql.cj.jdbc.Driver
spring.datasource.url=jdbc:mysql://localhost:3306/springdata?useUnicode=true&characterEncoding=utf8
spring.datasource.username=root
spring.datasource.password=ixfosa

# 数据库连接池
spring.datasource.type=com.alibaba.druid.pool.DruidDataSource

# mybatis 类型别名
# @Alias(value = "别名")
mybatis.type-aliases-package=top.ixfosa.domain

# 映射文件路径
mybatis.mapper-locations=classpath:mapper/*.xml

# 控制台输出执行sql log-impl:
mybatis.configuration.log-impl=org.apache.ibatis.logging.stdout.StdOutImpl
```

#### 数据模型

```java
public class User {
    private Integer id;
    private String name;
    private Integer age;

    public User(Integer id, String name, Integer age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }
    
    // ...
}
```



#### 创建接口 Dao

```java
@Repository
public interface UserDao {
    void add(User user);
    void del(Integer id);
    void upd(User user);
    User selById(Integer id);
    List<User> sel();
}
```

#### Mapper映射文件

src/main/resources/mapper/UserMapper.xml

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<mapper namespace="top.ixfosa.dao.UserDao">
    <insert id="add" parameterType="user">
        insert into user(id, name, age)
        values
        (default , #{name}, #{age});
    </insert>

    <select id="sel" resultType="user">
        select * from user
    </select>

    <delete id="del">
        delete from user where id = #{id}
    </delete>

    <update id="upd">
        update user set name = #{name}, age = #{age} where id = #{id}
    </update>

    <select id="selById" resultType="user">
        select * from user where id = #{id}
    </select>
</mapper>
```



#### 创建接口 Service

```java
public interface UserService {
    void addUser(User user);
    void delUser(Integer id);
    void updUser(User user);
    User selById(Integer id);
    List<User> selUser();
}
```

#### 创建实现 ServiceImpl

```java
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public void addUser(User user) {
        userDao.add(user);
    }

    @Override
    public void delUser(Integer id) {
        userDao.del(id);
    }

    @Override
    public void updUser(User user) {
        userDao.upd(user);
    }

    @Override
    public User selById(Integer id) {
        return userDao.selById(id);
    }

    @Override
    public List<User> selUser() {
        return userDao.sel();
    }
}
```

#### 创建Controller

```java
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/{page}")
    public String showPage(@PathVariable String page) {
        System.out.println(page);
        return page;
    }

    @RequestMapping("/addu")
    public String add(User user) {
        userService.addUser(user);
        return "redirect:selu";
    }

    @RequestMapping("/selu")
    public String sel(Model model) {
        List<User> list = userService.selUser();
        model.addAttribute("list", list);
        return "show";
    }

    @RequestMapping("/delu/{id}")
    public String del(@PathVariable Integer id) {
        userService.delUser(id);
        return "redirect:/selu";
    }

    @RequestMapping("/findu/{id}")
    public String selById(@PathVariable Integer id, Model model) {
        User user = userService.selById(id);
        model.addAttribute(user);
        return "upd";
    }

    @RequestMapping("/updu")
    public String upd(User user) {
        userService.updUser(user);
        return "redirect:selu";
    }
}
```



#### 创建 View

/src/main/resources/templates

1. add.xml

   ```html
   <body>
       <form th:action="@{/addu}" method="post">
           姓名：<input type="text" name="name"><br>
           年龄：<input type="text" name="age"><br>
           <input type="submit" value="add">
       </form>
   </body>
   ```

2. show.html

   ```html
   <body>
       <table border="1" cellspacing="0" align="center" width="400">
           <tr>
               <th>id</th>
               <td>name</td>
               <td>age</td>
               <td>del</td>
               <td>upd</td>
           </tr>
           <tr th:each="user : ${list}">
               <td th:text="${user.id}"></td>
               <td th:text="${user.name}"></td>
               <td th:text="${user.age}"></td>
               <td><a th:href="@{/delu/{id}(id=${user.id})}">del</a></td>
               <td><a th:href="@{/findu/{id}(id=${user.id})}">upd</a></td>
           </tr>
           <tr align="center">
               <td colspan="5"><a th:href="@{/add}">add</a> </td>
           </tr>
       </table>
   </body>
   ```

3. upd.html

   ```html
   <body>
       <form th:action="@{/updu}" method="post">
           <input type="hidden" name="id" th:value="${user.id}">
           姓名：<input th:value="${user.name}" type="text" name="name"><br>
           年龄：<input th:value="${user.age}"  type="text" name="age"><br>
           <input type="submit" value="upd">
       </form>
   </body>
   ```

4. err.html

   ```html
   <body>
       <h3>err...</h3>
   </body>
   ```



#### 起动器

+ `@MapperScan`

  作用：指定要变成实现类的接口所在的包，然后包下面的所有接口在编译之后都会生成相应的实现类

+ `@Mapper`

  作用：在接口类上添加了@Mapper，在编译之后会生成相应的接口实现类

  如果想要每个接口都要变成实现类，那么需要在每个接口类上加上@Mapper注解



```java
@SpringBootApplication
@MapperScan("top.ixfosa.dao")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
```



### 整合JPA

#### 搭建环境(idea)

1. 选择类型 Spring Initializr
2. 指定 GAV 及 pom 配置信息

   + packaging：`jar`
3. 选择 Spring Boot 版本及依赖，会根据选择的依赖自动添加起步依赖并进行自动配置
   + Web：Spring Web 创建的时 web 工程
   + SQL：Spring Data JPA，MySQL Driver
4. 修改 Content Root 路径及文件所在目录



#### pom文件介绍

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.5.0</version>
        <relativePath/> <!-- lookup parent from repository -->
    </parent>
    <groupId>top.ixfosa.springboot</groupId>
    <artifactId>ch11-springboot-jpa</artifactId>
    <version>0.0.1</version>
    <name>ch11-springboot-jpa</name>
    <properties>
        <java.version>1.8</java.version>
    </properties>
    <dependencies>
        <!-- spring data jpa 启动器 -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jpa</artifactId>
        </dependency>
        
          <!-- web 启动器 -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
			
          <!-- mysql 驱动 -->
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
        </dependency>
		
          <!-- 数据库连接池 -->
        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>druid</artifactId>
            <version>1.1.10</version>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>
</project>
```



#### 核心配置文件

 ```properties
 spring.datasource.driverClassName=com.mysql.cj.jdbc.Driver
 spring.datasource.url=jdbc:mysql://localhost:3306/springdata?useUnicode=true&characterEncoding=utf8
 spring.datasource.username=root
 spring.datasource.password=ixfosa
 
 spring.datasource.type=com.alibaba.druid.pool.DruidDataSource
 
 # 正向工程
 spring.jpa.hibernate.ddl-auto=update
 
 # 显示 sql
 spring.jpa.show-sql=true
 ```



#### 数据模型

```java
@Entity
@Table(name = "user")
public class User implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private Integer age;

    public User(Integer id, String name, Integer age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public User() {
    }
    
	// ...
}
```



#### 创建接口 Dao

```java
public interface UserDao extends JpaRepository<User, Integer> {
    
}
```



#### 创建接口 Service

```java
public interface UserService {
    void addUser(User user);
    void delUser(Integer id);
    void updUser(User user);
    User selById(Integer id);
    List<User> selUser();
}
```



#### 创建接口 ServiceIm

```java
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public void addUser(User user) {
        userDao.save(user);
    }

    @Override
    public void delUser(Integer id) {
        userDao.deleteById(id);
    }

    @Override
    public void updUser(User user) {
        userDao.save(user);
    }

    @Override
    public User selById(Integer id) {
        return userDao.findById(id).get();
    }

    @Override
    public List<User> selUser() {
        return userDao.findAll();
    }
}
```



#### 创建Controller

```java
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/addu")
    @ResponseBody
    public String add(User user) {
        userService.addUser(user);
        return "ok";
    }

    @RequestMapping("/selu")
    @ResponseBody
    public Object sel() {
        List<User> list = userService.selUser();
        return list;
    }

    @RequestMapping("/delu/{id}")
    @ResponseBody
    public String del(@PathVariable Integer id) {
        userService.delUser(id);
        return "ok";
    }

    @RequestMapping("/findu/{id}")
    @ResponseBody
    public Object selById(@PathVariable Integer id) {
        User user = userService.selById(id);
        return user;
    }

    @RequestMapping("/updu")
    @ResponseBody
    public String upd(User user) {
        userService.updUser(user);
        return "ok";
    }
}
```



#### 起动器

```java
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
```



## 服务端表单数据校验

### 数据校验

1. 数据校验：就是在应用程序中，对输入进来得数据做语义分析判断，阻挡不符合规则得数据，放行符合规则得数据，以确保被保存得数据符合我们得数据存储规则。

2. SpringMVC中：

   + Spring 自带的验证框架，
   + 利用 JSR 实现。JSR 是一个规范，提供了完整得一套 API，通过标注给对象属性添加约束。

3. Hibernate Validator 就是 对JSR 规范所有注解的具体实现，以及一些附加的约束注解。用户还可以自定义约束注解。Hibernate Validator提供得注解如下：

   | 注解                            | 作用目标                                          | 检查规则                                                     |
   | ------------------------------- | ------------------------------------------------- | ------------------------------------------------------------ |
   | @Length(min=, max=)             | 属性（String）                                    | 检查字符串长度是否符合范围                                   |
   | @Max(value=)                    | 属性（以 numeric 或者 string 类型来表示一个数字） | 检查值是否小于或等于最大值                                   |
   | @Min(value=)                    | 属性（以 numeric 或者 string 类型来表示一个数字） | 检查值是否大于或等于最小值                                   |
   | @NotNull                        | 属性                                              | 检查值是否非空（not null）                                   |
   | @Future                         | 属性（date 或 calendar）                          | 检查日期是否是未来                                           |
   | @Pattern(regex="regexp", flag=) | 属性（string）                                    | 检查属性是否与给定匹配标志的正则表达式相匹配                 |
   | @Range(min=, max=)              | 属性（以 numeric 或者 string 类型来表示一个数字） | 检查值是否在最小和最大值之间（包括临界值）                   |
   | @Size(min=, max=)               | 属性（array，collection，map）                    | 检查元素大小是否在最小和最大值之间（包括临界值）             |
   | @AssertFalse                    | 属性                                              | 检查方法的演算结果是否为 false（对以代码方式而不是注解表示的约束很有用） |
   | @AssertTrue                     | 属性                                              | 检查方法的演算结果是否为 true（对以代码方式而不是注解表示的约束很有用） |
   | @Valid                          | 属性（object）                                    | 对关联对象递归进行验证。如果对象是集合或数组，就递归地验证其元素；如果对象是 Map，则递归验证其值元素 |
   | @Email                          | 属性（String）                                    | 检查字符串是否符合有效的 email 地址规范                      |
   | @Past                           | 属性（date 或 calendar）                          | 检查日期是否是过去                                           |



### pom.xml中引入依赖

```java
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-thymeleaf</artifactId>
    </dependency>

    <dependency>
        <groupId>org.springframework.boot</groupId>
    	<artifactId>spring-boot-starter-web</artifactId>
    </dependency>

    <dependency>
        <groupId>org.hibernate</groupId>
        <artifactId>hibernate-validator</artifactId>
        <version>7.0.1.Final</version>
    </dependency>
</dependencies>
```



+ java.lang.IllegalStateException: Neither BindingResult nor plain target object for bean name 'user' available as request attribute



## 异常处理

### 自定义错误页面

1. SpringBoot 默认的处理异常的机制：SpringBoot 默认的已经提供了一套处理异常的机制。 一旦程序中出现了异常 SpringBoot 会像/error 的 url 发送请求。

2. 在 springBoot 中提供了一个 叫 `BasicExceptionController` 来处理/error 请求，然后跳转到默认显示异常的页面来展示异常 信息。

   ```html
   Whitelabel Error Page
   This application has no explicit mapping for /error, so you are seeing this as a fallback.
   
   Mon Jun 07 14:20:55 CST 2021
   There was an unexpected error (type=Not Found, status=404).
   ```

   

3. 如 果 我 们 需 要 将 所 有 的 异 常 同 一 跳 转 到 自 定 义 的 错 误 页 面 ， 需 要 再 src/main/resources/templates 目录下创建 `error.html` 页面。注意：名称必须叫 `error`

   ```html
   <!DOCTYPE html>
   <html lang="en" xmlns:th="http://www.thymeleaf.org">
   <head>
       <meta charset="UTF-8">
       <title>Title</title>
   </head>
   <body>
       出错了。。。
       <span th:text="${exception}"></span>
   </body>
   </html>
   ```

   

### @ExceptionHandle处理异常

#### Controller

```java
@Controller
public class ExceptionController {

    @RequestMapping("show1")
    public String show1() {
        String str = null;

        str.length();
        return "index";
    }

    @RequestMapping("show2")
    public String show2() {

        int a = 1 / 0;
        System.out.println("a = " + a);
        return "index";
    }

    /**
     * java.lang.ArithmeticException
     * 该方法需要返回一个 ModelAndView：目的是可以让我们封装异常信息以及视图的指定
     * 参数 Exception e:会将产生异常对象注入到方法中
     */
    @ExceptionHandler(ArithmeticException.class)
    public ModelAndView arithmeticExceptionHandler(Exception e) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("error", e.toString());
        mv.setViewName("ArithmeticException");
        return mv;
    }

    @ExceptionHandler(NullPointerException.class)
    public ModelAndView nullPointerExceptionHandler(Exception e) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("error", e.toString());
        mv.setViewName("NullPointerException");
        return mv;
    }
}
```



#### view

1. ArithmeticException

   ```html
   <!DOCTYPE html>
   <html lang="en" xmlns:th="http://www.thymeleaf.org">
   <head>
       <meta charset="UTF-8">
       <title>ArithmeticException</title>
   </head>
   <body>
   <span th:text="${error}"></span>
   </body>
   </html>
   ```

2. NullPointerException

   ```html
   <!DOCTYPE html>
   <html lang="en" xmlns:th="http://www.thymeleaf.org">
   <head>
       <meta charset="UTF-8">
       <title>NullPointerException</title>
   </head>
   <body>
   <span th:text="${error}"></span>
   </body>
   </html>
   ```



### @ControllerAdvice+@ExceptionHandler

1. 需要创建一个能够处理异常的全局异常类。在该类上需 要添加`@ControllerAdvice` 注解

   ```java
   @ControllerAdvice
   public class GlobalException {
       /**
        * java.lang.ArithmeticException
        * 该方法需要返回一个 ModelAndView：目的是可以让我们封装异常信息以及视图的指定
        * 参数 Exception e:会将产生异常对象注入到方法中
        */
       @ExceptionHandler(ArithmeticException.class)
       public ModelAndView arithmeticExceptionHandler(Exception e) {
           ModelAndView mv = new ModelAndView();
           mv.addObject("error", e.toString());
           mv.setViewName("ArithmeticException");
           return mv;
       }
   
       @ExceptionHandler(NullPointerException.class)
       public ModelAndView nullPointerExceptionHandler(Exception e) {
           ModelAndView mv = new ModelAndView();
           mv.addObject("error", e.toString());
           mv.setViewName("NullPointerException");
           return mv;
       }
   }
   ```



2. controller

   ```java
   @Controller
   public class ExceptionController {
   
       @RequestMapping("show1")
       public String show1() {
           String str = null;
   
           str.length();
           return "index";
       }
   
       @RequestMapping("show2")
       public String show2() {
   
           int a = 1 / 0;
           System.out.println("a = " + a);
           return "index";
       }
   }
   ```

   

### 配置 SimpleMappingExceptionResolver

通过 `SimpleMappingExceptionResolver` 做全局异常处理

1. 在全局异常类中添加一个方法完成异常的同一处理

   ```java
   @Configuration
   public class GlobalException {
   
       // 该方法必须要有返回值。返回值类型必须是：SimpleMappingExceptionResolver
       @Bean
       public SimpleMappingExceptionResolver getSimpleMappingExceptionResolver() {
           SimpleMappingExceptionResolver resolver = new SimpleMappingExceptionResolver();
   
           Properties mappings = new Properties();
   
           /**
            * 参数一：异常的类型，注意必须是异常类型的全名
            * 参数二：视图名称
            */
           mappings.put("java.lang.ArithmeticException", "ArithmeticException");
           mappings.put("java.lang.NullPointerException", "NullPointerException");
   
           //设置异常与视图映射信息
           resolver.setExceptionMappings(mappings);
           return resolver;
       }
   
   ```

   

2. controller

   ```java
   @Controller
   public class ExceptionController {
   
       @RequestMapping("show1")
       public String show1() {
           String str = null;
   
           str.length();
           return "index";
       }
   
       @RequestMapping("show2")
       public String show2() {
   
           int a = 1 / 0;
           System.out.println("a = " + a);
           return "index";
       }
   }
   ```





### 自定义 HandlerExceptionResolver

1. 需要在全局异常处理类中实现 `HandlerExceptionResolver` 接口

   ```java
   @Configuration
   public class GlobalException implements HandlerExceptionResolver {
   
       @Override
       public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
           ModelAndView mv = new ModelAndView();
   
           if (e instanceof ArithmeticException) {
               mv.setViewName("ArithmeticException");
           } if (e instanceof NullPointerException) {
               mv.setViewName("NullPointerException");
   
           }
   
           mv.addObject("error", e.toString());
           return mv;
       }
   }
   ```

   

2. controller

   ```java
   @Controller
   public class ExceptionController {
   
       @RequestMapping("show1")
       public String show1() {
           String str = null;
   
           str.length();
           return "index";
       }
   
       @RequestMapping("show2")
       public String show2() {
   
           int a = 1 / 0;
           System.out.println("a = " + a);
           return "index";
       }
   }
   ```





## 单元测试

### pom 文件

```xml
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>

    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-test</artifactId>
        <scope>test</scope>
    </dependency>
</dependencies>
```



### 编写业务代码

```java
@Repository
public class UserDaoImpl {

    public void save() {
        System.out.println("insert into user...");
    }
}
```



```java
@Service
public class UserServiceImpl {

    @Autowired
    private UserDaoImpl userDao;

    public void addUser() {
        this.userDao.save();
    }
}
```



### 编写启动类

```java
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
```



### 整合 Junit

```java
/**
 * @RunWith:启动器
 * SpringJUnit4ClassRunner.class：让 junit 与 spring 环境进行整合
 *
 * @SpringBootTest(classes={App.class}) 1,当前类为 springBoot 的测试类
 * 										2,加载 SpringBoot 启动类。启动springBoot
 *
 * junit 与 spring 整合
 * @Contextconfiguartion("classpath:applicationContext.xml")
 * @RunWith(SpringJUnit4ClassRunner.class)
 * @SpringBootTest(classes={App.class})
 */
@SpringBootTest
class ApplicationTest {

    @Autowired
    private UserServiceImpl service;
    @Test
    void testAddUser() {
        this.service.addUser();
    }
}
```



## 热部署

1. SprigBoot 的热部署方式分为两种

   + SpringLoader 插件

     + 以 maven 插件方式使用 SpringLoader

     + 在项目中直接使用 jar 包的方式

       `javaagent:.\lib\springloaded-1.2.5.RELEASE.jar -noverify`

   + DevTools 工具



2. SpringLoader 与 DevTools 的区别：
   + SpringLoader：SpringLoader 在部署项目时使用的是热部署的方式。
   + DevTools：DevTools 在部署项目时使用的是重新部署的方式



> SpringLoader 缺陷：就是 Java 代码做部署处理。但是对页面无能为力。
>
> 注意：Springloader 热部署程序是在 系统后台以进程的形式来运行。需要手动关闭该进程



### pom 文件添加 devtools 的依赖

```xml
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-thymeleaf</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>

    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-devtools</artifactId>
        <scope>runtime</scope>
        <optional>true</optional>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-test</artifactId>
        <scope>test</scope>
    </dependency>
</dependencies>
```



### idea配置

1. File → Settings → Build, Execution, Deployment → Compiler→build project automatically(勾选)

2. Edit Configutrations... → Configutration →  On Upadate action: Update classes and resources

   ​					                                           →  On frame deactivation: Update classes and resources







## 缓存技术

### 概述

1. `EhCache`是一个纯Java的进程内缓存框架，具有快速、精干等特点，是Hibernate中默认CacheProvider。



2. ehcache 和 redis 比较
   + ehcache直接在jvm虚拟机中缓存，速度快，效率高；但是缓存共享麻烦，集群分布式应用不方便。
   + redis是通过socket访问到缓存服务，效率比Ehcache低，比数据库要快很多，处理集群和分布式缓存方便
   + 如果是单个应用或者对缓存访问要求很高的应用，用ehcache。如果是大型系统，存在缓存共享、分布式部署、缓存内容很大的，建议用redis。





### EhCache

#### pom 文件

```xml
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>

    <!-- Spring Boot 缓存支持启动器 -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-cache</artifactId>
    </dependency>

    <!-- Ehcache 坐标 -->
    <dependency>
        <groupId>net.sf.ehcache</groupId>
        <artifactId>ehcache</artifactId>
    </dependency>

    <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
        <scope>runtime</scope>
    </dependency>

    <dependency>
        <groupId>com.alibaba</groupId>
        <artifactId>druid</artifactId>
        <version>1.1.10</version>
    </dependency>

    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-test</artifactId>
        <scope>test</scope>
    </dependency>
</dependencies>
```



#### Ehcache 配置文件

```xml
<?xml version="1.0" encoding="UTF-8"?>
<ehcache xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:noNamespaceSchemaLocation="http://ehcache.org/ehcache.xsd">

    <!-- 磁盘缓存位置 -->
    <diskStore path="java.io.tmpdir"/>

    <!--defaultCache:echcache的默认缓存策略  -->
    <defaultCache
            maxElementsInMemory="10000"
            eternal="false"
            timeToIdleSeconds="120"
            timeToLiveSeconds="120"
            maxElementsOnDisk="10000000"
            diskExpiryThreadIntervalSeconds="120"
            memoryStoreEvictionPolicy="LRU">
        <persistence strategy="localTempSwap"/>
    </defaultCache>

    <!-- 自定义缓存策略 -->
    <cache name="user"
           maxElementsInMemory="10000"
           eternal="false"
           timeToIdleSeconds="120"
           timeToLiveSeconds="120"
           maxElementsOnDisk="10000000"
           diskExpiryThreadIntervalSeconds="120"
           memoryStoreEvictionPolicy="LRU">
        <persistence strategy="localTempSwap"/>
    </cache>
</ehcache>
```



#### 核心配置文件

```properties
spring.datasource.driverClassName=com.mysql.cj.jdbc.Driver
spring.datasource.url=jdbc:mysql://localhost:3306/springdata?useUnicode=true&characterEncoding=utf8
spring.datasource.username=root
spring.datasource.password=ixfosa
spring.datasource.type=com.alibaba.druid.pool.DruidDataSource
# 正向工程
spring.jpa.hibernate.ddl-auto=update
# 显示 sql
spring.jpa.show-sql=true

# 加载 EhCache 配置
spring.cache.ehcache.config=classpath:ehcache.xml
```



#### 修改启动类

+ `@EnableCaching`: spring framework中的注解驱动的缓存管理功能。自spring版本3.1起加入了该注解。如果你使用了这个注解，那么你就不需要在XML文件中配置cache manager了。

```java
package top.ixfosa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
```

#### 实体

```java
@Entity
@Table(name = "user")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private Integer age;

	// ......
}
```



#### Dao

```java
public interface UserDao extends JpaRepository<User, Integer> {

}
```



#### Service

```java
public interface UserService {
    List<User> findUserAll();
    User findUserById(Integer id);
    Page<User> findUserByPage(Pageable pageable);
}
```

```java
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;


    @Override
    public List<User> findUserAll() {
        return this.userDao.findAll();
    }

    @Override
    @Cacheable(value="user") //对当前查询的对象做缓存处理
    public User findUserById(Integer id) {
        return this.userDao.findById(id).get();
    }

    @Override
    public Page<User> findUserByPage(Pageable pageable) {
        return this.userDao.findAll(pageable);
    }
}
```



#### 测试代码

```java
@SpringBootTest(classes = {Application.class})
public class ApplicationTests {

    @Autowired
    private UserService userService;

    @Test
    void testFindUserById() {
        User user = this.userService.findUserById(29);
        System.out.println(user);

        User user2 = this.userService.findUserById(29);
        System.out.println(user2);

        // Hibernate: select user0_.id as id1_0_0_, user0_.age as age2_0_0_,
        // user0_.name as name3_0_0_ from user user0_ where user0_.id=?
        // User{id=29, name='夏', age=100}
        // User{id=29, name='夏', age=100}
    }
}
```



#### @Cacheable

1. `@Cacheable` 作用：把方法的返回值添加到 Ehcache 中做缓存 
   + `Value` 属性：指定一个 Ehcache 配置文件中的缓存策略，如果么有给定 value，name 则 表示使用默认的缓存策略
   + `Key` 属性：给存储的值起个名称。在查询时如果有名称相同的，那么则知己从缓存中将 数据返回



2. 业务层

   ```java
   @Override
   @Cacheable(value = "user", key = "#pageable.pageSize")
   public Page<User> findUserByPage(Pageable pageable) {
       return this.userDao.findAll(pageable);
   }
   ```



3. 测试代码

   ```java
   @Test
   void testFindUserByPage() {
       Pageable pageable = PageRequest.of(0, 2);
   
       // 第一次查询
       System.out.println(userService.findUserByPage(pageable).getContent());
   
       // 第二次查询
       System.out.println(userService.findUserByPage(pageable).getContent());
   
       // 第三次查询
       pageable = PageRequest.of(1, 2);  // 取缓存值
       Pageable pageable1 = PageRequest.of(1, 2); // 取缓存值
       System.out.println(userService.findUserByPage(pageable1).getContent());
   
       // Hibernate: select user0_.id as id1_0_, user0_.age as age2_0_, user0_.name as name3_0_ from user user0_ limit ?
       // Hibernate: select count(user0_.id) as col_0_0_ from user user0_
       // [User{id=29, name='夏', age=100}, User{id=31, name='ixfosa', age=121211}]
       // [User{id=29, name='夏', age=100}, User{id=31, name='ixfosa', age=121211}]
       // [User{id=29, name='夏', age=100}, User{id=31, name='ixfosa', age=121211}]
   }
   ```

   

#### @CacheEvict

1. @CacheEvict 作用：清除缓存

   

2. 业务层

   ```java
   @Service
   public class UserServiceImpl implements UserService {
   
       @Autowired
       private UserDao userDao;
   
   
       @Override
       @Cacheable(value = "user")
       public List<User> findUserAll() {
           return this.userDao.findAll();
       }
   
       @Override
       @CacheEvict(value = "user", allEntries = true)
       public void saveUser(User user) {
           this.userDao.save(user);
       }
   }
   ```

   

3. 测试代码

   ```java
   @Test
   void testFindAll() {
   
       //第一次查询
       System.out.println(userService.findUserAll().size());
   
       User user = new User();
       user.setName("ixfosa");
       userService.saveUser(user);
   
       // 第三次查询
       System.out.println(userService.findUserAll().size());
       
       // Hibernate: select user0_.id as id1_0_, user0_.age as age2_0_, user0_.name as name3_0_ from user user0_
       // 9
       // Hibernate: insert into user (age, name) values (?, ?)
       // Hibernate: select user0_.id as id1_0_, user0_.age as age2_0_, user0_.name as name3_0_ from user user0_
       // 10
   }
   ```





### Spring-Data-Redis

#### pom.xml文件

```xml
<dependencies>
    <!-- Spring Data Redis 的启动器 -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-redis</artifactId>
    </dependency>
	
     <dependency>
         <groupId>redis.clients</groupId>
         <artifactId>jedis</artifactId>
         <version>2.10.2</version>
         <type>jar</type>
         <scope>compile</scope>
    </dependency>
    
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-test</artifactId>
        <scope>test</scope>
    </dependency>
</dependencies>
```

#### Spring Data Redis 配置类

```java
@Configuration
public class RedisConfig {

    // 1. 创建 JedisPoolConfig 对象。在该对象中完成一些链接池配置
    @Bean
    public JedisPoolConfig getjedisPoolConfig() {
        JedisPoolConfig config = new JedisPoolConfig();
        // 最大空闲数
        config.setMaxIdle(10);
        //最小空闲数
        config.setMinIdle(5);
        // 最大链接数
        config.setMaxTotal(20);
        return config;
    }

    // 2. 创建 JedisConnectionFactory：配置 redis 链接信息
    @Bean
    public JedisConnectionFactory getJedisConnectionFactory(JedisPoolConfig config) {
        JedisConnectionFactory factory = new JedisConnectionFactory();
        // 关联链接池的配置对象
        factory.setPoolConfig(config);
        //配置链接 Redis 的信息
        factory.setHostName("127.0.0.1");
        factory.setPort(6379);
        return factory;
    }

    // 3. 创建 RedisTemplate:用于执行 Redis 操作的方法
    @Bean
    public RedisTemplate getRedisTemplate(JedisConnectionFactory factory) {
        RedisTemplate<String, Object> template = new RedisTemplate();
        template.setConnectionFactory(factory);

        // 为 key 设置序列化器
        template.setKeySerializer(new StringRedisSerializer());
        // 为 value 设置序列化器
        template.setValueSerializer(new StringRedisSerializer());

        return template;
    }
}
```



#### 测试代码

```java
@SpringBootTest
class ApplicationTests {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Test
    void testSet() {
        redisTemplate.opsForValue().set("k", "v");
    }

    @Test
    void testGet() {
        String k = (String) redisTemplate.opsForValue().get("k");
        System.out.println(k);
    }
}
```



#### 提取 redis 的配置信息

+ application.properties， 不需要在有 Spring Data Redis 的配置类

```properties
spring.redis.jedis.pool.max-idle=10
spring.redis.jedis.pool.min-idle=5
spring.redis.jedis.pool.max-total=20


spring.redis.host=localhost
spring.redis.port=6379
```



#### 修改pring Data Redis配置类

+ @ConfigurationProperties：将前缀相同的内容创建一个实体。

```java
@Configuration
public class RedisConfig {

    // 1. 创建 JedisPoolConfig 对象。在该对象中完成一些链接池配置
    // @ConfigurationProperties:会将前缀相同的内容创建一个实体。
    @Bean
    @ConfigurationProperties("spring.redis.jedis.pool")
    public JedisPoolConfig getjedisPoolConfig() {
        JedisPoolConfig config = new JedisPoolConfig();
        // 最大空闲数
        // config.setMaxIdle(10);
        //最小空闲数
        // config.setMinIdle(5);
        // 最大链接数
        // config.setMaxTotal(20);

        System.out.println("默认值："+config.getMaxIdle());  // 默认值：8
        System.out.println("默认值："+config.getMinIdle());  // 默认值：0
        System.out.println("默认值："+config.getMaxTotal()); // 默认值：8

        return config;
    }

    // 2. 创建 JedisConnectionFactory：配置 redis 链接信息
    @Bean
    @ConfigurationProperties(prefix = "spring.redis")
    public JedisConnectionFactory getJedisConnectionFactory(JedisPoolConfig config) {

        System.out.println("配置完毕："+config.getMaxIdle());  // 10
        System.out.println("配置完毕："+config.getMinIdle());  // 5
        System.out.println("配置完毕："+config.getMaxTotal()); // 20

        JedisConnectionFactory factory = new JedisConnectionFactory();
        // 关联链接池的配置对象
        factory.setPoolConfig(config);

        // 配置链接 Redis 的信息
        // factory.setHostName("127.0.0.1");
        // factory.setPort(6379);
        return factory;
    }

    // 3. 创建 RedisTemplate:用于执行 Redis 操作的方法
    @Bean
    public RedisTemplate getRedisTemplate(JedisConnectionFactory factory) {
        RedisTemplate<String, Object> template = new RedisTemplate();
        template.setConnectionFactory(factory);

        // 为 key 设置序列化器
        template.setKeySerializer(new StringRedisSerializer());
        // 为 value 设置序列化器
        template.setValueSerializer(new StringRedisSerializer());

        return template;
    }
}
```



#### 操作实体对象

```java
@SpringBootTest
class ApplicationTests {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Test
    void testSetObj() {
        User user = new User();
        user.setId(1);
        user.setName("佛");
        user.setAge(23);
        redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
        redisTemplate.opsForValue().set("obj", user);
    }

    @Test
    void testGetObj() {
        redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
        User User = (User) redisTemplate.opsForValue().get("obj");
        System.out.println(User);
    }
}

```



#### 操作JSON格式存储实体对象

```java
@SpringBootTest
class ApplicationTests {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Test
    void testSetJSON() {
        User user = new User();
        user.setId(1);
        user.setName("龙");
        user.setAge(22);

        redisTemplate.
            setValueSerializer(new Jackson2JsonRedisSerializer<>(User.class));

        redisTemplate.opsForValue().set("json", user);
    }

    @Test
    void testGetJSON() {
        redisTemplate.
            setValueSerializer(new Jackson2JsonRedisSerializer<>(User.class));
        
        User User = (User) redisTemplate.opsForValue().get("json");
        System.out.println(User);
    }
}

```





## 定时任务

### Scheduled 定时任务器

`Scheduled` 定时任务器：是 Spring3.0 以后自带的一个定时任务器。



#### pom.xml 添加 Scheduled 的坐标

```xml
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter</artifactId>
    </dependency>

    <!-- 添加 Scheduled 坐标 -->
    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-context-support</artifactId>
    </dependency>

    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-test</artifactId>
        <scope>test</scope>
    </dependency>
</dependencies>
```



#### 编写定时任务类

+  `@Scheduled`

```java
@Component
// @EnableScheduling   // 或在启动器中开启
public class ScheduledDemo {
    /**
     * 定时任务方法
     * @Scheduled:设置定时任务
     * cron 属性：cron 表达式。定时任务触发是时间的一个字符串表达形式
     */
    @Scheduled(cron = "0/2 * * * * ?")
    public void scheduledMethod() {
        System.out.println("Scheduled: " + new Date());
    }
}
```



#### 在启动类中开启定时任务的使用

+ `@EnableScheduling`

```java
@SpringBootApplication
@EnableScheduling
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
```



### cron 表达式讲解

1. Cron 表达式是一个字符串，分为 `6` 或 `7` 个域，每一个域代表一个含义



2. Cron 有如下两种语法格式：
   + Seconds Minutes Hours Day Month Week Year   ( 7 域)
   + Seconds Minutes Hours Day Month Week            (6 域)



3. 结构

   `corn` 从左到右（用空格隔开）：秒   分   小时   月份中的日期   月份   星期中的日期    年份

   

4. 各字段的含义

   | 字段                     | 允许值                                 | 允许的特殊字符             |
   | ------------------------ | -------------------------------------- | -------------------------- |
   | 秒（Seconds）            | 0~59的整数                             | , - * /   四个字符         |
   | 分（*Minutes*）          | 0~59的整数                             | , - * /   四个字符         |
   | 小时（*Hours*）          | 0~23的整数                             | , - * /   四个字符         |
   | 日期（*DayofMonth*）     | 1~31的整数（但是你需要考虑你月的天数） | ,- * ? / L W C   八个字符  |
   | 月份（*Month*）          | 1~12的整数或者 JAN-DEC                 | , - * /   四个字符         |
   | 星期（*DayofWeek*）      | 1~7的整数或者 SUN-SAT （1=SUN）        | , - * ? / L C #   八个字符 |
   | 年(可选，留空)（*Year*） | 1970~2099                              | , - * /   四个字符         |



6. Cron 表达式的时间字段除允许设置数值外，还可使用一些特殊的字符，提供列表、范围、通配符等功 能，细说如下：
   + 星号(`*`)：可用在所有字段中，表示对应时间域的每一个时刻，例如，*在分钟字段时，表示“每分钟”；
   + 问号(`?`)：该字符只在日期和星期字段中使用，它通常指定为“无意义的值”，相当于占位符； 
   + 减号(`-`)：表达一个范围，如在小时字段中使用“10-12”，则表示从 10 到 12 点，即 10,11,12；
   + 逗号(`,`)：表达一个列表值，如在星期字段中使用“MON,WED,FRI”，则表示星期一，星期三和星期 五； 
   + 斜杠(`/`)：x/y 表达一个等步长序列，x 为起始值，y 为增量步长值。如在分钟字段中使用 0/15，则 表示为 0,15,30 和 45 秒，而 5/15 在分钟字段中表示 5,20,35,50，你也可以使用*/y，它等同于 0/y； 
   + `L`：该字符只在日期和星期字段中使用，代表“Last”的意思，但它在两个字段中意思不同。L 在日期 字段中，表示这个月份的最后一天，如一月的 31 号，非闰年二月的 28 号；如果 L 用在星期中，则表示星 期六，等同于 7。但是，如果 L 出现在星期字段里，而且在前面有一个数值 X，则表示“这个月的最后 X 天”， 例如，6L 表示该月的最后星期五； 
   + `W`：该字符只能出现在日期字段里，是对前导日期的修饰，表示离该日期最近的工作日。例如 15W 表示离该月 15 号最近的工作日，如果该月 15 号是星期六，则匹配 14 号星期五；如果 15 日是星期日， 则匹配 16 号星期一；如果 15 号是星期二，那结果就是 15 号星期二。但必须注意关联的匹配日期不能够 跨月，如你指定 1W，如果 1 号是星期六，结果匹配的是 3 号星期一，而非上个月最后的那天。W 字符串 只能指定单一日期，而不能指定日期范围； 
   + `LW` 组合：在日期字段可以组合使用 LW，它的意思是当月的最后一个工作日； 
   + 井号(`#`)：该字符只能在星期字段中使用，表示当月某个工作日。如 6#3 表示当月的第三个星期五(6 表示星期五，#3 表示当前的第三个)，而 4#5 表示当月的第五个星期三，假设当月没有第五个星期三， 忽略不触发； 
   + `C`：该字符只在日期和星期字段中使用，代表“Calendar”的意思。它的意思是计划所关联的日期， 如果日期没有被关联，则相当于日历中所有日期。例如 5C 在日期字段中就相当于日历 5 日以后的第一天。 1C 在星期字段中相当于星期日后的第一天。 C

> ron 表达式对特殊字符的`大小写不敏感`，对代表星期的缩写英文大小写也不敏感。



7. 例子
   + @Scheduled(cron = "0 0 1 1 1 ?")               每年一月的一号的 1:00:00 执行一次
   + @Scheduled(cron = "0 0 1 1 1,6 ?")             一月和六月的一号的 1:00:00 执行一次 
   + @Scheduled(cron = "0 0 1 1 1,4,7,10 ?")     每个季度的第一个月的一号的 1:00:00 执
   + @Scheduled(cron = "0 0 1 1 * ?")                 每月一号 1:00:00 执行一次 
   + @Scheduled(cron="0 0 1 * * *")                     每天凌晨 1 点执行一次





### Quartz 定时任务框架

#### Quartz 的介绍

`Quartz`是OpenSymphony开源组织在Job scheduling领域又一个开源项目，完全由Java开发，可以用来执行定时任务，类似于java.util.Timer。但是相较于Timer， Quartz增加了很多功能：

+ 持久性作业 - 就是保持调度定时的状态;
+ 作业管理 - 对调度作业进行有效的管理;



#### Quartz 的使用思路

1. 首先需要定义实现一个定时功能的接口，我们可以称之为`Task`（或Job），如定时发送邮件的task（Job），重启机器的task（Job），优惠券到期发送短信提醒的task（Job）

2. 有了任务后，还需要一个能够实现触发任务去执行的触发器，触发器`Trigger`最基本的功能是指定Job的执行时间，执行间隔，运行次数等。
3. 有了Job和Trigger后，怎么样将两者结合起来呢？即怎样指定Trigger去执行指定的Job呢？这时需要一个`Schedule`，来负责这个功能的实现。



4. Quartz的基本组成部分：

   + 任务：JobDetail                                                               你要做什么事？

   + 触发器：Trigger，包括SimpleTrigger和CronTrigger       你什么时候去做？
   + 调度器：Scheduler                                                          你什么时候需要去做什么事？



#### 基本使用

##### pom 文件添加 Quartz 的坐标

```xml
<!-- Quartz 坐标 -->
<dependency>
    <groupId>org.quartz-scheduler</groupId>
    <artifactId>quartz</artifactId>
    <version>2.3.2</version>
</dependency>
```



##### 创建 Job 类

```java
public class QuartzDemo implements Job {
    // 任务被触发时所执行的方法
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("Quartz: " + new Date());
    }
}
```



##### 测试代码

```java
public class Application {

    public static void main(String[] args) throws SchedulerException {
        // 1.创建 Job 对象：你要做什么事？
        JobDetail job = JobBuilder.newJob(QuartzDemo.class).build();

        // 简单的 trigger 触发时间：通过 Quartz 提供一个方法来完成简单的重复调用 cron
        // Trigger：按照 Cron 的表达式来给定触发的时间

        // 2.创建 Trigger 对象：在什么时间做？
        Trigger trigger = TriggerBuilder.newTrigger().
                withSchedule(SimpleScheduleBuilder.repeatSecondlyForever()).build();
        
        Trigger trigger1 = TriggerBuilder.newTrigger().
                withSchedule(CronScheduleBuilder.
                             cronSchedule("0/2 * * * * ?")).build();

        // 3.创建 Scheduler 对象：在什么时间做什么事？
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

        scheduler.scheduleJob(job, trigger);

        scheduler.start();
    }
}
```





#### 整合 Quartz 定时框架

##### pom 文件添加坐标

```xml
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-quartz</artifactId>
    </dependency>

    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-test</artifactId>
        <scope>test</scope>
    </dependency>
</dependencies>
```



##### 创建 Job 类

```java
public class QuartzDemo implements Job {
    // 任务被触发时所执行的方法
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("Quartz: " + new Date());
    }
}
```



##### Quartz 配置类

+ `JobDetailFactoryBean`
+ `SimpleTriggerFactoryBean`
+ `SchedulerFactoryBean`

```java
@Configuration
public class QuartzConfig {
    // 1.创建 Job 对象
    @Bean
    public JobDetailFactoryBean getJobDetailFactoryBean() {
        JobDetailFactoryBean factory = new JobDetailFactoryBean();
        // 关联我们自己的 Job 类
        factory.setJobClass(QuartzDemo.class);
        return factory;
    }

    //  2.创建 Trigger 对象
    @Bean
    public SimpleTriggerFactoryBean getSimpleTriggerFactoryBean(JobDetailFactoryBean jobDetailFactoryBean) {
        SimpleTriggerFactoryBean factory = new SimpleTriggerFactoryBean();
        // 关联 JobDetail 对象
        factory.setJobDetail(jobDetailFactoryBean.getObject());
        // 该参数表示一个执行的毫秒数
        factory.setRepeatInterval(2000);
        // 重复次数
        factory.setRepeatCount(5);
        return factory;
    }

    // 3.创建 Scheduler 对象
    @Bean
    public SchedulerFactoryBean getSchedulerFactoryBean(SimpleTriggerFactoryBean simpleTriggerFactoryBean) {
        SchedulerFactoryBean factory = new SchedulerFactoryBean();
        // 关联 trigger
        factory.setTriggers(simpleTriggerFactoryBean.getObject());
        return factory;
    }
}
```



##### 修改启动类

+ `@EnableScheduling`

```java
@SpringBootApplication
@EnableScheduling
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
```





#### Job 类中注入对象

##### 创建 Job 类

```java
@Service
public class UserService {
    public void addUser() {
        System.out.println("add user...");
    }
}
```



```java
public class QuartzDemo implements Job {

    @Autowired
    private UserService userService;


    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("Quartz: " + new Date());
        userService.addUser();
    }
}
```



##### 注入时会产生异常

```java
org.quartz.SchedulerException: Job threw an unhandled exception.
	at org.quartz.core.JobRunShell.run(JobRunShell.java:213) ~[quartz-2.3.2.jar:na]
	at org.quartz.simpl.SimpleThreadPool$WorkerThread.run(SimpleThreadPool.java:573) [quartz-2.3.2.jar:na]
Caused by: java.lang.NullPointerException: null
	at top.ixfosa.quartz.QuartzDemo.execute(QuartzDemo.java:23) ~[classes/:na]
	at org.quartz.core.JobRunShell.run(JobRunShell.java:202) ~[quartz-2.3.2.jar:na]
	... 1 common frames omitted
```

##### 编写AdaptableJobFactory实现类解决

```java
@Component
public class MyAdaptableJobFactory extends AdaptableJobFactory {

    // AutowireCapableBeanFactory 可以将一个对象添加到 SpringIOC 容器中，并且完成该对象注入
    @Autowired
    private AutowireCapableBeanFactory autowireCapableBeanFactory;

    // 该方法需要将实例化的任务对象手动的添加到 springIOC 容器中并且完成对象的注入
    @Override
    protected Object createJobInstance(TriggerFiredBundle bundle) throws Exception {
        Object instance = super.createJobInstance(bundle);
        // 将 obj 对象添加 Spring IOC 容器中，并完成注入
        this.autowireCapableBeanFactory.autowireBean(instance);
        return instance;
    }
}	
```



##### Quartz 配置类

```java
@Configuration
public class QuartzConfig {
    // 1.创建 Job 对象
    @Bean
    public JobDetailFactoryBean getJobDetailFactoryBean() {
        JobDetailFactoryBean factory = new JobDetailFactoryBean();
        // 关联我们自己的 Job 类
        factory.setJobClass(QuartzDemo.class);
        return factory;
    }

    //  2.创建 Trigger 对象
    //      简单的 Trigger
    /*@Bean
    public SimpleTriggerFactoryBean getSimpleTriggerFactoryBean(JobDetailFactoryBean jobDetailFactoryBean) {
        SimpleTriggerFactoryBean factory = new SimpleTriggerFactoryBean();
        // 关联 JobDetail 对象
        factory.setJobDetail(jobDetailFactoryBean.getObject());
        // 该参数表示一个执行的毫秒数
        factory.setRepeatInterval(2000);
        // 重复次数
        factory.setRepeatCount(5);
        return factory;
    }
    */

    // Cron Trigger
    @Bean
    public CronTriggerFactoryBean getCronTriggerFactoryBean(JobDetailFactoryBean jobDetailFactoryBean) {
        CronTriggerFactoryBean factory = new CronTriggerFactoryBean();
        factory.setJobDetail(jobDetailFactoryBean.getObject());
        // 设置触发时间
        factory.setCronExpression("0/2 * * * * ?");
        return factory;
    }

    // 3.创建 Scheduler 对象
    @Bean
    public SchedulerFactoryBean getSchedulerFactoryBean(CronTriggerFactoryBean cronTriggerFactoryBean,
                                                        MyAdaptableJobFactory myAdaptableJobFactory) {
        SchedulerFactoryBean factory = new SchedulerFactoryBean();
        // 关联 trigger
        factory.setTriggers(cronTriggerFactoryBean.getObject());
        factory.setJobFactory(myAdaptableJobFactory);
        return factory;
    }
}
```



##### 修改启动类

```java
@SpringBootApplication
@EnableScheduling
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
```



## 整合 JWT

### JWT 的简介

`JSON Web Token`（缩写 JWT）是目前最流行的跨域认证解决方案

1. 传统的session认证
   1. 用户向服务器发送用户名和密码。
   2. 服务器验证通过后，在当前对话（session）里面保存相关数据，比如用户角色、登录时间等等。
   3. 服务器向用户返回一个 session_id，写入用户的 Cookie。
   4. 用户随后的每一次请求，都会通过 Cookie，将 session_id 传回服务器。
   5. 服务器收到 session_id，找到前期保存的数据，由此得知用户的身份。

> 缺点：扩展性（scaling）不好。如果是服务器集群，或者是跨域的服务导向架构，就要求 session 数据共享，每台服务器都能够读取 session。



2. 基于token的鉴权机制
   1. 用户使用用户名密码来请求服务器
   2. 服务器进行验证用户的信息
   3. 服务器通过验证发送给用户一个token
   4. 客户端存储token，并在每次请求时附送上这个token值
   5. 服务端验证token值，并返回数据



### JWT 的数据结构

JWT 的三个部分依次如下

- Header（头部）

- Payload（负载）

- Signature（签名）

  ```json
  // 是一个很长的字符串，中间用点（.）分隔成三个部分。注意，JWT 内部是没有换行的
  Header.Payload.Signature
  ```

  

1. Header

   ```json
   // Header 部分是一个 JSON 对象，描述 JWT 的元数据，通常是下面的样子。
   {
       // alg属性表示签名的算法（algorithm），默认是 HMAC SHA256（写成 HS256）；
       "alg": "HS256", 
       // typ属性表示这个令牌（token）的类型（type），JWT 令牌统一写为JWT。
       "typ": "JWT"
   }
   
   // 最后，将上面的 JSON 对象使用 Base64 算法转成字符串。

2. Payload

   ```json
   // Payload 部分也是一个 JSON 对象，用来存放实际需要传递的数据。JWT 规定了7个官方字段，供选用。
   // 也可以在这个部分自定义私有字段
   iss (issuer)：          签发人
   exp (expiration time)： 过期时间
   sub (subject)：         主题
   aud (audience)：        受众
   nbf (Not Before)：      生效时间
   iat (Issued At)：       签发时间
   jti (JWT ID)：          编号
   userId:                自定义私有字段
   username:              自定义私有字段
   
   // 最后，Payload 部分 使用 Base64 算法转成字符串。
   
   // 注意：JWT 默认是不加密的，任何人都可以读到，所以不要把秘密信息放在这个部分。
   ```

3. Signature

   ```js
   // Signature 部分是对前两部分的签名，防止数据篡改。
   
   // 首先，需要指定一个密钥（secret）。这个密钥只有服务器才知道，不能泄露给用户。
   // 然后，使用 Header 里面指定的签名算法（默认是 HMAC SHA256），按照下面的公式产生签名。
   HMACSHA256(
     base64UrlEncode(header) + "." +
     base64UrlEncode(payload),
     secret)
   
   // 算出签名以后，把 Header、Payload、Signature 三个部分拼成一个字符串，每个部分之间用"点"（.）分隔，就可以返回给用户。
   ```

   

### JWT 的特点

1. JWT 默认是不加密，但也是可以加密的。生成原始 Token 以后，可以用密钥再加密一次。
2. JWT 不加密的情况下，不能将秘密数据写入 JWT。
3. JWT 不仅可以用于认证，也可以用于交换信息。有效使用 JWT，可以降低服务器查询数据库的次数。
4. JWT 的最大缺点是，由于服务器不保存 session 状态，因此无法在使用过程中废止某个 token，或者更改 token 的权限。也就是说，一旦 JWT 签发了，在到期之前就会始终有效，除非服务器部署额外的逻辑。
5. JWT 本身包含了认证信息，一旦泄露，任何人都可以获得该令牌的所有权限。为了减少盗用，JWT 的有效期应该设置得比较短。对于一些比较重要的权限，使用时应该再次对用户进行认证。
6. 为了减少盗用，JWT 不应该使用 HTTP 协议明码传输，要使用 HTTPS 协议传输。



### JWT 的简单使用

1. 客户端收到服务器返回的 JWT，可以储存在 `Cookie` 里面，也可以储存在 `localStorage` 或 sessionStorage 中。

2. 客户端每次与服务器通信，都要带上这个 JWT。

   + 可以把它放在 Cookie 里面自动发送，但是这样不能跨域，
   + 更好的做法是放在 HTTP **请求的头信息** `Authorization` 字段里面。

   + 另一种做法是，跨域的时候，JWT 就放在 POST 请求的数据体里面。

```xml
<!--引入jwt-->
<dependency>
    <groupId>com.auth0</groupId>
    <artifactId>java-jwt</artifactId>
    <version>3.10.3</version>
</dependency>
```

```java
package top.ixfosa;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Calendar;
import java.util.HashMap;

@SpringBootTest
class JwtApplicationTests {
    private static final String SIGN_CODE = "hahaha";
    @Test
    public void getTokenTest() {

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.SECOND , 200);

        // withHeader(map): 有默认值 {  "alg": "HS256", "typ": "JWT" }
        String token = JWT.create().withHeader(new HashMap<>())  // header

                .withClaim("id", 1)   // payload
                .withClaim("name", "ixfosa") // payload
                .withExpiresAt(calendar.getTime())   // 指定令牌的过期时间
                .sign(Algorithm.HMAC256(SIGN_CODE));  // 签名, 保密

        System.out.println("token: " + token);
        // token: eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJuYW1lIjoiaXhmb3NhIiwiaWQiOjEsImV4cCI6MTYyODg0NDU0Mn0.0rnQmMeWIE_2it4dW81itIjqFfRSg99UCshmTIKlXNI
    }

    // 令牌验证:根据令牌和签名解析数据
    // 常见异常：
        //- SignatureVerificationException  签名不一致异常
        //- TokenExpiredException           令牌过期异常
        //- AlgorithmMismatchException      算法不匹配异常
        //- InvalidClaimException           失效的payload异常
    @Test
    public void parseTokenTest() {
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJuYW1lIjoiaXhmb3NhIiwiaWQiOjEsImV4cCI6MTYyODg0NTEzNH0.lDIv_CVl-TTNIYYJnJySWQQHhIPAb33Hz7b8YkPxKdU";
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(SIGN_CODE)).build();
        DecodedJWT verify = jwtVerifier.verify(token);
        System.out.println("用户Id: " + verify.getClaim("id").asInt());
        System.out.println("用户名：" + verify.getClaim("name"));
        System.out.println("用户名：" + verify.getClaim("name").asString());
        System.out.println("过期时间：" + verify.getExpiresAt());
        // 用户Id: 1
        // 用户名：com.auth0.jwt.impl.JsonNodeClaim@1bc776b7
        // 用户名：ixfosa
        // 过期时间：Fri Aug 13 16:58:54 CST 2021
    }
}
```



### 整合 SpringBoot

