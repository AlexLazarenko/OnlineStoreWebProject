<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="map" scope="session" type="java.util.HashMap"/>

<%--
  Created by IntelliJ IDEA.
  User: AlexLazarenko
  Date: 16.06.2021
  Time: 11:17
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="property.language"/>
<jsp:include page="header.jsp"/>

<table align="center" border="1" width="90%">
    <tr>
        <td colspan="10" align="center"><b><fmt:message key="table.header.shopping.cart"/></b></td>

    </tr>

    <tr>
        <td width="300" align="center"><b><fmt:message key="table.shopping.cart.column.dish"/></b></td>
        <td width="300" align="center"><b><fmt:message key="table.shopping.cart.column.price"/></b></td>
        <td width="300" align="center"><b><fmt:message key="table.shopping.cart.column.quantity"/></b></td>
        <td align="center"><b><fmt:message key="table.shopping.cart.column.total"/></b></td>
    </tr>
    <c:forEach items="${map}" var="entry">
        <tr>
            <td>
                    ${entry.key.name}
            </td>
            <td>
                    ${entry.key.price}
            </td>
            <td>
                <form action="${pageContext.request.contextPath}/Home?action=updateShoppingCart&id=<c:out value="${entry.key.id}"/>"
                      method="post">
                    <select name="quantity" id="select_quantity">
                        <option value="${entry.value}">${entry.value}</option>
                        <option value="0">0</option>
                        <option value="1">1</option>
                        <option value="2">2</option>
                        <option value="3">3</option>
                        <option value="4">4</option>
                        <option value="5">5</option>
                    </select>
                    <input type="submit" value="<fmt:message key="submit.button"/>">
                </form>
            </td>
            <td>
                    ${entry.key.price*entry.value}
            </td>
        </tr>
    </c:forEach>
    <tr>
        <td>
        </td>
        <td>
        </td>
        <td>
        </td>
        <td><fmt:message key="placeholder.price"/>${totalPrice}   <fmt:message key="placeholder.discount"/>${discount}
            <fmt:message key="placeholder.your.price"/>${discountPrice}</td>
    </tr>
</table>
<form action="${pageContext.request.contextPath}/Home?action=makeOrder" method="post">
    <input type="submit" value="<fmt:message key="submit.button.make.order"/>">
</form>

<jsp:include page="footer.jsp"/>
