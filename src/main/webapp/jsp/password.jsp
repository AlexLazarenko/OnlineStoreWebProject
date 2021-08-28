<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: AlexLazarenko
  Date: 15.03.2021
  Time: 15:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="property.language"/>
<jsp:include page="header.jsp"/>

<fmt:message key="header.change.password.page"/>
<br><br>

<form action="${pageContext.request.contextPath}/Home?action=changePasswordResult" method="post">

    <fmt:message key="header.password"/><br>
    <label>
        <input type="password" name="password" pattern="^\S{8,25}$" title="<fmt:message key="title.password"/>"
               placeholder="<fmt:message key="placeholder.password"/>" value="" required>
    </label>
    <jsp:useBean id="messages" scope="request" type="java.util.HashMap"/>
    <c:out value="${messages['fail']}"/>
    <c:out value="${messages['password']}"/>
    <br>
    <fmt:message key="placeholder.new.password"/><br>
    <label>
        <input type="password" name="new_password" pattern="^\S{8,25}$"
               onchange="this.setCustomValidity(this.validity.patternMismatch ? '<fmt:message key="validity.message.password"/>' : '');
           if(this.checkValidity()) form.password_two.pattern = this.value;" title="<fmt:message key="title.new.password"/>"
               placeholder="<fmt:message key="placeholder.password"/>" required> </label>
    <c:out value="${messages['new_password']}"/>
    <br>
    <fmt:message key="header.confirm.password"/><br>
    <input type="password" name="password_two" pattern="^\S{8,25}$"
           onchange="this.setCustomValidity(this.validity.patternMismatch ? '<fmt:message key="validity.message.confirm.password"/>':'');"
           title="<fmt:message key="title.confirm.password"/>" placeholder="<fmt:message key="placeholder.confirm.password"/>" required>
    <br>

    <br><br>
    <input type="submit" value="<fmt:message key="submit.button"/>">
    <c:out value="${messages['message']}"/>
</form>
<jsp:include page="footer.jsp"/>
