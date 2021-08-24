
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>show</title>
</head>
<body>
    <ul>
        <c:forEach items="${files}" var="filename">
            <li>
                <a href="download?fileName=${filename}">${filename}</a>
            </li>
        </c:forEach>
    </ul>
</body>
</html>
