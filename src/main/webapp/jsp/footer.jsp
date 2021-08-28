<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 24.01.2021
  Time: 22:55
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="property.language"/>
<br><br>
<a href="${pageContext.request.contextPath}/Home?action=showShoppingCart"><fmt:message key="shopping.cart.link"/></a>
<br><br>
<a href="${pageContext.request.contextPath}/Home?action=showMenu"><fmt:message key="menu.page.link"/></a>
<br><br>
<a href="${pageContext.request.contextPath}/Home?action=home"><fmt:message key="main.page.link"/></a>
<br><br>
<a href="${pageContext.request.contextPath}/Home?action=logout"><fmt:message key="logout.link"/></a>

</body>
</html>
