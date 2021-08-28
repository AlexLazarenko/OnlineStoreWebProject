<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="property.language"/>
<jsp:useBean id="user" scope="session" type="edu.epam.web.entity.User"/>
<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 28.12.2020
  Time: 19:40
  To change this template use File | Settings | File Templates.
--%>
<jsp:include page="header.jsp"/>
<fmt:message key="header.update.user.page"/>
<br><br>
<form action="${pageContext.request.contextPath}/Home?action=updateResultUser" method="post">

    <fmt:message key="header.telephone.number"/><br>
    <label>
        <input type="text" name="telephone" pattern="\d{12}"
               placeholder="<fmt:message key="placeholder.telephone.number"/>"
               title="<fmt:message key="title.telephone.number"/>" value="${user.telephoneNumber}" required>
    </label>
    <jsp:useBean id="messages" scope="request" type="java.util.HashMap"/>
    <c:out value="${messages['telephone']}"/>
    <br>

    <fmt:message key="header.surname"/><br>
    <label>
        <input type="text" name="surname" minlength="2" maxlength="25" title="<fmt:message key="title.surname"/>"
               placeholder="<fmt:message key="placeholder.surname"/>" value="${user.surname}" required>
    </label>
    <br>

    <fmt:message key="header.name"/><br>
    <label>
        <input type="text" name="name" minlength="2" maxlength="25" title="<fmt:message key="title.name"/>"
               placeholder="<fmt:message key="placeholder.name"/>" value="${user.name}"
               required>
    </label>
    <br>

    <fmt:message key="header.birthday"/><br>
    <label>
        <input type="date" name="birthday" min="1920-01-01" max="2021-01-01" value="${user.birthday}" required>
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
               title="<fmt:message key="title.email"/>" placeholder="<fmt:message key="placeholder.email"/>" value="${user.email}" required>
    </label>
    <c:out value="${messages['email']}"/>
    <br><br>
    <input type="submit" value="<fmt:message key="submit.button"/>">
    <br>
    <c:out value="${messages['message']}"/>

</form>
<br>
<a href="${pageContext.request.contextPath}/Home?action=showUser"><fmt:message key="user.page.link"/></a>
<br>
<jsp:include page="footer.jsp"/>
