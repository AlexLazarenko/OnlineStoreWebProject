<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 28.12.2020
  Time: 15:32
  To change this template use File | Settings | File Templates.
--%>
<jsp:include page="header.jsp"/>

<table align="center" border="1" width="90%">
    <tr>
        <td colspan="10" align="center"><b>All users</b></td>

    </tr>

    <tr>
        <td align="center"><b>ID</b></td>
        <td align="center"><b>Telephone number</b></td>
        <td align="center"><b>Surname</b></td>
        <td align="center"><b>Name</b></td>
        <td align="center"><b>Birthday</b></td>
        <td align="center"><b>Gender</b></td>
        <td align="center"><b>Email</b></td>
        <td align="center"><b>Status point</b></td>
        <td align="center"><b>User role</b></td>
        <td align="center"><b>Account status</b></td>
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
                        <option value="CLIENT">CLIENT</option>
                        <option value="STAFF">STAFF</option>
                        <option value="ADMIN">ADMIN</option>
                    </select>
                    <input type="submit" value="Submit">
                </form>
            </label></td>
            <td><label>
                <form action="${pageContext.request.contextPath}/Home?action=updateAccountStatus&id=<c:out value="${entry.id}"/>" method="post">
                    <select name="account_status" id="select_status">
                        <option value="${entry.accountStatus}">${entry.accountStatus}</option>
                        <option value="NEW">NEW</option>
                        <option value="ACTIVE">ACTIVE</option>
                        <option value="BAN">BAN</option>
                    </select>
                    <input type="submit" value="Submit">
                </form>
            </label></td>
        </tr>
    </c:forEach>

</table>

<jsp:include page="footer.jsp"/>
