<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String basePath = request.getScheme() + "://" +
            request.getServerName() + ":" + request.getServerPort() +
            request.getContextPath() + "/";
%>
<html>
<head>
    <title>Title</title>
</head>
<body>

<a href= "<%=basePath%>/some/path">${basePath}/some/path</a><br>
<a href="some/path">some/path</a><br>
<%=basePath %>
</body>
</html>
