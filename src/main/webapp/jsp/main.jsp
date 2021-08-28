<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="property.language"/>
<jsp:include page="header.jsp"/>

<br>
<fmt:message key="header.main.page"/>
<br><br>
<a href="${pageContext.request.contextPath}/Home?action=login"><fmt:message key="login.page.link"/></a>
<br><br>
<a href="${pageContext.request.contextPath}/Home?action=registration"><fmt:message key="registration.page.link"/></a>
<br><br>
<a href="${pageContext.request.contextPath}/Home?action=showUser"><fmt:message key="user.page.link"/></a>
<c:if test="${sessionScope.user.role == 'ADMIN'}">
    <br><br>
    <a href="${pageContext.request.contextPath}/Home?action=showAllUsers"><fmt:message key="users.list.page.link"/></a>
</c:if>
<br><br>
<a href="${pageContext.request.contextPath}/Home?action=showUserOrders"><fmt:message key="user.orders.link"/></a>
<br><br>
<a href="${pageContext.request.contextPath}/Home?action=showMenu"><fmt:message key="menu.page.link"/></a>
<br><br>
<a href="${pageContext.request.contextPath}/Home?action=addDish"><fmt:message key="add.dish.link"/></a>
<br><br>
<a href="${pageContext.request.contextPath}/Home?action=showAllOrders"><fmt:message key="all.orders.link"/></a>

<jsp:include page="footer.jsp"/>
