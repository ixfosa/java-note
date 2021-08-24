<%--
  Created by IntelliJ IDEA.
  User: 86138
  Date: 2021/4/25
  Time: 20:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <form id="f1">
        <input type="file" multiple name="files">
        <input type="submit" value="submit">
    </form>

    <script>
        let f1 = document.getElementById("f1");
        f1.onsubmit = async (e) => {

            e.preventDefault();

            let response = await fetch("file2", {
                method: "post",
                body: new FormData(f1)
            });

            let result = await response.text();
            alert(result);
        }
    </script>
</body>
</html>
