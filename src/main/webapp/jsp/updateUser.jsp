<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 28.12.2020
  Time: 19:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Update</title>
</head>
<body>
<c:out value="${messages['login']}"/>
<br><br>
<form action="${pageContext.request.contextPath}/Home?action=editResult&id=${user.id}" method="post">

    Telephone number:<br>
    <label>
        <input type="text" name="id" value="" required>
    </label>
    <c:out value="${messages['id']}"/>
    Login:<br>
    <input type="text" name="username" value="" required>
    <c:out value="${messages['username']}"/>
    <br>

    Password:<br>
    <label>
        <input type="text" name="password" value="" required>
    </label>
    <input type="text" name="password" value="" required>
    <c:out value="${messages['password']}"/>

    Nickname:<br>
    <label>
        <input type="text" name="nickname" value="" required>
    </label>
    <c:out value="${messages['nickname']}"/>
    <br>

    Surname:<br>
    <label>
        <input type="text" name="surname" value="" required>
    </label>
    <c:out value="${messages['surname']}"/>
    <br>

    Name:<br>
    <label>
        <input type="text" name="name" value="" required>
    </label>
    <c:out value="${messages['name']}"/>
    <br>

    Birthday:<br>
    <label>
        <input type="text" name="birthday" value="" required>
    </label>
    <c:out value="${messages['birthday']}"/>
    <br>

    Gender:<br>
    <label>
        <input type="text" name="gender" value="" required>
    </label>
    <c:out value="${messages['gender']}"/>
    <br>

    Email:<br>
    <label>
        <input type="text" name="email" value="" required>
    </label>
    <c:out value="${messages['email']}"/>

    <br><br>
    <input type="submit" value="Submit">
</form>
<br>
<a href="${pageContext.request.contextPath}/Home?action=showUser">Show user</a>
<br>
</body>
</html>
