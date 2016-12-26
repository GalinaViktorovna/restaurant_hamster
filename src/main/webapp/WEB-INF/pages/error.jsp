<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>

<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link href='<c:url value="/theme/main.css" />' rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.6.3/css/font-awesome.min.css">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Open+Sans" rel="stylesheet">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <title>Error</title>
</head>
<body>

<security:authorize access="isAnonymous()">
    <jsp:include page="main_menu.jsp"/>
</security:authorize>
<security:authorize access="isAuthenticated()">
    <jsp:include page="main_menu_adm.jsp"/>
</security:authorize>

<div class="container" style="margin: 80px">
    <div class="row"
         style="color: white; background-image:url('<c:url value='/img/sorry.jpg'/>'); margin:0; height: 30%; background-repeat: no-repeat;
    background-position: center; ">
    </div>
    <p class="text-center">Operation failed! Please, contact administrator.</p>
    <div class="panel panel-danger">
        <div class="panel-heading">
            Error details:
        </div>
        <div class="panel-body">
            ${exception.getMessage()}
        </div>
    </div>
</div>

</body>
</html>