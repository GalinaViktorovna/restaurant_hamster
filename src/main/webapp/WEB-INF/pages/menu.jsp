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
    <title>Menu</title>
</head>
<body>

<jsp:include page="main_menu_adm.jsp"/>

<form:form modelAttribute="menu" method="POST" action="/menu" id="menuForm" enctype="multipart/form-data">

    <div class="container">
        <h2>Combo-menu <span class="glyphicon glyphicon-cutlery"></span></h2>
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
                <label for="description">Description:</label>
                <textarea class="form-control" name="description"></textarea>
            </div>
        </div>
        <div class="col-md-6">
            <div class="form-group">
                <br>
                <img src="/imageController/${menu.getPhoto().getId()}" style="height: 30%"/>
                <input type="file" name="img" accept="image/jpeg,image/png,image/gif" style="margin: 10px 10px 0px 0px"/>
            </div>
        </div>

        <h3>Dishes
            <button href="#" id="addDish" class="btn btn-warning">
                Add
            </button>
        </h3>
        <div class="panel-body">

            <div class="row">
                <div class="col-md-12">
                    <table class="table">
                        <thead>
                        <tr>
                            <th>ID</th>
                        </tr>
                        </thead>

                        <tbody id="menu">
                        <c:forEach items="${menu.dishes}" var="Dish" varStatus="i" begin="0">
                            <tr class="dish">
                                <td>
                                    <form:select path="dishes[${i.index}].id" id="$id{i.index}"
                                                 items="${allDishes}"
                                                 itemValue="id" itemLabel="name" class="form-control"/>
                                </td>
                                <td>
                                    <button href="#" class="removeDish btn btn-danger">Delete</button>
                                </td>
                            </tr>
                        </c:forEach>
                        <c:if test="${menu.dishes.size() == 0}">
                            <tr class="dish defaultRow">
                                <td>
                                    <form:select path="dishes[0].id" id="$id{0}"
                                                 items="${allDishes}"
                                                 itemValue="id" itemLabel="name" class="form-control"/>
                                </td>
                                <td>
                                    <button href="#" class="removeDish btn btn-danger">Delete</button>
                                </td>
                            </tr>
                        </c:if>
                        </tbody>
                    </table>
                </div>
            </div>
            <p class="text-right">
                <button type="submit" class="btn btn-success custom-width">OK</button>
                <a href="/menus" class="btn btn-danger custom-width" role="button">Cancel</a>
            </p>
        </div>
    </div>
    </div>
</form:form>

<script type="text/javascript">
    $(document).ready(function () {
        var config = {
            rowClass: 'dish',
            addRowId: 'addDish',
            removeRowClass: 'removeDish',
            formId: 'menuForm',
            rowContainerId: 'menu',
            indexedPropertyName: 'dishes',
            indexedPropertyMemberNames: 'id'
        };
        new DynamicListHelper(config);
    });

</script>

</body>
</html>