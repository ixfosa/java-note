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
    <form action="demo9" method="post">
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
  </body>
</html>
