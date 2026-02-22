<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Редактирование пользователя</title>
</head>
<body>

<h2>Редактировать пользователя</h2>

<form action="${pageContext.request.contextPath}/users/update" method="post">
    <%-- Спрятанное поле для ID --%>
    <input type="hidden" name="id" value="${user.id}">

    Имя: <input type="text" name="firstName" value="${user.firstName}" required><br>
    Фамилия: <input type="text" name="lastName" value="${user.lastName}" required><br>
    Email: <input type="email" name="email" value="${user.email}" required><br>
    <input type="submit" value="Обновить">
</form>

<br>
<a href="${pageContext.request.contextPath}/users">Вернуться к списку</a>

</body>
</html>