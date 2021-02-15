<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 27.12.2020
  Time: 13:01
  To change this template use File | Settings | File Templates.
--%>
<jsp:include page="header.jsp"/>
<c:out value="${messages['login']}"/>
<br><br>
<form action="${pageContext.request.contextPath}/Home?action=registration" method="post">

    Telephone number:<br>
        <label>
            <input type="text" name="id" value="" required>
        </label>
        <c:out value="${messages['id']}"/>
    <br>
        Login:<br>
        <input type="text" name="username" value="" required>
        <c:out value="${messages['username']}"/>
    <br>

    Password:<br>
        <label>
            <input type="text" name="password" value="" required>
        </label>
    <br>
        <input type="text" name="password" value="" required>
    <c:out value="${messages['password']}"/>
    <br>
        Nickname:<br>
        <label>
            <input type="text" name="nickname" value="" required>
        </label>
        <c:out value="${messages['nickname']}"/>
        <br>

        Surname:<br>
        <label>
           <input type="text" name="surname" value="" required>
       </label>
        <c:out value="${messages['surname']}"/>
        <br>

        Name:<br>
        <label>
           <input type="text" name="name" value="" required>
        </label>
        <c:out value="${messages['name']}"/>
        <br>

        Birthday:<br>
        <label>
            <input type="text" name="birthday" value="" required>
        </label>
        <c:out value="${messages['birthday']}"/>
        <br>

        Gender:<br>
        <label>
            <input type="text" name="gender" value="" required>
        </label>
        <c:out value="${messages['gender']}"/>
        <br>

        Email:<br>
        <label>
            <input type="text" name="email" value="" required>
        </label>
        <c:out value="${messages['email']}"/>

    <br><br>
    <input type="submit" value="Submit">
</form>
<br>
<jsp:include page="footer.jsp"/>
