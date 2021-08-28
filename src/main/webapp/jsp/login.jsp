<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 27.12.2020
  Time: 13:02
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="property.language"/>

<jsp:include page="header.jsp"/>
<br>
<fmt:message key="header.login.page"/>
<br><br>
<form action="${pageContext.request.contextPath}/Home?action=loginResult" method="post">

    <fmt:message key="header.telephone.number"/><br>
    <label>
        <input type="text" name="telephone" pattern="\d{12}" placeholder="<fmt:message key="placeholder.telephone.number"/>"
               title="<fmt:message key="title.telephone.number"/>" value="" required>
    </label
    <jsp:useBean id="messages" scope="request" type="java.util.HashMap"/>
    <c:out value="${messages['telephone']}"/>
    <br>
    <br>

    <fmt:message key="header.password"/><br>
    <label>
        <input type="password" name="password"  pattern="^\S{8,25}$" title="<fmt:message key="title.password"/>"
               placeholder="<fmt:message key="placeholder.password"/>" value="" required>
    </label>
    <c:out value="${messages['password']}"/>

    <br><br>
    <input type="submit" value=<fmt:message key="submit.button"/>>
</form>
<br><br>
<a href="${pageContext.request.contextPath}/Home?action=registration"><fmt:message key="registration.page.link"/></a>
<br><br>
<a href="${pageContext.request.contextPath}/Home?action=forgotPassword"><fmt:message key="forgot.password.page.link"/></a>

<jsp:include page="footer.jsp"/>
