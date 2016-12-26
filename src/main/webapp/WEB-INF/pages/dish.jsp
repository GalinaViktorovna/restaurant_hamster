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
    <script src= '<c:url value="/js/dynamic_list_helper.js"/>' type="text/javascript"></script>
    <title>Dish</title>
</head>
<body>

<jsp:include page="main_menu_adm.jsp"/>

<form:form modelAttribute="dish" method="POST" action="/dish" id="dishForm" enctype="multipart/form-data">

    <div class="panel panel-default">
        <div class="panel-body">
            <h2>Dish <span class="glyphicon glyphicon-cutlery"></span></h2>

            <div class="row">
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
                        <form:textarea type="text" class="form-control" path="description"/>
                    </div>

                    <div class="form-group" style="padding: 0px">
                        <div class="col-md-6" style="padding: 0px">
                            <label for="name">Weight, g.:</label>
                            <form:input type="number" class="form-control" path="weight" min="0"/>
                        </div>
                        <div class="col-md-6">
                            <label for="name">Price, UAH:</label>
                            <form:input type="number" class="form-control" path="price" min="0"/>
                        </div>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="form-group">
                        <br>
                        <c:choose>
                            <c:when test="${dish.getPhoto()!= null}">
                                <img src="/imageController/${dish.getPhoto().getId()}" style="height: 40%"/>
                            </c:when>
                            <c:otherwise>
                                <img src="<c:url value='/img/placeholder.jpg'/>" style="height: 40%">
                            </c:otherwise>
                        </c:choose>

                        <input type="file" name="img" accept="image/jpeg,image/png,image/gif" style="margin: 10px 10px 0px 0px"/>
                    </div>
                </div>
            </div>
        </div>

        <div class="panel-body">
            <div class="row">
                <div class="col-md-12">
                    <table class="table">
                        <thead>
                        <h3>Components
                            <button href="#" id="addIngredient" class="btn btn-warning">
                                <!--<span class="glyphicon glyphicon-plus"></span>-->
                                Add
                            </button>
                        </h3>
                        <tr>
                            <th>Ingredient</th>
                            <th>Qty</th>
                        </tr>
                        </thead>

                        <tbody id="dish">
                        <c:forEach items="${dish.components}" var="DishComponent" varStatus="i" begin="0">
                            <tr class="dishComponent">
                                <td>
                                    <form:input type="hidden" path="components[${i.index}].id" id="$id{i.index}" class="form-control"
                                                readonly="true"/>

                                    <form:select path="components[${i.index}].ingredient.id" id="$ingredient{i.index}.id"
                                                 items="${ingredients}"
                                                 itemValue="id" itemLabel="name" class="form-control"/>
                                </td>
                                <td>
                                    <form:input path="components[${i.index}].quantity" id="$quantity{i.index}"
                                                class="form-control" type="number" min="0"/>
                                </td>
                                <td>
                                    <button href="#" class="removeIngredient btn btn-danger">
                                        Delete
                                    </button>
                                </td>
                            </tr>
                        </c:forEach>

                        <c:if test="${dish.components.size() == 0}">
                            <tr class="dishComponent defaultRow">
                                <td>
                                    <form:input type="hidden" path="components[0].id" id="$id{0}" class="form-control" readonly="true"/>

                                    <form:select path="components[0].ingredient.id" id="$ingredient{0}.id" items="${ingredients}"
                                                 itemValue="id" itemLabel="name" class="form-control"/>
                                </td>
                                <td>
                                    <form:input path="components[0].quantity" id="$quantity{0}" class="form-control"
                                                type="number" min="0"/>
                                </td>
                                <td>
                                    <button href="#" class="removeIngredient btn btn-danger">
                                        Delete
                                    </button>
                                </td>
                            </tr>
                        </c:if>
                        </tbody>
                    </table>
                </div>
            </div>
            <p class="text-right">
                <button type="submit" class="btn btn-success custom-width">OK</button>
                <a href="/dishes" class="btn btn-danger custom-width" role="button">Cancel</a>
            </p>
        </div>
    </div>
</form:form>


<script type="text/javascript">
    $(document).ready(function () {
        var config = {
            rowClass: 'dishComponent',
            addRowId: 'addIngredient',
            removeRowClass: 'removeIngredient',
            formId: 'dishForm',
            rowContainerId: 'dish',
            indexedPropertyName: 'components',
            indexedPropertyMemberNames: 'id, ingredient.id, quantity'
        };
        new DynamicListHelper(config);
    });
</script>

</body>
</html>