<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link href='<c:url value="/theme/main.css" />' rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.6.3/css/font-awesome.min.css">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Open+Sans" rel="stylesheet">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <title>Positions</title>
</head>

<body>

<jsp:include page="main_menu_adm.jsp"/>

<div class="container">
    <h2>Positions</h2>
    <p>available positions <a href="/position" class="btn btn-warning" role="button">Add new</a></p>

    <table class="table">
        <thead>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th></th>
        </tr>
        </thead>
        <tbody>

        <c:forEach var="position" items="${positions}">
            <tr>
                <td>${position.id}</td>
                <td>${position.getName()}</td>
                <td>
                    <a href="<c:url value='/positions/edit/${position.getId()}'/>" class="btn btn-success"
                       role="button">Edit</a>
                    <a href="<c:url value='/positions/delete/${position.getId()}'/>" class="btn btn-danger"
                       role="button">Remove</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>