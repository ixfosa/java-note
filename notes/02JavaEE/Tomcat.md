## 基本使用

1. 下载：apache官网。

   + www.apache.org
   + http://jakarta.apache.org  (产品的主页)

2. 安装版：window （exe、msi） linux（rmp）
   压缩版：window（rar，zip） linux（tar，tar.gz）学习时候使用

3. 运行和关闭tomcat

   1. 启动软件
      + 找到 `%tomcat%/bin/startup.bat` ，双击这个文件
      + 弹出窗口，显示信息（不要关闭次窗口）
      + 打开浏览器，输出以下地址   `http://localhost:8080`
      + 看到一只猫画面，证明软件启动成功！

    2. 关闭软件
       + 找到 `%tomcat%/bin/shutdown.bat`，双击这个文件即可！
       + 打开浏览器，输入 http://localhost:8080 地址。看到“无法连接”（清空浏览器缓存）

4. tomcat软件使用的常见问题

   1. 闪退问题
      原因：tomcat软件是java语言开发的。 tomcat软件启动时，会默认到系统的环境变量中查找一个名称叫`JAVA_HOME`的变量。这个变量的作用找到tomcat启动所需的jvm。

      解决办法：到环境变量中设置JAVA_HOME的变量

   2. 端口占用的错误

      + 原因： tomcat启动所需的端口被其他软件占用了！

      + 解决办法： 

        + 关闭其他软件程序，释放所需端口
        + 修改tomcat软件所需端口，修改%tomcat%/conf/`server.xml`文件

        ```xml
        <Connector port="8080" protocol="HTTP/1.1" 
                       connectionTimeout="20000" 
                       redirectPort="8443" />
        ```

   3. CATALINA环境变量问题

      + 原因： tomcat软件启动后，除了查找JAVA_HOME后，还会再查找一个叫`CATALINA_HOME`变量，这个变量的作用是设置tomcat的`根目录`。
      + 解决办法：建议不要设置CATALINA_HOME变量。

      



## Tomcat的目录结构

```java
|-- bin: 存放tomcat的命令。
	catalina.bat 命令：
		startup.bat  -> catalina.bat start	
		shutdown.bat -> catalina.bat stop
|-- conf:     存放tomcat的配置信息。其中server.xml文件是核心的配置文件。
|-- lib:      支持tomcat软件运行的jar包。其中还有技术支持包，如servlet，jsp
|-- logs:     运行过程的日志信息
|-- temp:     临时目录
|-- webapps:  共享资源目录。web应用目录。（注意不能以单独的文件进行共享）
|-- work:     tomcat的运行目录。jsp运行时产生的临时文件就存放在这里
```





