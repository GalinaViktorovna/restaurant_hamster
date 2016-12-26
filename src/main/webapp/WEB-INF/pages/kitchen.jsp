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
    <title>Kitchen</title>
</head>

<body>

<jsp:include page="main_menu_adm.jsp"/>

<div class="container">
    <h2>Kitchen journal</h2>
    <p>history of cooking dishes </p>
    <a href="/kitchenItem" class="btn btn-warning" role="button">Add new</a>

    <table class="table">
        <thead>
        <tr>
            <th>ID</th>
            <th>Date</th>
            <th>Dish</th>
            <th>Order #</th>
            <th>Cook</th>
            <th></th>
        </tr>
        </thead>

        <tbody>
        <c:forEach var="kitchenItem" items="${kitchenJournal}">
            <tr>
                <td>${kitchenItem.id}</td>
                <td>${kitchenItem.date}</td>
                <td>${kitchenItem.getDish().getName()}</td>
                <td>${kitchenItem.getOrderInfo().getId()}</td>
                <td>${kitchenItem.getCook().getName()}</td>
                <td>
                    <a href="<c:url value='/kitchen/edit/${kitchenItem.getId()}'/>" class="btn btn-success"
                       role="button">Edit</a>
                    <a href="<c:url value='/kitchen/delete/${kitchenItem.getId()}'/>" class="btn btn-danger"
                       role="button">Remove</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>