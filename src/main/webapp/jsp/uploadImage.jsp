<jsp:include page="header.jsp"/>
<form action=${pageContext.request.contextPath}/Home?action=uploadImageResult&id=${user.id}" method="post" enctype="multipart/form-data">
    <input type="text" name="description" />
    <input type="file" name="file" />
    <input type="submit" />
</form>
<jsp:include page="footer.jsp"/>