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
<form action="${pageContext.request.contextPath}/Home?action=forgotPasswordResult" method="post">

    Email:<br>
    <label>
        <input type="email" name="email" pattern="^[-0-9a-zA-Z.+_]+@[-0-9a-zA-Z.+_]+\.[a-zA-Z]{2,7}"
               title="Enter valid email like xxx@xxx.com" placeholder="Email" value="" required>
    </label>
    <c:out value="${messages['email']}"/>
    <br>
    If you push submit button generated password will be sent to your email address
    <br>
    <input type="submit" value="Submit">
    <br>
    <c:out value="${messages['message']}"/>

</form>
<br>
<jsp:include page="footer.jsp"/>
