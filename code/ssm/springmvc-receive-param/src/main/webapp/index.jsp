<%--
  Created by IntelliJ IDEA.
  User: 86138
  Date: 2021/4/24
  Time: 15:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <form action="basetype" method="post" name="frm1">
        昵称：<input type="text" name="name">
        年龄：<input type="number" name="age">
        <input type="submit" value="submit">
    </form>

    <form action="paramdiff" method="post" name="frm2">
        昵称：<input type="text" name="name">
        年龄：<input type="number" name="age">
        <input type="submit" value="submit">
    </form>

    <form action="Objtyte" method="post" name="frm3">
        昵称：<input type="text" name="name">
        年龄：<input type="number" name="age">
        <input type="submit" value="submit">
    </form>
</body>
</html>
