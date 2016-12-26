<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link href='<c:url value="/theme/main.css" />' rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.6.3/css/font-awesome.min.css">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Open+Sans" rel="stylesheet">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <title>Resrvation</title>
</head>

<body>

<jsp:include page="main_menu.jsp"/>

<div class="row"
     style="color: white; background-image:url('<c:url value='/img/booking.jpg'/>'); margin:0; height:60%; background-size:100%">
</div>

<div class="container">
    <div class="text-center">
        <h3><b>Make your reservation:</b></h3>
        <p>We recommend reservations for all of our tables and services.
            Reservations can be made online (via the link above), or by calling our reservations line.
            If you have a large party or group, we recommend giving us a call for assistance with booking. </p>

        <div class="row" style="color:dimgrey; text-align: center;">
            <div class="col-md-3" style="align:center"></div>
            <div class="col-md-3" style="align:center">
                <a href="#" class="btn button-transparent round-button" role="button">
                    <i class="material-icons" style="font-size:36px">mail</i>
                </a>
                <br>reserve.hamster@gmail.com
            </div>
            <div class="col-md-3" class="text-center">
                <a href="#" class="btn button-transparent round-button" role="button">
                    <i class="material-icons" style="font-size:36px">phone</i>
                </a>
                <br>(057)321-54-76
            </div>
            <div class="col-md-3" style="align:center"></div>
        </div>

        <hr>

        <img src='<c:url value="/img/tables.png"/>' width="1000" height="600">
    </div>
</div>


<div></div>

<jsp:include page="footer.jsp"/>


</body>
</html>