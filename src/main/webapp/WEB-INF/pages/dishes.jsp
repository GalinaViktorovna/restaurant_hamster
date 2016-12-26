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
                <form:form id="searchForm" action="/dishes/search/${name}" method="get">
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
                                <!--<a href="/restaurant/dish" class="btn btn-warning" role="button">Add new</a>-->
                            </div>
                            <div class="col-sm-3"></div>
                        </div>
                    </form>
                </form:form>
            </div>
        </div>

        <hr>

        <div class="row">
            <c:forEach var="dish" items="${dishes}">
                <div class="col-md-4 polaroid">
                    <c:choose>
                        <c:when test="${dish.getPhoto()!= null}">
                            <div class="row"
                                 style="background-image:url('/imageController/${dish.getPhoto().getId()}'); margin:0; height: 30%; background-repeat: no-repeat;
    background-position: center; background-size:contain">
                            </div>
                        </c:when>
                        <c:otherwise>
                            <div class="row"
                                 style="background-image:url('<c:url value='/img/placeholder.jpg'/>'); margin:0; height: 30%; background-repeat: no-repeat;
    background-position: center; background-size:contain">
                            </div>
                        </c:otherwise>
                    </c:choose>


                    <div class="container col-md-8">
                        <h4 class="text-left"><b>${dish.getName()}</b></h4>
                        <p class="text-left">${dish.getDescription()}</p>
                        <p class="text-left"><a href="#" style="color:dimgray">Learn more ></a></p>
                    </div>
                    <div class="container col-md-4">
                        <h4 class="text-right" style="color:dimgray">
                            <b>
                                ${dish.getPrice()} UAH
                            </b>
                        </h4>
                        <p class="text-right">
                            ${dish.getWeight()} g.
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

        <div>
            <h2>Dishes</h2>
            <p>list of available receipts </p>
        </div>

        <form:form id="searchForm" action="/dishes/search/${name}" method="get">
            <form class="form-horizontal" role="form">
                <div class="form-group">
                    <div class="col-sm-8">
                        <input type="text" class="form-control" id="name" name="name"/>
                    </div>
                    <div class="col-sm-4">
                        <button class="btn btn-default" type="submit"><span
                                class="glyphicon glyphicon-search"></span>Search
                        </button>
                        <a href="/dish" class="btn btn-warning" role="button">Add new</a>
                    </div>
                </div>
            </form>
        </form:form>

        <hr>

        <table class="table">
            <thead>
            <tr>
                <th>ID</th>
                <th>Photo</th>
                <th>Name</th>
                <th>Weight</th>
                <th>Price</th>
                <th></th>
            </tr>
            </thead>

            <tbody>
            <c:forEach var="dish" items="${dishes}">
                <tr>
                    <td>${dish.getId()}</td>
                    <td>
                        <c:choose>
                            <c:when test="${dish.getPhoto()!= null}">
                                <img src="/imageController/${dish.getPhoto().getId()}" class="img-thumbnail"
                                     width="150"
                                     height="100"/>
                            </c:when>
                            <c:otherwise>
                                <img src="<c:url value='/img/placeholder.jpg'/>" class="img-thumbnail"
                                     width="150"
                                     height="100">
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td>
                        <b>${dish.getName()}</b>
                        <p>${dish.getDescription()}</p>
                    </td>
                    <td>${dish.getWeight()} g.</td>
                    <td>${dish.getPrice()} UAH</td>
                    <td>
                        <a href="<c:url value='/dishes/edit/${dish.getId()}'/>" class="btn btn-success"
                           role="button">Edit</a>
                        <a href="<c:url value='/dishes/delete/${dish.getId()}'/>" class="btn btn-danger"
                           role="button">Remove</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>

</security:authorize>

</body>