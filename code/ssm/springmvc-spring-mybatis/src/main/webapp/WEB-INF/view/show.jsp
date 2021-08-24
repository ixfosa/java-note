<%--
  Created by IntelliJ IDEA.
  User: 86138
  Date: 2021/4/24
  Time: 20:36
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>show</title>
    <script src="${pageContext.request.contextPath}/static/js/jquery-3.4.1.js"></script>

    <script>
        $(function () {
            let str = "";
            $.post("show", {}, data => {
                data.forEach(stu => {
                    str += "<tr>";
                    str +=      "<td>"+ stu.id +"</td>"
                    str +=      "<td>"+ stu.name +"</td>"
                    str +=      "<td>"+ stu.email +"</td>"
                    str +=      "<td>"+ stu.age +"</td>"
                    str += "</tr>";
                })
                $("tbody").html(str);
            })
        })
    </script>
</head>
<body>
    <p>添加结果：${tips}</p>

    <table cellspacing="0" border="1">
        <thead>
            <tr>
                <th>编号</th>
                <th>姓名</th>
                <th>邮箱</th>
                <th>年龄</th>
            </tr>
        </thead>
        <tbody>

        </tbody>
    </table>
</body>
</html>
