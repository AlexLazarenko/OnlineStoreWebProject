<jsp:useBean id="allOrders" scope="request" type="java.util.List"/>
<%--
  Created by IntelliJ IDEA.
  User: AlexLazarenko
  Date: 21.06.2021
  Time: 21:38
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="property.language"/>

<jsp:include page="header.jsp"/>
<br>
<form action="${pageContext.request.contextPath}/Home?action=showUserOrdersByDate"
      method="post">
    Date from:
    <label>
        <input type="date" name="dateFrom" min="1920-01-01" max="2050-01-01" value="" required>
    </label>
    <label>
        Date to:
        <input type="date" name="dateTo" min="1920-01-01" max="2050-01-01" value="" required>
    </label>
    <input type="submit" value="<fmt:message key="submit.button"/>">
</form>

<br>
<form action="${pageContext.request.contextPath}/Home?action=showUserOrdersByIdDate"
      method="post">
    User id:
    <label>
        <input type="text" name="userId" pattern="\d{12}" placeholder="User id"
               title="User id" value="" required>
    </label>

    Date from:
    <label>
        <input type="date" name="dateFrom" min="1920-01-01" max="2050-01-01" value="" required>
    </label>
    <label>
        Date to:
        <input type="date" name="dateTo" min="1920-01-01" max="2050-01-01" value="" required>
    </label>
    <input type="submit" value="<fmt:message key="submit.button"/>">
</form>
<br>
<form action="${pageContext.request.contextPath}/Home?action=showOrderById"
      method="post">
    Order id:
    <label>
        <input type="text" name="orderId" pattern="\d{1,12}" placeholder="Order id"
               title="Order id" value="" required>
    </label>
    <input type="submit" value="<fmt:message key="submit.button"/>">
</form>
<br>

<table align="center" border="1" width="90%">
    <tr>
        <td colspan="10" align="center"><b>Your orders</b></td>

    </tr>

    <tr>
        <td width="180" align="center"><b>Order id</b></td>
        <td width="180" align="center"><b>Date</b></td>
        <td width="180" align="center"><b>Order status</b></td>
        <td width="180" align="center"><b>Dish</b></td>
        <td width="180" align="center"><b>Price</b></td>
        <td width="180" align="center"><b>Quantity</b></td>
        <td align="center"><b>Total price</b></td>
    </tr>
    <c:forEach items="${allOrders}" var="entry">
        <tr>
        <td>
                ${entry.id}
        </td>


        <td>
            <fmt:formatDate value="${entry.date}" pattern="yyyy-MM-dd HH:mm:ss"/>
        </td>
        <td>
            <form action="${pageContext.request.contextPath}/Home?action=updateOrderStatus&id=${entry.id}"
                  method="post">
                <select name="status" id="select_status">
                    <option value="${entry.status}">${entry.status}</option>
                    <option value="NEW">NEW</option>
                    <option value="RUNNING">RUNNING</option>
                    <option value="READY">READY</option>
                    <option value="DONE">DONE</option>
                    <option value="CANCELED">CANCELED</option>
                </select>
                <input type="submit" value="<fmt:message key="submit.button"/>">
            </form>
        </td>
        <c:forEach items="${entry.dishMap}" var="map" varStatus="status">
            <td>
                    ${map.key.name}
            </td>
            <td>
                    ${map.key.price}
            </td>
            <td>
                    ${map.value}
            </td>
            <td>
                    ${map.key.price*map.value}
            </td>
            </tr>
            <c:choose>
                <c:when test="${status.last}">
                </c:when>
                <c:otherwise>
                    <td>
                    </td>


                    <td>
                    </td>
                    <td>
                    </td>
                </c:otherwise>
            </c:choose>

        </c:forEach>
        <tr>
            <td>
            </td>
            <td>
            </td>
            <td>
            </td>


            <td>
            </td>
            <td>
            </td>
            <td>
            </td>
            <td>
                Price:${entry.price*100/(100-entry.discount)} Discount:${entry.discount} Final price:${entry.price}
            </td>
        </tr>
    </c:forEach>
</table>

<jsp:include page="footer.jsp"/>

