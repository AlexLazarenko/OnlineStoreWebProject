<jsp:useBean id="user" scope="session" type="edu.epam.web.entity.User"/>
<jsp:include page="header.jsp"/>

<br>
<p><img src="${pageContext.request.contextPath}/fronts/avatar.jpg" alt="avatar"
        width="820" height="502" style="vertical-align:middle;margin:0px 50px">
</p>
<p><img src="${pageContext.request.contextPath}/Home?action=loadImage" alt="avatar"
        width="1137" height="571" style="vertical-align:middle;margin:0px 50px">
</p>
<br>
<div style="vertical-align:middle;margin:0px 50px">
    Telephone number:${user.telephoneNumber}
    <br>
    Surname:${user.surname}
    <br>
    Name:${user.name}
    <br>
    Birthday:${user.birthday}
    <br>
    Gender:${user.gender}
    <br>
    Email:${user.email}
    <br>
    Status point:${user.statusPoint}
    <br>
    User status:${user.userStatus}
    <br><br>
    <a href="<%=request.getContextPath()%>/Home?action=updateUser">Edit profile</a>
    <br>
    <a href="<%=request.getContextPath()%>/Home?action=uploadImage">Change avatar</a>
    <br>
    <a href="<%=request.getContextPath()%>/Home?action=changePassword">Change password</a>
</div>

<jsp:include page="footer.jsp"/>