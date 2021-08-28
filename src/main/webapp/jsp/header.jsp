<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 24.01.2021
  Time: 23:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="ctg" uri="customtags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="property.language"/>
<!DOCTYPE html>
<html>
<body>
<ctg:infoTimeTag language="${language}"/>
<ctg:userInfoTag email="${user.email}" language="${language}"/>
<hr/>
<form action="${pageContext.request.contextPath}/Home?action=changeLanguage" method="post">
    <fmt:message key="header.choose.language"/>
    <select name="language" id="select_language">
        <option value="${language}">${language}</option>
        <option value="ru">ru</option>
        <option value="en">en</option>
    </select>
    <input type="submit" value=<fmt:message key="submit.button"/>>
    <br>
</form>
<hr/>

