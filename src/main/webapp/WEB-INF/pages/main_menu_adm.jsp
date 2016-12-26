<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<nav class="navbar navbar-inverse">

    <div class="container-fluid">

        <ul class="nav navbar-nav navbar-right ">
            <li><a href="/dishes"><h4>MENU</h4></a></li>
            <li><a href="/tables"><h4>BOOKING</h4></a></li>
            <li><a href="/contacts"><h4>CONTACTS</h4></a></li>
            <li><a href="/employees"><h4>ABOUT US</h4></a></li>

            <li>
                <a class="dropdown-toggle" data-toggle="dropdown"><h4>ADMINISTRATION</h4></a>
                <ul class="dropdown-menu">
                    <li><a href="/dishes">MENU</a></li>
                    <li><a href="/menus">COMBO</a></li>
                    <li><a href="/orders">ORDERS</a></li>
                    <li><a href="/inventory">INGREDIENTS</a></li>
                    <li><a href="/kitchen">KITCHEN</a></li>
                    <li><a href="/employees">EMPLOYEES</a></li>
                    <li><a href="/positions">POSITIONS</a></li>
                </ul>
            </li>
            <li>
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                    <h4>
                        <span class="glyphicon glyphicon-user"></span>
                        <span class="text-uppercase"><security:authentication property="principal.username"/></span>
                    </h4>
                </a>
                <ul class="dropdown-menu">
                    <li><a href="/logout">LOGOUT</a></li>
                </ul>
            </li>
        </ul>

    </div>
</nav>