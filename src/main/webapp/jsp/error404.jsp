<%--
  Created by IntelliJ IDEA.
  User: AlexLazarenko
  Date: 02.03.2021
  Time: 21:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isErrorPage="true" %>
<html>
<head>
    <title>Error page</title>
</head>
<body>
<div>
    <h1>Oops!</h1>
    <h2>404 Not Found</h2>
    <div >
        Sorry, an error has occured, Requested page not found!
    </div>
    <div >
        <a href="${pageContext.request.contextPath}/Home" class="btn btn-primary btn-lg">
            <span><i class="fas fa-home"></i></span> Take Me Home
        </a>
    </div>
</div>
</body>
</html>
