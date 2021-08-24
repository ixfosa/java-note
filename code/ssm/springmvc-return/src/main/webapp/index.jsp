<%--
  Created by IntelliJ IDEA.
  User: 86138
  Date: 2021/4/24
  Time: 16:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>hello</title>
    <script src="js/jquery-3.4.1.js"></script>
    <script>
        $(function () {
            $("#btn1").click(function () {
                let name = $("#name").val();
                let age = $("#age").val();
                $.post("returnjson", {name: name, age: age}, (data)=>{
                    $("#data").html(data.name + "--" + data.age);
                } )
            });

            $("#btn2").click(function () {
                let str = ""
                $.post("returnstudents", {}, (data)=>{
                    data.forEach(item => {
                        str += item.name + "->" + item.age + "<br>"
                    })
                    $("#data").html(str);
                } )
            });

            $("#btn3").click(function () {
                $.post("returnstr", {}, (data)=>{
                    $("#data").html(data);
                } )
            });
        });
    </script>
</head>
<body>
    <a href="returnstring_view1">returnString_view1</a><br>
    <a href="returnstring_view2">returnString_view2</a><br>

    <hr>

    <div id="data" style="height: 200px; width: 300px"></div>

    <form action="returnjson" method="post">
        <input type="text" name="name" id="name">
        <input type="number" name="age" id="age">
        <input type="button" value="submit" id="btn1">
    </form>

    <button type="button" id="btn2" value="returnstudents">returnstudents</button>
    <button type="button" id="btn3" value="returnstr">returnstr</button>


</body>
</html>
