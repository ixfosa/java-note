<%--
  Created by IntelliJ IDEA.
  User: 86138
  Date: 2021/4/25
  Time: 16:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <form id="f1">
        <input type="text" name="name">
        <input type="password" name="password">
        <input type="submit" value="submit" id="btn">
    </form>

    <script>

        let f1 = document.getElementById("f1");

        f1.onsubmit = async (e) => {

            e.preventDefault();

            let name = document.getElementsByTagName("input")[1].value;
            let password = document.getElementsByTagName("input")[2].value;
            alert(name + password);
            let map = new Map();
            map.set("name", name);
            map.set("password", password);

            let response = await fetch("text1", {
                method: "post",
                // body: new URLSearchParams(map)
                // body: {"name":"ixfosa", "password":"123"}  // [object Object]
                body: "anfafjalfjof" // anfafjalfjof
            });
            let result = await response.json();
            alert("name: " + result.name + "---" + result.password);
        }
    </script>

    <%--
        如果是 enctype="application/x-www-form-urlencoded" 或 enctype="multipart/form-data" 的话，
        则为 FormData 方式。

        如果是 a`pplication/json 或 enctype="text/plain" 的话，则为 Request Payload 的方式。
    --%>
</body>
</html>
