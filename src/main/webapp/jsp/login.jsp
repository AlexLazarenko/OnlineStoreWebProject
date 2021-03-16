<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 27.12.2020
  Time: 13:02
  To change this template use File | Settings | File Templates.
--%>
<jsp:include page="header.jsp"/>

<br><br>
<form action="${pageContext.request.contextPath}/Home?action=login" method="post">

    Telephone number:<br>
    <label>
        <input type="text" name="telephone" pattern="\d{12}" placeholder="Telephone number"
               title="Telephone number with full code" value="" required>
    </label>
    <jsp:useBean id="messages" scope="request" type="java.util.HashMap"/>
    <c:out value="${messages['telephone']}"/>
    <br>

    Password:<br>
    <label>
        <input type="password" name="password"  pattern="^\S{8,25}$" title="Enter your password"
               placeholder="Password" value="" required>
    </label>
    <c:out value="${messages['password']}"/>

    <br><br>
    <input type="submit" value="Submit">
</form>
<br><br>
<a href="${pageContext.request.contextPath}/Home?action=registration">Go to registration page</a>

<jsp:include page="footer.jsp"/>
