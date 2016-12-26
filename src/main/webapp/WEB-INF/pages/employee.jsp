<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link href='<c:url value="/theme/main.css" />' rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.6.3/css/font-awesome.min.css">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Open+Sans" rel="stylesheet">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src='<c:url value="/js/dynamic_list_helper.js"/>' type="text/javascript"></script>
    <title>Employee</title>
</head>
<body>

<jsp:include page="main_menu_adm.jsp"/>

<form:form modelAttribute="employee" method="POST" action="/employee" enctype="multipart/form-data">

    <div class="container">
        <h2>Employee <span class="glyphicon glyphicon-user"></span></h2>
        <div class="col-md-6">
            <div class="form-group">
                <label for="id">ID:</label>
                <form:input type="text" class="form-control" path="id" readonly="true"/>
            </div>
            <div class="form-group">
                <label for="name">Name:</label>
                <form:input type="text" class="form-control" path="name"/>
            </div>
            <div class="form-group">
                <label for="salary">Salary:</label>
                <form:input type="number" class="form-control" path="salary" min="0"/>
            </div>
            <div class="form-group">
                <label for="position">Position:</label>
                <form:select path="position.id" id="position" items="${positions}" itemValue="id" itemLabel="name" class="form-control"/>
            </div>
        </div>
        <div class="col-md-6">
            <div class="form-group">
                <br>
                <c:choose>
                    <c:when test="${employee.getPhoto()!= null}">
                        <img src="/imageController/${employee.getPhoto().getId()}" style="height: 40%"/>
                    </c:when>
                    <c:otherwise>
                        <img src="<c:url value='/img/placeholder.jpg'/>" style="height: 40%">
                    </c:otherwise>
                </c:choose>
                <input type="file" name="img" accept="image/jpeg,image/png,image/gif" style="margin: 10px 10px 0px 0px"/>
            </div>
        </div>

        <p class="text-right">

            <button type="submit" class="btn btn-success custom-width">OK</button>
            <a href="/employees" class="btn btn-danger custom-width" role="button">Cancel</a>

        </p>
    </div>

</form:form>

</body>
</html>