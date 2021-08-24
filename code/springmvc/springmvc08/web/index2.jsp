<%--
  Created by IntelliJ IDEA.
  User: 86138
  Date: 2021/4/12
  Time: 18:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>file</title>
  </head>
  <body>
    <form action="demo2" method="post" enctype="multipart/form-data">
      选择文件：<input type="file" name="file"><br>
      文件描述：<input type="text" name="description">
      <input type="submit" value="submit">
    </form>
  </body>
</html>
