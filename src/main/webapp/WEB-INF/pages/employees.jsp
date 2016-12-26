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
    <title>Employees</title>
</head>

<body>

<security:authorize access="isAnonymous()">
    <jsp:include page="main_menu.jsp"/>

    <div class="row"
         style="color: white; background-image:url('<c:url value='/img/team.jpg'/>'); margin:0; height:60%; background-size:100%">
    </div>

    <div class="container">
        <div class="row">
            <div class="col-md-1"></div>
            <div class="col-md-10">
                <h3 class="text-center"><b>About us</b></h3>
                <p class="text-center">Driven by the desire to provide genuine hospitality for our guests
                    and our employees we offer a setting where people truly care about one another,
                    and show it in everything they do. The high level of respect and
                    enthusiasm that runs through our entire organization is evident in many ways,
                    including the longevity of our staff and managers,
                    some of whom have been with us for decades. </p>
            </div>
            <div class="col-md-1"></div>
        </div>

        <hr>

        <div class="row">
            <h3 class="text-center"><b>Our team</b></h3>
        </div>

        <div class="row">
            <c:forEach var="employee" items="${employees}">
                <div class="col-md-4 polaroid">
                    <c:choose>
                        <c:when test="${employee.getPhoto()!= null}">
                            <div class="row"
                                 style="background-image:url('/imageController/${employee.getPhoto().getId()}'); margin:0; height: 30%; background-repeat: no-repeat;
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

                    <div class="container col-md-12">
                        <h4 class="text-center"><b>${employee.getName()}</b></h4>
                        <p class="text-center">${employee.getPosition().getName()}</p>
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
        <h2>Employees</h2>
        <p>list of company's employees </p>

        <form:form id="searchForm" action="/employees/search/${name}" method="get">
            <form class="form-horizontal" role="form">
                <div class="form-group">
                    <div class="col-sm-8">
                        <input type="text" class="form-control" id="name" name="name"/>
                    </div>
                    <div class="col-sm-4">
                        <button class="btn btn-default" type="submit"><span
                                class="glyphicon glyphicon-search"></span>Search
                        </button>
                        <a href="/employee" class="btn btn-warning" role="button">Add new</a>
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
                <th>Position</th>
                <th></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="employee" items="${employees}">
                <tr>
                    <td>${employee.getId()}</td>
                    <td>
                        <c:choose>
                            <c:when test="${employee.getPhoto()!= null}">
                                <img src="/imageController/${employee.getPhoto().getId()}"
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
                    <td>${employee.getName()}</td>
                    <td>${employee.getPosition().getName()}</td>
                    <td>
                        <a href="<c:url value='/employees/edit/${employee.getId()}'/>" class="btn btn-success"
                           role="button">Edit</a>
                        <a href="<c:url value='/employees/delete/${employee.getId()}'/>" class="btn btn-danger"
                           role="button">Remove</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</security:authorize>
</body>