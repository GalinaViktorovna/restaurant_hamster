<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link href='<c:url value="/theme/main.css" />' rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.6.3/css/font-awesome.min.css">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Open+Sans" rel="stylesheet">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <title>Contacts</title>
</head>

<body>

<jsp:include page="main_menu.jsp"/>

<div class="row"
     style="color: white; background-image:url('<c:url value='/img/contact.jpg'/>'); margin:0; height:60%; background-size:100% auto">
</div>

<div class="container">
    <h3 class="text-center"><b>Contact us</b></h3>


    <div class="row" style="color:dimgrey; text-align: center;">
        <div class="col-md-3" style="align:center">
            <a href="#" class="btn button-transparent round-button" role="button">
                <i class="material-icons" style="font-size:36px">mail</i>
            </a>
            <br>HAMSTER@gmail.com
        </div>
        <div class="col-md-3" class="text-center">
            <a href="#" class="btn button-transparent round-button" role="button">
                <i class="material-icons" style="font-size:36px">phone</i>
            </a>
            <br>(057)321-54-76
        </div>
        <div class="col-md-3" class="text-center">
            <a href="#" class="btn button-transparent round-button" role="button">
                <i class="fa fa-facebook-official" style="font-size:36px"></i>
            </a>
            <br>HAMSTER
        </div>
        <div class="col-md-3" class="text-center">
            <a href="#" class="btn button-transparent round-button" role="button">
                <i class="fa fa-skype" style="font-size:36px"></i>
            </a>
            <br>HAMSTER
        </div>
    </div>
</div>

<hr>

<div class="container">
    <div class="text-center">
        <h3><b>Find us</b></h3>

        <address style="color:dimgrey">
            Our address: 9B Gogolya street, Ukraine
        </address>

        <div id="map" style="width:100%;height:500px"></div>
        <div></div>
    </div>
</div>

<jsp:include page="footer.jsp"/>

<script>
function myMap() {
  var myCenter = new google.maps.LatLng(49.996872,36.234693);
  var mapCanvas = document.getElementById("map");
  var mapOptions = {center: myCenter, zoom: 17};
  var map = new google.maps.Map(mapCanvas, mapOptions);
  var marker = new google.maps.Marker({position:myCenter});
  marker.setMap(map);
}


</script>
<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyChNVhml-nd5OLEFs5hs5zMxBxpnwbR1vM&callback=myMap"></script>

</body>
</html>