<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 27.12.2020
  Time: 13:01
  To change this template use File | Settings | File Templates.
--%>
<jsp:include page="header.jsp"/>
<style>
    input:required:invalid {
        border: 1px solid red;}
    input:required:valid {
        border: 2px solid green;}
</style>
<br><br>
<form action="${pageContext.request.contextPath}/Home?action=registrationResult" method="post">

    Telephone number:<br>
    <label>
        <input type="text" name="telephone" pattern="\d{12}" placeholder="Telephone number"
               title="Telephone number with full code" value="" required>
    </label>
    <jsp:useBean id="messages" scope="request" type="java.util.HashMap"/>
    <c:out value="${messages['telephone']}"/>
    <br>

    Password:<br>
    <label>
        <input type="password" name="password"  pattern="^\S{8,25}$"
               onchange="this.setCustomValidity(this.validity.patternMismatch ? 'Must have at least 8 characters' : '');
           if(this.checkValidity()) form.password_two.pattern = this.value;" title="Enter your password"
               placeholder="Password" required>    </label>
    <c:out value="${messages['password']}"/>
    <br>
    Confirm password:<br>
    <input type="password" name="password_two" pattern="^\S{8,25}$"
           onchange="this.setCustomValidity(this.validity.patternMismatch ? 'Please enter the same Password as above':'');"
           title="Confirm your password" placeholder="Confirm Password" required>
    <br>

    Surname:<br>
    <label>
        <input type="text" name="surname" minlength="2" maxlength="25" title="Your surname"
               placeholder="Surname" value="" required>
    </label>
    <br>

    Name:<br>
    <label>
        <input type="text" name="name" minlength="2" maxlength="25" title="Your name" placeholder="Name" value="" required>
    </label>
    <br>

    Birthday:<br>
    <label>
        <input type="date" name="birthday" min="1920-01-01" max="2020-01-01" value="" required>
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
               title="Enter valid email like xxx@xxx.com" placeholder="Email" value="" required>
    </label>
    <c:out value="${messages['email']}"/>
    <br><br>
    <input type="submit" value="Submit">
    <br>
    <c:out value="${messages['message']}"/>

</form>
<br>
<jsp:include page="footer.jsp"/>
