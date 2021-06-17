<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="user" scope="session" type="edu.epam.web.entity.User"/>
<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 28.12.2020
  Time: 19:40
  To change this template use File | Settings | File Templates.
--%>
<jsp:include page="header.jsp"/>

<br><br>
<form action="${pageContext.request.contextPath}/Home?action=updateResultUser" method="post">

    Telephone number:<br>
    <label>
        <input type="text" name="telephone" pattern="\d{12}" placeholder="Telephone number"
               title="Telephone number with full code" value="${user.telephoneNumber}" required>
    </label>
    <jsp:useBean id="messages" scope="request" type="java.util.HashMap"/>
    <c:out value="${messages['telephone']}"/>
    <br>

    Surname:<br>
    <label>
        <input type="text" name="surname" minlength="2" maxlength="25" title="Your surname"
               placeholder="Surname" value="${user.surname}" required>
    </label>
    <br>

    Name:<br>
    <label>
        <input type="text" name="name" minlength="2" maxlength="25" title="Your name" placeholder="Name" value="${user.name}"
               required>
    </label>
    <br>

    Birthday:<br>
    <label>
        <input type="date" name="birthday" min="1920-01-01" max="2020-01-01" value="${user.birthday}" required>
    </label>
    <br>

    Gender:<br>
    <label>
        <label><input type="radio" name="gender" value="NONE" checked> None</label><br>
        <label><input type="radio" name="gender" value="MALE"> Male</label><br>
        <label><input type="radio" name="gender" value="FEMALE"> Female</label>
    </label>
    <br>

    Email:<br>
    <label>
        <input type="email" name="email" pattern="^[-0-9a-zA-Z.+_]+@[-0-9a-zA-Z.+_]+\.[a-zA-Z]{2,7}"
               title="Enter valid email like xxx@xxx.com" placeholder="Email" value="${user.email}" required>
    </label>
    <c:out value="${messages['email']}"/>
    <br><br>
    <input type="submit" value="Submit">
    <br>
    <c:out value="${messages['message']}"/>

</form>
<br>
<a href="${pageContext.request.contextPath}/Home?action=showUser">Show user</a>
<br>
<jsp:include page="footer.jsp"/>
