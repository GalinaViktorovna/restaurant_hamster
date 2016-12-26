<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link href='<c:url value="/resources/theme/css/main.css" />' rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.6.3/css/font-awesome.min.css">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Open+Sans" rel="stylesheet">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src='<c:url value="/resources/theme/js/dynamic_list_helper.js"/>' type="text/javascript"></script>
    <title>Ingredient</title>
</head>

<body>
<jsp:include page="main_menu_adm.jsp"/>

<form:form modelAttribute="inventoryItem" method="POST" action="/inventoryItem">
    <div class="container">
        <h2>Ingredient on stock <span class="glyphicon glyphicon-stats"></span></h2>

        <div class="form-group">
            <label for="id">ID:</label>
            <form:input type="text" class="form-control" path="id" readonly="true"/>
        </div>
        <div class="form-group">
            <label for="name">Name:</label>
            <form:input type="text" class="form-control" path="name"/>
        </div>
        <div class="form-group">
            <label for="quantity">Qty:</label>
            <form:input type="number" class="form-control" path="quantity" min="0"/>
        </div>

        <p class="text-right">
            <button type="submit" class="btn btn-success custom-width">OK</button>
            <a href="/inventory" class="btn btn-danger custom-width" role="button">Cancel</a>
        </p>
    </div>
</form:form>
</body>