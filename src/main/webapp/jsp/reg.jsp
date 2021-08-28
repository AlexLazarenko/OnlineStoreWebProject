<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="property.language"/>
<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 27.12.2020
  Time: 13:01
  To change this template use File | Settings | File Templates.
--%>
<jsp:include page="header.jsp"/>
<style>
    input:required:invalid {
        border: 1px solid red;}
    input:required:valid {
        border: 2px solid green;}
</style>
<fmt:message key="header.registration.page"/>
<br><br>
<form action="${pageContext.request.contextPath}/Home?action=registrationResult" method="post">

    <fmt:message key="header.telephone.number"/><br>
    <label>
        <input type="text" name="telephone" pattern="\d{12}" placeholder="<fmt:message key="placeholder.telephone.number"/>"
               title="<fmt:message key="title.telephone.number"/>" value="" required>
    </label>
    <jsp:useBean id="messages" scope="request" type="java.util.HashMap"/>
    <c:out value="${messages['telephone']}"/>
    <br>

    <fmt:message key="header.password"/><br>
    <label>
        <input type="password" name="password"  pattern="^\S{8,25}$"
               onchange="this.setCustomValidity(this.validity.patternMismatch ? '<fmt:message key="validity.message.password"/>' : '');
           if(this.checkValidity()) form.password_two.pattern = this.value;" title="<fmt:message key="title.password"/>"
               placeholder="<fmt:message key="placeholder.password"/>" required>    </label>
    <c:out value="${messages['password']}"/>
    <br>
    <fmt:message key="header.confirm.password"/><br>
    <input type="password" name="password_two" pattern="^\S{8,25}$"
           onchange="this.setCustomValidity(this.validity.patternMismatch ? '<fmt:message key="validity.message.confirm.password"/>':'');"
           title="<fmt:message key="title.confirm.password"/>" placeholder="<fmt:message key="placeholder.confirm.password"/>" required>
    <br>

    <fmt:message key="header.surname"/><br>
    <label>
        <input type="text" name="surname" minlength="2" maxlength="25" title="<fmt:message key="title.surname"/>"
               placeholder="<fmt:message key="placeholder.surname"/>" value="" required>
    </label>
    <br>

    <fmt:message key="header.name"/><br>
    <label>
        <input type="text" name="name" minlength="2" maxlength="25" title="<fmt:message key="title.name"/>" placeholder="<fmt:message key="placeholder.name"/>" value="" required>
    </label>
    <br>

    <fmt:message key="header.birthday"/><br>
    <label>
        <input type="date" name="birthday" min="1920-01-01" max="2021-01-01" value="" required>
    </label>
    <br>

    <fmt:message key="header.gender"/><br>
    <label>
        <label><input type="radio" name="gender" value="NONE" checked> <fmt:message key="gender.value.none"/></label><br>
        <label><input type="radio" name="gender" value="MALE"> <fmt:message key="gender.value.male"/></label><br>
        <label><input type="radio" name="gender" value="FEMALE"> <fmt:message key="gender.value.female"/></label>
    </label>
    <br>

    <fmt:message key="header.email"/><br>
    <label>
        <input type="email" name="email" pattern="^[-0-9a-zA-Z.+_]+@[-0-9a-zA-Z.+_]+\.[a-zA-Z]{2,7}"
               title="<fmt:message key="title.email"/>" placeholder="<fmt:message key="placeholder.email"/>" value="" required>
    </label>
    <c:out value="${messages['email']}"/>
    <br><br>
    <input type="submit" value="<fmt:message key="submit.button"/>">
    <br>
    <c:out value="${messages['message']}"/>

</form>
<br>
<jsp:include page="footer.jsp"/>
