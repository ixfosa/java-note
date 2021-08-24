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
            let response = await fetch("text2", {
                method: "post",
                body: new FormData(f1)
            });
            let result = await response.json();
            alert("name: " + result.name + "---" + result.password);
        }


        /*
        *   FormData：
        *       ------WebKitFormBoundarydWZhpGxFKrciMhZP
                Content-Disposition: form-data; name="name"

                夏
                ------WebKitFormBoundarydWZhpGxFKrciMhZP
                Content-Disposition: form-data; name="password"

                1
                ------WebKitFormBoundarydWZhpGxFKrciMhZP--


        *   URLSearchParams：
        *       name=val&password=val
        * */
    </script>
</body>
</html>
