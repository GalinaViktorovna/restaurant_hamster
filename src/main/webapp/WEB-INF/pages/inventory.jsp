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
    <title>Ingredients</title>
</head>

<body>

<jsp:include page="main_menu_adm.jsp"/>

<div class="container">
    <h2>Warehouse inventory</h2>
    <p>available ingredients </p>

    <form:form id="searchForm" action="/inventory/search/${name}" method="get">
        <form class="form-horizontal" role="form">
            <div class="form-group">
                <div class="col-sm-6">
                    <input type="text" class="form-control" id="name" name="name"/>
                </div>
                <div class="col-sm-6">
                    <button class="btn btn-default" type="submit"><span
                            class="glyphicon glyphicon-search"></span>Search
                    </button>
                    <a href="/inventoryItem" class="btn btn-warning" role="button">Add new</a>
                    <a href="/inventory/ending" class="btn btn-info" role="button">Show ending items</a>
                </div>
            </div>
        </form>
    </form:form>

    <table class="table">
        <thead>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Qty</th>
            <th></th>
        </tr>
        </thead>
        <tbody>

        <c:forEach var="inventoryItem" items="${inventory}">
            <tr>
                <td>${inventoryItem.id}</td>
                <td>${inventoryItem.getName()}</td>
                <td>${inventoryItem.quantity}</td>
                <td>
                    <a href="<c:url value='/inventory/edit/${inventoryItem.getId()}'/>" class="btn btn-success"
                       role="button">Edit</a>
                    <a href="<c:url value='/inventory/delete/${inventoryItem.getId()}'/>" class="btn btn-danger"
                       role="button">Remove</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>