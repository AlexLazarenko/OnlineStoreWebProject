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
    <td colspan="10" align="center"><b>Main page</b></td>

</tr>

<tr>
    <td align="center"><b>ID</b></td>
    <td align="center"><b>Password</b></td>
    <td align="center"><b>Nickname</b></td>
    <td align="center"><b>Surname</b></td>
    <td align="center"><b>Name</b></td>
    <td align="center"><b>Birthday</b></td>
    <td align="center"><b>Gender</b></td>
    <td align="center"><b>Email</b></td>
    <td align="center"><b>Status point</b></td>
    <td align="center"><b>User role</b></td>
</tr>

<c:forEach items="${allUsers}" var="entry">
    <tr>
    <td><label>
        <input type="text" value="${entry.id}" >
    </label></td>
    <td><label>
        <input type="text" value="${entry.password}" >
    </label></td>
    <td><label>
        <input type="text" value="${entry.nickname}" >
    </label></td>
    <td><label>
        <input type="text" value="${entry.surname}" >
    </label></td>
    <td><label>
        <input type="text" value="${entry.name}" >
    </label></td>
    <td><label>
        <input type="text" value="${entry.birthday}" >
    </label></td>
    <td><label>
        <input type="text" value="${entry.gender}" >
    </label></td>
    <td><label>
        <input type="text" value="${entry.email}" >
    </label></td>
    <td><label>
        <input type="text" value="${entry.statuspoint}" >
    </label></td>
    <td><label>
        <input type="text" value="${entry.role}" >
    </label></td>
        <c:if test = "${sessionScope.user.role == 'admin'}">
            <td><a href="<%=request.getContextPath()%>/Home?action=updateUser&id=<c:out value="${entry.id}"/>">Edit</a></td>
            <td><a href="<%=request.getContextPath()%>/Home?action=deleteUser&id=<c:out value="${entry.id}"/>">Remove</a></td>
        </c:if>
    </tr>
</c:forEach>

    </table>

<jsp:include page="footer.jsp"/>