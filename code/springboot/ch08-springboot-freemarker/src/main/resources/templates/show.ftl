<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
    <table border="1" cellspacing="0" align="center">
        <tr>
            <th>ID</th>
            <th>name</th>
            <th>age</th>
        </tr>

        <#list list as user>
            <tr>
                <td>${user.id}</td>
                <td>${user.name}</td>
                <td>${user.age}</td>
            </tr>
        </#list>
</table>
</body>
