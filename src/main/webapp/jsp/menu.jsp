<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: AlexLazarenko
  Date: 08.04.2021
  Time: 13:42
  To change this template use File | Settings | File Templates.
--%>
<jsp:include page="header.jsp"/>

<table align="center" border="1" width="90%">
    <tr>
        <td colspan="10" align="center"><b>Menu</b></td>

    </tr>

    <tr>
        <td width="800" align="center"><b>Dish</b></td>
        <td align="center"><b>Staff info</b></td>
    </tr>

    <jsp:useBean id="dishList" scope="request" type="java.util.List"/>
    <c:forEach items="${dishList}" var="entry">
        <tr>
            <td>
                    ${entry.name}
                <p><img src="${pageContext.request.contextPath}/fronts/dish.jpg" alt="picture"
                        width="200" height="200" style="vertical-align:middle;margin:0px 50px">
                        ${entry.clientInfo}.&nbsp Structure:
                    <c:forEach items="${entry.ingredientList}" var="list">
                        ${list.name},&nbsp
                    </c:forEach>
                </p>
                        <br>
                        <a href="<%=request.getContextPath()%>/Home?action=uploadDishImage&id=${entry.id}">Change image</a>
                        <br>
                    ${entry.status}
                <br>
                Size=${entry.size}
                <br>
                Price=${entry.price}
                        <br>
                        <form action="${pageContext.request.contextPath}/Home?action=addDishOrder/>" method="post">
                            <select name="quantity" id="select_quantity">
                                <option value="1">1</option>
                                <option value="2">2</option>
                                <option value="3">3</option>
                                <option value="4">4</option>
                                <option value="5">5</option>
                            </select>
                            <input type="submit" value="Add to cart">
            </td>
            <td>
                    ${entry.staffInfo}<br>
                <c:forEach items="${entry.ingredientList}" var="list">
                    ${list.name}&nbsp ${list.size}<br>
                </c:forEach>
                        <br>
                        <a href="<%=request.getContextPath()%>/Home?action=updateDish&id=${entry.id}">Update dish</a>
                        <br>
            </td>
        </tr>
    </c:forEach>

</table>

<jsp:include page="footer.jsp"/>