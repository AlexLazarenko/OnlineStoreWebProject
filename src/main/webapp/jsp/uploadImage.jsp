<%--
  Created by IntelliJ IDEA.
  User: AlexLazarenko
  Date: 02.03.2021
  Time: 18:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Поле загрузки файлов, которое мы заслужили</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery-3.3.1.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/script.js"></script>
</head>
<body>
<form id="upload-container" method="POST" action="${pageContext.request.contextPath}/Home?action=uploadImage&id=${user.id}">
    <img id="upload-image" src="${pageContext.request.contextPath}/assets/fronts/upload.svg">
    <div>
        <input id="file-input" type="file" name="file" multiple>
        <label for="file-input">Выберите файл</label>
        <span>или перетащите его сюда</span>
    </div>
</form>
</body>
</html>