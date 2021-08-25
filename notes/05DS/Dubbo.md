## RPC 基础知识

1. `RPC` 【Remote Procedure Call】是指远程过程调用
   + 是一种进程间通信方
   + 是一种技术思想，而不是规范。

> 它允许程序调用另一个地址空间（网络的另一台机器上）的过程或函 数，而不用开发人员显式编码这个调用的细节。调用本地方法和调用远程方法一样。



2. RPC 的特点  
   + 简单：使用简单，建立分布式应用更容易。 
   + 高效：调用过程看起来十分清晰，效率高。
   + 通用：进程间通讯的方式，有通用的规则。



3. RPC架构
   一个完整的RPC架构里面包含了四个核心的组件，分别是C lient，Client Stub，Server以及Server Stub，这个Stub可以理解为存根。
   - 客户端(Client)，服务的调用方。
   - 客户端存根(Client Stub)，存放服务端的地址消息，再将客户端的请求参数打包成网络消息，然后通过网络远程发送给服务方。
   - 服务端(Server)，真正的服务提供者。
   - 服务端存根(Server Stub)，接收客户端发送过来的消息，将消息解包，并调用本地的方法。



4. RPC 基本原理

   <img src="../../_media/RPC基本原理.png" alt="RPC基本原理" style="zoom: 50%;" />	

   1. 调用方 client 要使用右侧 server 的功能（方法），发起对方法的调用
   2. client stub 是 PRC 中定义的存根，看做是 client 的助手。stub 把要调用的方法参数进行序 列化，方法名称和其他数据包装起来。
   3. 通过网络 socket(网络通信的技术)，把方法调用的细节内容发送给右侧的 server 
   4. server 端通过 socket 接收请求的方法名称，参数等数据，传给 stub。 
   5. server 端接到的数据由 serverstub(server 的助手)处理，调用 server 的真正方法，处理业务
   6. server 方法处理完业务，把处理的结果对象（Object）交给了助手，助手把 Object 进行序 列化，对象转为二进制数据。
   7. server 助手二进制数据交给网络处理程序 
   8. 通过网络将二进制数据，发送给 client。 
   9. client 接数据，交给 client 助手。 
   10. client 助手，接收数据通过反序列化为 java 对象（Object），作为远程方法调用结果。

   <img src="../../_media/rpc时序.png" alt="rpc时序" style="zoom:50%;" />

> rpc 通讯是基于 tcp 或 udp 议 
>
> 序列化方式（xml/json/二进制）



## Dubbo 概述

1. Apache Dubbo (incubating)  
   + 一款高性能、轻量级的开源 Java `RPC` 框架
   + 提供了三大核心能力：
     + 面向接口的远程方法调用
     + 智能容错和负载均衡
     + 以及服务自动注册和发现。 
   + 是一个分布式服务框架，致力于提供高性能和透明化的 RPC 远程服务调用方案、服 务治理方案。



2. 基本架构

   <img src="../../_media/基本架构.png" alt="基本架构" style="zoom:50%;" />

   + `服务提供者`（Provider）：暴露服务的服务提供方，服务提供者在启动时，向注册中心注 册自己提供的服务。 

   + `服务消费者`（Consumer）: 调用远程服务的服务消费方，服务消费者在启动时，向注册 中心订阅自己所需的服务，服务消费者，从提供者地址列表中，基于软负载均衡算法，选一 台提供者进行调用，如果调用失败，再选另一台调用。 

   + `注册中心`（Registry）：注册中心返回服务提供者地址列表给消费者，如果有变更，注册 中心将基于长连接推送变更数据给消费者 

   + `监控中心`（Monitor）：服务消费者和提供者，在内存中累计调用次数和调用时间，定时 每分钟发送一次统计数据到监控中心

     > 调用关系说明
     >
     > + 服务容器负责启动，加载，运行服务提供者。
     > + 服务提供者在启动时，向注册中心注册自己提供的服务。
     > + 服务消费者在启动时，向注册中心订阅自己所需的服务。
     > + 注册中心返回服务提供者地址列表给消费者，如果有变更，注册中心将基于长连接推送变更数据给消费者。
     > + 服务消费者，从提供者地址列表中，基于软负载均衡算法，选一台提供者进行调用，如果调用失败，再选另一台调用。
     > + 服务消费者和提供者，在内存中累计调用次数和调用时间，定时每分钟发送一次统计数据到监控中心。



​	

## 注册中心-Zookeeper

###  注册中心概述



### 安装 zookeeper

1. 下载zookeeper  网址 http://zookeeper.apache.org/releases.html
2. 解压zookeeper  解压运行 `zkServer.cmd` ，初次运行会报错，没有zoo.cfg配置文件 
3.  修改zoo.cfg配置文件  
   + 将conf下的 `zoo_sample.cfg` 复制一份改名为 `zoo.cfg` 即可。 
   + 注意几个重要位置：  `dataDir=./临时数据存储的目录`（可写相对路径）  
   + `clientPort=2181`   zookeeper的端口号  修改完成后再次启动zookeeper  
4. 使用 `zkCli.cmd` 测试 
   + `ls /`：列出zookeeper根下保存的所有节点  
   + create –e /ixfosa123：创建一个 ixfosa 节点，值为123  get /ixfosa：获取/ixfosa节点的值 

> `Zookeeper3.5` 以后的版本, 会自动占用 `8080` 端口
>
> + 修改 `conf/zoo.cfg` 配置文件更改端口 `admin.serverPort=post`
>
> 避免与 `dubbo-admin` 端口冲突



### dubbo-admin管理控制台

1. 下载dubbo-admin

   + `git clone https://github.com/apache/dubbo-admin.git`
     + 后端部分：`dubbo-admin-server`
       + 构建： `mvn clean package -Dmaven.test.skip=true`
       + 启动：`java  -jar dubbo-admin-server-0.3.0.jar`
     + 前端部分：`dubbo-admin-ui`   
       + 安装依赖：`npm install`
       + 启动：`npm run dev`

   

2. `application.properties` 配置项说明

   - admin.config-center
     - 推荐使用，配置中心地址，比如`admin.config-center="zookeeper://127.0.0.1:2181"`
     - 需要在配置中心中，配置注册中心和元数据中心地址配置格式如下：

   - admin.registry.address
     - 不推荐使用，老版本的配置中心地址，比如：`admin.registry.address="zookeeper://127.0.0.1:2181"`
     - 如使用该配置，Dubbo Admin会将其作为注册中心和配置中心使用，元数据中心将无法使用，会影响服务测试等功能。

   ```properties
   # centers in dubbo2.7, if you want to add parameters, please add them to the url
   admin.registry.address=zookeeper://127.0.0.1:2181
   admin.config-center=zookeeper://127.0.0.1:2181
   admin.metadata-report.address=zookeeper://127.0.0.1:2181
   
   # 登陆 dubbo-admin-ui 的密码
   admin.root.user.name=root
   admin.root.user.password=root
   
   #session timeout, default is one hour
   admin.check.sessionTimeoutMilli=3600000
   
   # compress
   server.compression.enabled=true
   server.compression.mime-types=text/css,text/javascript,application/javascript
   server.compression.min-response-size=10240
   ```



### 使用 Zookeeper







## 直连方式