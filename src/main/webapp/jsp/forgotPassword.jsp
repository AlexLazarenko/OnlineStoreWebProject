<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 27.12.2020
  Time: 13:01
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="property.language"/>
<jsp:include page="header.jsp"/>
<style>
    input:required:invalid {
        border: 1px solid red;}
    input:required:valid {
        border: 2px solid green;}
</style>
<fmt:message key="header.forgot.password.page"/><br>

<br>
<form action="${pageContext.request.contextPath}/Home?action=forgotPasswordResult" method="post">

    <fmt:message key="header.email"/><br>
    <label>
        <input type="email" name="email" pattern="^[-0-9a-zA-Z.+_]+@[-0-9a-zA-Z.+_]+\.[a-zA-Z]{2,7}"
               title="<fmt:message key="title.email"/>" placeholder="<fmt:message key="placeholder.email"/>" value="" required>
    </label>
    <c:out value="${messages['email']}"/>
    <br>
    <fmt:message key="forgot.password.message"/>
    <br>
    <input type="submit" value="<fmt:message key="submit.button"/>">
    <br>
    <c:out value="${messages['message']}"/>

</form>
<br>
<jsp:include page="footer.jsp"/>
