<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 28.12.2020
  Time: 15:32
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="property.language"/>
<jsp:include page="header.jsp"/>

<table align="center" border="1" width="90%">
    <tr>
        <td colspan="10" align="center"><b><fmt:message key="table.header.all.users"/></b></td>

    </tr>

    <tr>
        <td align="center"><b><fmt:message key="table.all.users.column.id"/></b></td>
        <td align="center"><b><fmt:message key="table.all.users.column.telephone.number"/></b></td>
        <td align="center"><b><fmt:message key="table.all.users.column.surname"/></b></td>
        <td align="center"><b><fmt:message key="table.all.users.column.name"/></b></td>
        <td align="center"><b><fmt:message key="table.all.users.column.birthday"/></b></td>
        <td align="center"><b><fmt:message key="table.all.users.column.gender"/></b></td>
        <td align="center"><b><fmt:message key="table.all.users.column.email"/></b></td>
        <td align="center"><b><fmt:message key="table.all.users.column.s.point"/></b></td>
        <td align="center"><b><fmt:message key="table.all.users.column.role"/></b></td>
        <td align="center"><b><fmt:message key="table.all.users.column.a.status"/></b></td>
    </tr>

    <jsp:useBean id="allUsers" scope="request" type="java.util.List"/>
    <c:forEach items="${allUsers}" var="entry">
        <tr>
            <td>
                <label>
                        ${entry.id}
                </label>
            </td>
            <td><label>
                    ${entry.telephoneNumber}
            </label></td>
            <td><label>
                    ${entry.surname}
            </label></td>
            <td><label>
                    ${entry.name}
            </label></td>
            <td><label>
                    ${entry.birthday}
            </label></td>
            <td><label>
                    ${entry.gender}
            </label></td>
            <td><label>
                    ${entry.email}
            </label></td>
            <td><label>
                    ${entry.statusPoint}
            </label></td>
            <td><label>
                <form action="${pageContext.request.contextPath}/Home?action=updateUserRole&id=<c:out value="${entry.id}"/>" method="post">
                    <select name="role" id="select_role">
                        <option value="${entry.role}" hidden="">${entry.role}</option>
                        <option value="CLIENT"><fmt:message key="user.role.value.client"/></option>
                        <option value="STAFF"><fmt:message key="user.role.value.staff"/></option>
                        <option value="ADMIN"><fmt:message key="user.role.value.admin"/></option>
                    </select>
                    <input type="submit" value="<fmt:message key="submit.button"/>">
                </form>
            </label></td>
            <td><label>
                <form action="${pageContext.request.contextPath}/Home?action=updateAccountStatus&id=<c:out value="${entry.id}"/>" method="post">
                    <select name="account_status" id="select_status">
                        <option value="${entry.accountStatus}">${entry.accountStatus}</option>
                        <option value="NEW"><fmt:message key="account.status.value.new"/></option>
                        <option value="ACTIVE"><fmt:message key="account.status.value.active"/></option>
                        <option value="BAN"><fmt:message key="account.status.value.ban"/></option
                    </select>
                    <input type="submit" value="<fmt:message key="submit.button"/>">
                </form>
            </label></td>
        </tr>
    </c:forEach>

</table>

<jsp:include page="footer.jsp"/>
