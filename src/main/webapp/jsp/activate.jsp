<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="header.jsp"/>
<jsp:useBean id="messages" scope="request" type="java.util.List"/>
<c:out value="${messages['message']}"/>
<jsp:include page="footer.jsp"/>
