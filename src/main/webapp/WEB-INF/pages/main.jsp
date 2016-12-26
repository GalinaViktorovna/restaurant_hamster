<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link href='<c:url value="/theme/main.css" />' rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.6.3/css/font-awesome.min.css">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Open+Sans" rel="stylesheet">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <title>Restaurant</title>
</head>

<body>

<jsp:include page="main_menu.jsp"/>

<div id="myCarousel" class="carousel slide" data-ride="carousel">

    <div class="carousel-indicators">
        <img src="<c:url value='/img/logo1.png'/>" alt="" style="height:30%">
    </div>

    <div class="carousel-inner">

        <div class="item active">
            <div class="fill" style="background-image:url('<c:url value='/img/back3.jpg'/>');">
                <div class="container">

                </div>
            </div>
        </div>

        <div class="item">
            <div class="fill" style="background-image:url('<c:url value='/img/back5.jpg'/>');">
                <div class="container">

                </div>
            </div>
        </div>

        <div class="item">
            <div class="fill" style="background-image:url('<c:url value='/img/back6.jpg'/>');">
                <div class="container">

                </div>
            </div>
        </div>

        <div class="item">
            <div class="fill" style="background-image:url('<c:url value='/img/back7.jpg'/>');">
                <div class="container">

                </div>
            </div>
        </div>

    </div>

    <h1>
        <a class="left carousel-control" href="#myCarousel" data-slide="prev">
            <span class="glyphicon glyphicon-chevron-left"></span>
        </a>
        <a class="right carousel-control" href="#myCarousel" data-slide="next">
            <span class="glyphicon glyphicon-chevron-right"></span>
        </a>
    </h1>

</div>


<div class="container">

    <div class="row">
        <div class="col-lg-6">
            <h3><b>Legendary pancakes done right</b></h3>
            <p>Sound too good to be true?
                <br>The HAMSTER is a unique little restaurant, located in the heart of historic Kharkiv.
                In our pancake restaurant we make authentic Dutch pancakes and Belgian waffles with delicious
                fillings and toppings, made from high quality certified organic ingredients.
                <br>Everything is freshly made on the spot, you can watch the cook make them!</p>
        </div>
        <div class="col-lg-6">
            <h3><b>Our propose</b></h3>
            <p>Tasty pancakes in perfect place!
                <br>We also serve great organic coffee, tea, hot cocoa, latte, fresh orange juice and premium cold drinks.
        </div>

    </div>
</div>

<hr>
<div class="row">
    <div class="col-lg-4" style="color: white; background-image:url('<c:url value='/img/wines.png'/>'); margin:0; height:40%; background-size:100% auto">
    </div>
    <div class="col-lg-4">
        <h3 class="text-center"><b>World-class tea and coffee</b></h3>
        <p class="text-center">
            Our organic & rainforest alliance certified coffee is small batch roasted weekly in Union City, New Jersey.
            Our extensive line of organic loose teas are selected & custom blended by Sullivan Street Tea & Spice Company, right here in Greenwich Village.
        </p>
    </div>
    <div class="col-lg-4" style="color: white; background-image:url('<c:url value='/img/glass.jpg'/>'); margin:0; height:40%; background-size:100% auto">
    </div>
</div>
<hr>



<div class="container">
    <div class="row" style="margin:0;">
        <div class="col-lg-6" style="color: white; background-image:url('<c:url value='/img/menus.jpg'/>'); margin:0; height:40%; background-size:100% auto">
            <br>
            <br><h3><b>New winter menu</b></h3>
            <br><a href="/dishes" class="btn button-transparent" role="button"><h3><b> MENU</b> </h3></a>
            <br>
        </div>
        <div class="col-lg-6" style="color: white; background-image:url('<c:url value='/img/combo.jpg'/>'); margin:0; height:40%; background-size:100% auto">
            <br>
            <br><h3><b>Better taste together</b></h3>
            <br><a href="/menus" class="btn button-transparent" role="button"><h3><b>COMBO</b></h3></b></a>
            <br>
        </div>

    </div>

    <hr>

    <h3><b>Follow us</b></h3>
    <div class="row">
        <div class="col-lg-12">
            <i class="fa fa-facebook-official" style="font-size:36px"></i>
            <i class="fa fa-skype" style="font-size:36px"></i>
            <i class="fa fa-instagram" style="font-size:36px"></i>
        </div>
    </div>
</div>

<hr>

<jsp:include page="footer.jsp"/>


<script>
 $('.carousel').carousel();
</script>


</body>
</html>