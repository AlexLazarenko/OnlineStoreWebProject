<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 27.12.2020
  Time: 13:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
</head>
<body>
<c:out value="${messages['login']}"/>
<br><br>
<form action="${pageContext.request.contextPath}/Home?action=login" method="post">

    Login:<br>
    <label>
        <input type="text" name="username" value="" required>
    </label>
    <c:out value="${messages['username']}"/>
    <br>

    Password:<br>
    <label>
        <input type="text" name="password" value="" required>
    </label>
    <c:out value="${messages['password']}"/>

    <br><br>
    <input type="submit" value="Submit">
</form>
<br>
<a href="${pageContext.request.contextPath}/Home?action=registration">Go to registration page</a>
</body>
</html>
