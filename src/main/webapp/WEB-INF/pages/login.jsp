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
    <title>Log in</title>
</head>

<body style="background: #eee">

<c:url var="loginUrl" value="/login"/>

<div class="container" style="margin-top: 80px">
    <div class="row">
        <div class="col-md-3"></div>
        <div class="col-md-6">
            <div class="panel panel-default"
                 style="border-color:darkgray; box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);">
                <div class="panel-heading text-center" style="background-color:rgba(0,0,0,0.7);">
                    <img src="<c:url value='/img/logo2.png'/>">
                </div>
                <div class="panel-body">
                    <form action="${loginUrl}" method="post" class="form-horizontal">
                        <div class="form-group">
                            <label class="control-label col-sm-2" for="username">
                                <!--<i class="fa fa-user"></i>-->
                                <p class="text-left">Login:</p>
                            </label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="username" name="username"
                                       placeholder="Enter Username"
                                       required>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-sm-2" for="password">
                                <!--<i class="fa fa-lock"></i>-->
                                <p class="text-left">Password:</p>
                            </label>
                            <div class="col-sm-10">
                                <input type="password" class="form-control" id="password" name="password"
                                       placeholder="Enter Password"
                                       required>
                            </div>
                        </div>

                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                        <div class="row">
                            <div class="col-md-3"></div>
                            <div class="col-md-6">
                                <button type="submit"
                                        class="btn btn-block" style="background-color: white;
    color: dimgrey;
    border: 2px solid dimgrey; "><b>LOG IN</b></button>
                            </div>
                            <div class="col-md-3"></div>
                        </div>

                        <c:if test="${param.error != null}">
                            <hr>
                            <div class="alert alert-danger" style="margin: 10px">
                                <p>Invalid username and password.</p>
                            </div>
                        </c:if>
                    </form>
                </div>
            </div>
        </div>
        <div class="col-md-3"></div>
    </div>
</div>
</body>
</html>