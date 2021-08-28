<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: AlexLazarenko
  Date: 08.04.2021
  Time: 13:42
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="property.language"/>
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
            <td><label>
                <form action="${pageContext.request.contextPath}/Home?action=addDishOrder&id=<c:out value="${entry.id}"/>" method="post">
                    ${entry.name}


                    <c:choose>
                    <c:when test="${entry.dishImage==null}">
                    <br>
                        <p><img src="${pageContext.request.contextPath}/fronts/dish.jpg" alt="picture"
                        width="200" height="200" style="vertical-align:middle;margin:0px 50px">
                        </c:when>
                        <c:otherwise>
                            <br>
                            <p><img src="${pageContext.request.contextPath}/upload/?url=C:/Pictures/${entry.dishImage}" alt="picture"
                                    width="200" height="200" style="vertical-align:middle;margin:0px 50px">
                        </c:otherwise>
                        </c:choose>

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

                            <select name="quantity" id="select_quantity">
                                <option value="1">1</option>
                                <option value="2">2</option>
                                <option value="3">3</option>
                                <option value="4">4</option>
                                <option value="5">5</option>
                            </select>
                            <input type="submit" value="<fmt:message key="submit.button.add.to.cart"/>">
                        </form>
            </label>
            </td>
            <td><label>
                    ${entry.staffInfo}<br>
                <c:forEach items="${entry.ingredientList}" var="list">
                    ${list.name}&nbsp ${list.size}<br>
                </c:forEach>
                        <br>
                        <a href="<%=request.getContextPath()%>/Home?action=updateDish&id=${entry.id}"><fmt:message key="update.dish.page.link"/></a>
                        <br>
            </label>
            </td>
        </tr>
    </c:forEach>

</table>

<jsp:include page="footer.jsp"/>