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
    <script src='<c:url value="/js/dynamic_list_helper.js"/>' type="text/javascript"></script>
    <title>Cooked dish</title>
</head>

<body>
<jsp:include page="main_menu_adm.jsp"/>

<form:form modelAttribute="kitchenItem" method="POST" action="./kitchenItem">
    <div class="panel panel-default">
        <div class="panel-body">
            <h2>Cooked dish <span class="glyphicon glyphicon-apple"></span></h2>
            <div class="form-group">
                <label for="id">ID:</label>
                <form:input type="text" class="form-control" path="id" readonly="true"/>
            </div>
            <div class="form-group">
                <label for="date">Date:</label>
                <form:input type="text" class="form-control" path="date"/>
            </div>
            <div class="form-group">
                <label for="dish">Dish:</label>
                <form:select path="dish.id" id="dish" items="${dishes}" itemValue="id" itemLabel="name"
                             class="form-control"/>
            </div>
            <div class="form-group">
                <label for="orderInfo">Order:</label>
                <form:select path="orderInfo.id" id="orderInfo" items="${orders}" itemValue="id" itemLabel="id"
                             class="form-control"/>
            </div>
            <div class="form-group">
                <label for="cook">Cook:</label>
                <form:select path="cook.id" id="cook" items="${employees}" itemValue="id" itemLabel="name"
                             class="form-control"/>
            </div>
            <p class="text-right">
                <button type="submit" class="btn btn-success custom-width">OK</button>
                <a href="/kitchen" class="btn btn-danger custom-width" role="button">Cancel</a>
            </p>
        </div>
    </div>
</form:form>
</body>