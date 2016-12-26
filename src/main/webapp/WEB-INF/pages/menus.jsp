<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>

<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link href='<c:url value="/theme/main.css" />' rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.6.3/css/font-awesome.min.css">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Open+Sans" rel="stylesheet">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <title>Menu</title>
</head>

<body>
<security:authorize access="isAnonymous()">
        <jsp:include page="main_menu.jsp"/>

        <div class="row"
             style="color: white; background-image:url('<c:url value='/img/menu.jpg'/>'); margin:0; height:60%; background-size:100%">
        </div>

        <div class="container">
            <div class="row">
                <h3 class="text-center"><b>Menu</b></h3>
                <div class="text-center">
                    <form:form id="searchForm" action="/menus/search/${name}" method="get">
                        <form class="form-horizontal" role="form">
                            <div class="form-group">
                                <div class="col-sm-3"></div>
                                <div class="col-sm-5">
                                    <input type="text" class="form-control" id="name" name="name"/>
                                </div>
                                <div class="col-sm-1">
                                    <button class="btn btn-default" type="submit"><span
                                            class="glyphicon glyphicon-search"></span>Search
                                    </button>
                                    <!--<a href="/restaurant/menu" class="btn btn-warning" role="button">Add new</a>-->
                                </div>
                                <div class="col-sm-3"></div>
                            </div>
                        </form>
                    </form:form>
                </div>
            </div>

            <hr>

            <div class="row">
                <c:forEach var="menu" items="${menus}">
                    <div class="col-md-4 polaroid">
                        <div class="row"
                             style="background-image:url('/imageController/${menu.getPhoto().getId()}'); margin:0; height: 30%; background-repeat: no-repeat;
    background-position: center; background-size:contain">
                        </div>
                        <div class="container col-md-12 text-center">
                            <h4><b>${menu.getName()}</b></h4>
                            <p>
                                <c:forEach var="component" items="${menu.getDishes()}">
                                    ${component.getName()};
                                </c:forEach>
                            </p>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>

        <jsp:include page="footer.jsp"/>

</security:authorize>

<security:authorize access="isAuthenticated()">
        <jsp:include page="main_menu_adm.jsp"/>

        <div class="container">
            <h2>Combo-menu</h2>
            <p>list of available ready-made menus </p>

            <form:form id="searchForm" action="/menus/search/${name}" method="get">
                <form class="form-horizontal" role="form">
                    <div class="form-group">
                        <div class="col-sm-8">
                            <input type="text" class="form-control" id="name" name="name"/>
                         </div>
                        <div class="col-sm-4">
                            <button class="btn btn-default" type="submit"><span
                                    class="glyphicon glyphicon-search"></span>Search
                            </button>
                            <a href="/menu" class="btn btn-warning" role="button">Add new</a>
                        </div>
                    </div>
                </form>
            </form:form>

            <table class="table">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Photo</th>
                        <th>Name</th>
                        <th>Dishes</th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="menu" items="${menus}">
                        <tr>
                            <td>${menu.getId()}</td>
                            <td>
                                <img src="/imageController/${menu.getPhoto().getId()}" class="img-thumbnail"
                                     width="150"
                                     height="100"/>
                            </td>
                            <td>${menu.getName()}</td>
                            <td>
                                <c:forEach var="component" items="${menu.getDishes()}">
                                    ${component.getName()};
                                </c:forEach>
                            </td>
                            <td>
                                <a href="<c:url value='/menus/edit/${menu.getId()}'/>" class="btn btn-success"
                                   role="button">Edit</a>
                                <a href="<c:url value='/menus/delete/${menu.getId()}'/>" class="btn btn-danger"
                                   role="button">Remove</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
</security:authorize>
</body>
