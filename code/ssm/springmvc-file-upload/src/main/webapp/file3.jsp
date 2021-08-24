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

</body>
    <script>
        async function showFiles() {
            let response = await fetch("file3", {
                method: "post"
            });

            let files = await response.json();
            let str = "";
            files.forEach(file => {
                let fileName = file.substring(file.lastIndexOf("=") + 1);
                str += `<a href="${file}">${fileName}</a><br>`
            });
            document.body.innerHTML = str;
        }
        showFiles();
    </script>
</html>
