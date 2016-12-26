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

<div class="panel panel-default">
    <div class="panel-head">
        <h2>Order # ${orderInfo.getId()}</h2>
    </div>
    <hr>
    <div class="panel-body">
        <div class="col-md-6">
            <div class="form-group">
                <h3>Date:</h3>
                ${orderInfo.getDate()}
            </div>
            <div class="form-group">
                <h3>Closed:</h3>
                <c:if test="${orderInfo.getClosed()}">
                    <span class="glyphicon glyphicon-ok"></span>
                </c:if>
            </div>
        </div>
        <div class="col-md-6">
            <div></div>
            <div class="form-group">
                <h3>Table #:</h3>
                ${orderInfo.getTableNumber()}
            </div>
            <div class="form-group">
                <h3>Waiter:</h3>
                ${orderInfo.getWaiter().getName()}
            </div>
        </div>
    </div>

    <div class="panel-body">
        <table class="table">
            <thead>
            <h3>
                Dishes
            </h3>
            <tr>
                <th></th>
                <th>Item</th>
                <th>Price</th>
                <th>Qty</th>
            </tr>
            </thead>

            <tbody id="order">
            <c:forEach items="${order.orderContent}" var="orderPosition">
                <tr class="orderPosition">
                    <td>
                        <img src="/imageController/${orderPosition.getDish().getPhoto().getId()}" class="img-thumbnail"
                             width="150"
                             height="100"/>
                    </td>
                    <td>
                        ${orderPosition.getDish().getName()}
                    </td>
                    <td>
                        ${orderPosition.getDish().getPrice()}
                    </td>
                    <td>
                        ${orderPosition.getQuantity()}
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>

        <p class="text-right">
            <a href="/orders" class="btn btn-info custom-width" role="button">OK</a>
        </p>
    </div>
</div>

</body>
</html>