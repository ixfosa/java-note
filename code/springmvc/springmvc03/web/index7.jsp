<%--
  Created by IntelliJ IDEA.
  User: 86138
  Date: 2021/4/10
  Time: 18:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>hello</title>
  </head>
  <body>
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
  </body>
</html>
