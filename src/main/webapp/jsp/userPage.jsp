<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>

<body>

<br>
${user.id}
<br>
${user.nickname}
<br>
${user.surname}
<br>
${user.name}
<br>
${user.birthday}
<br>
${user.gender}
<br>
${user.email}
<br>
${user.statuspoint}
<br>

<br><br>
<a href="${pageContext.request.contextPath}/Home">Go to home page</a>
<br><br>
<td><a href="<%=request.getContextPath()%>/Home?action=updateUser&id=<c:out value="${user.id}"/>
</body>
</html>