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
    <title>Order</title>
</head>

<body>
<jsp:include page="main_menu_adm.jsp"/>

<form:form modelAttribute="order" method="POST" action="/order" id="orderForm">
    <div class="panel panel-default">
        <div class="panel-body">
            <h2>Order <span class="glyphicon glyphicon-shopping-cart"></span></h2>
            <div class="col-md-6">
                <div class="form-group">
                    <label for="id">ID:</label>
                    <form:input type="text" class="form-control" path="id" readonly="true"/>
                </div>
                <div class="form-group">
                    <label for="date">Date:</label>
                    <form:input type="text" class="form-control" path="date"/>
                </div>
                <div class="form-group">
                    <label for="closed">Closed:</label>
                    <form:checkbox path="closed"/>
                </div>
            </div>
            <div class="col-md-6">
                <div></div>
                <div class="form-group">
                    <label for="tableNumber">Table #:</label>
                    <form:input type="number" class="form-control" path="tableNumber" min="0"/>
                </div>
                <div class="form-group">
                    <label for="waiter">Waiter:</label>
                    <form:select path="waiter.id" id="waiter" items="${employees}" itemValue="id" itemLabel="name"
                                 class="form-control"/>
                </div>
            </div>
        </div>

        <div class="panel-body">

            <table class="table">
                <thead>
                <h3>
                    Dishes
                    <button href="#" id="addOrderPosition" class="btn btn-warning">
                        Add
                    </button>
                </h3>
                <tr>
                    <th>Dish</th>
                    <th>Qty</th>
                </tr>
                </thead>

                <tbody id="order">
                <c:forEach items="${order.orderContent}" var="OrderPosition" varStatus="i" begin="0">
                    <tr class="orderPosition">
                        <td>
                            <form:input type="hidden" path="orderContent[${i.index}].id" id="$id{i.index}" class="form-control"
                                        readonly="true"/>

                            <form:select path="orderContent[${i.index}].dish.id" id="$dish{i.index}.id"
                                         items="${dishes}"
                                         itemValue="id" itemLabel="name" class="form-control"/>
                        </td>
                        <td>
                            <form:input path="orderContent[${i.index}].quantity" id="$quantity{i.index}"
                                        class="form-control" min="0"/>
                        </td>
                        <td>
                            <button href="#" class="removeDish btn btn-danger">Delete</button>
                        </td>
                    </tr>
                </c:forEach>

                <c:if test="${order.orderContent.size() == 0}">
                    <tr class="orderPosition defaultRow">
                        <td>
                            <form:input type="hidden" path="orderContent[0].id" id="$id{0}" class="form-control" readonly="true"/>

                            <form:select path="orderContent[0].dish.id" id="$dish{0}.id" items="${dishes}"
                                         itemValue="id" itemLabel="name" class="form-control"/>
                        </td>
                        <td>
                            <form:input path="orderContent[0].quantity" id="$quantity{0}" class="form-control" min="0"/>
                        </td>
                        <td>
                            <button href="#" class="removeDish btn btn-danger">Delete</button>
                        </td>
                    </tr>
                </c:if>
                </tbody>
            </table>

            <p class="text-right">
                <button type="submit" class="btn btn-success custom-width">OK</button>
                <a href="/orders" class="btn btn-danger custom-width" role="button">Cancel</a>
            </p>
        </div>
    </div>

</form:form>

<script type="text/javascript">
    $(document).ready(function () {
        var config = {
            rowClass: 'orderPosition',
            addRowId: 'addOrderPosition',
            removeRowClass: 'removeDish',
            formId: 'orderForm',
            rowContainerId: 'order',
            indexedPropertyName: 'orderContent',
            indexedPropertyMemberNames: 'dish.id, quantity'
        };
        new DynamicListHelper(config);
    });




</script>

</body>
</html>