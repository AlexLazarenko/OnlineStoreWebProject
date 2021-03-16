<jsp:include page="header.jsp"/>

<br>
MAIN PAGE
<br><br>
<a href="${pageContext.request.contextPath}/Home?action=login">Go to login page</a>
<br><br>
<a href="${pageContext.request.contextPath}/Home?action=registration">Go to registration page</a>
<br><br>
<a href="${pageContext.request.contextPath}/Home?action=showUser">Show  user</a>
<br><br>
<a href="${pageContext.request.contextPath}/Home?action=showAllUsers">Show all users</a>
<br><br>
<a href="${pageContext.request.contextPath}/Home?action=logout">Logout</a>
<br><br>
<a href="${pageContext.request.contextPath}/Home?action=uploadImage">Upload image</a>
<br><br>
<a href="${pageContext.request.contextPath}/Home?action=showUser&id=<c:out value="${user.id}"/>

<jsp:include page="footer.jsp"/>