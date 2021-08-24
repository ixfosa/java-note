<%--
  Created by IntelliJ IDEA.
  User: 86138
  Date: 2021/4/11
  Time: 15:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>
    <h1>所有的演示例子</h1>
    <h3><a href="${pageContext.request.contextPath }/daodb"> 处理dao中数据库异常</a></h3>
    <h3><a href="${pageContext.request.contextPath }/daomy"> 处理dao中自定义异常</a></h3>
    <h3><a href="${pageContext.request.contextPath }/daono"> 处理dao未知错误 </a></h3>
    <hr>
    <h3><a href="${pageContext.request.contextPath }/servicedb">处理 service中数据库异常</a></h3>
    <h3><a href="${pageContext.request.contextPath }/servicemy">处理 service中自定义异常</a></h3>
    <h3><a href="${pageContext.request.contextPath }/serviceno">处理 service未知错误</a></h3>
    <hr>
    <h3><a href="${pageContext.request.contextPath }/db">处理 controller中数据库异常</a></h3>
    <h3><a href="${pageContext.request.contextPath }/my">处理 controller中自定义异常</a></h3>
    <h3><a href="${pageContext.request.contextPath }/no">处理 controller未知错误</a></h3>
    <hr>
    <!-- 在 web.xml中配置404 -->
    <h3>
      <a href="${pageContext.request.contextPath }/404">404 错误</a>
    </h3>
  </body>
</html>
