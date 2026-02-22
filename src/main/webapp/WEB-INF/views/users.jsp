<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Список пользователей</title>
    <style>
        /* Самый минимум стилей, чтобы было не совсем уродливо */
        table { border-collapse: collapse; width: 100%; }
        th, td { border: 1px solid black; padding: 8px; text-align: left; }
        th { background-color: #f2f2f2; }
    </style>
</head>
<body>

<h2>Добавить нового пользователя</h2>
<form action="${pageContext.request.contextPath}/users/add" method="post">
    Имя: <input type="text" name="firstName" required><br>
    Фамилия: <input type="text" name="lastName" required><br>
    Email: <input type="email" name="email" required><br>
    <input type="submit" value="Добавить">
</form>

<h2>Список пользователей</h2>
<table>
    <tr>
        <th>ID</th>
        <th>Имя</th>
        <th>Фамилия</th>
        <th>Email</th>
        <th>Действия</th>
    </tr>
    <c:forEach var="user" items="${users}">
        <tr>
            <td>${user.id}</td>
            <td>${user.firstName}</td>
            <td>${user.lastName}</td>
            <td>${user.email}</td>
            <td>
                <a href="${pageContext.request.contextPath}/users/edit/${user.id}">Редактировать</a>
                |
                <a href="${pageContext.request.contextPath}/users/delete/${user.id}"
                   onclick="return confirm('Точно удалить?')">Удалить</a>
            </td>
        </tr>
    </c:forEach>
</table>

</body>
</html>