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
      <form action="demo3" method="post" enctype="multipart/form-data">
        选择文件1：<input type="file" name="multipartFileList"><br>
        文件描述1：<input type="text" name="descriptionList"><br>

        选择文件2：<input type="file" name="multipartFileList"><br>
        文件描述2：<input type="text" name="descriptionList"><br>

        选择文件3：<input type="file" name="multipartFileList"><br>
        文件描述3：<input type="text" name="descriptionList"><br>
        <input type="submit" value="submit">
      </form>
  </body>
</html>
