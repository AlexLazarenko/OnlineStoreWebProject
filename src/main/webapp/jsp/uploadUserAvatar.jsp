<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="property.language"/>
<jsp:include page="header.jsp"/>
<fmt:message key="header.upload.avatar"/>
<br> <br>
<form action=upload/" method="post" enctype="multipart/form-data">
    <input type="hidden" name="command" value="uploadUserAvatarResult">
    <input type="file" name="file" required/>
    <input type="submit" />
</form>
<jsp:include page="footer.jsp"/>
