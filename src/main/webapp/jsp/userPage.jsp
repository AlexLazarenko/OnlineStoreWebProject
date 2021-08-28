<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="ctg" uri="customtags" %>
<jsp:useBean id="user" scope="session" type="edu.epam.web.entity.User"/>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="property.language"/>
<jsp:include page="header.jsp"/>

<fmt:message key="header.login.page"/>
<br>
<br>
<c:choose>
    <c:when test="${user.avatar==null}">
        <br>
        <p><img src="${pageContext.request.contextPath}/fronts/avatar.jpg" alt="avatar"
                width="820" height="502" style="vertical-align:middle;margin:0px 50px">
        </p>
    </c:when>
    <c:otherwise>
        <br>
        <p><img src="${pageContext.request.contextPath}/upload/?url=C:/Pictures/${user.avatar}" alt="avatar"
                width="502" height="502" style="vertical-align:middle;margin:0px 50px">
        </p>
    </c:otherwise>
</c:choose>

<br>
<div style="vertical-align:middle;margin:0px 50px">
    <fmt:message key="header.telephone.number"/>${user.telephoneNumber}
    <br>
    <fmt:message key="header.surname"/>${user.surname}
    <br>
    <fmt:message key="header.name"/>${user.name}
    <br>
    <fmt:message key="header.birthday"/>${user.birthday}
    <br>
    <fmt:message key="header.gender"/>${user.gender}
    <br>
    <fmt:message key="header.email"/>${user.email}
    <br>
    <fmt:message key="table.all.users.column.s.point"/>:${user.statusPoint}
    <br>
    <fmt:message key="placeholder.user.status"/>${user.userStatus}
    <br><br>
    <a href="<%=request.getContextPath()%>/Home?action=updateUser"><fmt:message key="update.user.profile.link"/></a>
    <br>
    <a href="<%=request.getContextPath()%>/Home?action=uploadUserAvatar"> <fmt:message key="update.avatar.link"/></a>
    <br>
    <a href="<%=request.getContextPath()%>/Home?action=changePassword"> <fmt:message key="update.password.link"/></a>
</div>

<jsp:include page="footer.jsp"/>