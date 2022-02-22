<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4"
            crossorigin="anonymous"></script>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

    <title>Форум</title>
</head>

<body>
<div class="container pt-3">
    <div class="row">
        <ul class="nav">
            <li class="nav-item">
                <a class="nav-link" href="<%=request.getContextPath()%>">Главная</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="<%=request.getContextPath()%>/create">Добавить пост</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="<%=request.getContextPath()%>/logout">${user.username} | Выйти</a>
            </li>
        </ul>
    </div>
</div>

<div class="container pt-3">
    <div class="card">
        <div class="card-body">
            <table class="table table-hover" id="table">
                <thead>
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">Имя</th>
                    <th scope="col">Описание</th>
                    <th scope="col">Автор</th>
                    <th scope="col">Дата</th>
                </tr>
                </thead>
                <tbody>
                <jsp:useBean id="posts" scope="request" type="java.util.List"/>
                <c:forEach items="${posts}" var="post">
                    <tr>
                        <td><c:out value="${post.id}"/></td>
                        <td>
                            <a href="<%=request.getContextPath()%>/view?id=${post.id}">
                                <c:out value="${post.name}"/>
                            </a>
                        </td>

                        <td><c:out value="${post.description}"/></td>
                        <td><c:out value="${post.user.username}"/></td>
                        <td><fmt:formatDate value="${post.created.time}" type="date" dateStyle="short"/></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>
</body>
</html>