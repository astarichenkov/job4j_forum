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
                <a class="nav-link" href="<%=request.getContextPath()%>/">Главная</a>
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
    <p><c:out value="${post.name}"/></p>
    <p><c:out value="${post.description}"/></p>


    <c:forEach items="${comments}" var="comment">
        <div class="card">
            <div class="card-body">
                <p><c:out value="${comment.user.username}"/></p>
                <p> <fmt:formatDate value="${post.created.time}" type="date" dateStyle="short"/></p>
                <p> <c:out value="${comment.text}"/></p>
            </div>
        </div>
    </c:forEach>


    <form name='comment' action="<c:url value='/comment/save?post_id=${post.id}'/>" method='POST'>
        <div class="form-group">
            <label>Новое сообщение</label>
            <input type="text" class="form-control" name="text" required>
        </div>
        <button type="submit" class="btn btn-primary">Отправить</button>
    </form>

</div>

</body>
</html>
