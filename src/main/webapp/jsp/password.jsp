<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: AlexLazarenko
  Date: 15.03.2021
  Time: 15:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/Home?action=changePasswordResult" method="post">

    Password:<br>
    <label>
        <input type="password" name="password" pattern="^\S{8,25}$" title="Enter your password"
               placeholder="Password" value="" required>
    </label>
    <jsp:useBean id="messages" scope="request" type="java.util.HashMap"/>
    <c:out value="${messages['fail']}"/>
    <c:out value="${messages['password']}"/>
    <br>
    New password:<br>
    <label>
        <input type="password" name="new_password" pattern="^\S{8,25}$"
               onchange="this.setCustomValidity(this.validity.patternMismatch ? 'Must have at least 8 characters' : '');
           if(this.checkValidity()) form.password_two.pattern = this.value;" title="Enter new password"
               placeholder="Password" required> </label>
    <c:out value="${messages['new_password']}"/>
    <br>
    Confirm password:<br>
    <input type="password" name="password_two" pattern="^\S{8,25}$"
           onchange="this.setCustomValidity(this.validity.patternMismatch ? 'Please enter the same Password as above':'');"
           title="Confirm your password" placeholder="Confirm Password" required>
    <br>

    <br><br>
    <input type="submit" value="Submit">
    <c:out value="${messages['message']}"/>
</form>
</body>
</html>
