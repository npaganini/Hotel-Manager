
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<html>

<head>

    <link href="/resources/CSS/slideBar.css" rel="stylesheet">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
    <script src="/resources/js/jquery.min.js"></script>
    <script src="/resources/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="/resources/js/slideBar.js"></script>
    <link href="http://cdn.datatables.net/1.10.19/css/jquery.dataTables.min.css" rel="stylesheet">
    <script type="text/javascript" src="/resources/js/jquery.dataTables.min.js"></script>
    <link href="/resources/CSS/my_style.css" rel="stylesheet">
    <script src='https://kit.fontawesome.com/a076d05399.js'></script>

</head>
<body >
<!-- Image and text -->
<nav class="navbar navbar-inverse" style="margin-bottom: 0; border-radius: 0" >
    <div class="container-fluid" >
        <div class="navbar-header">
            <div class="col" style="text-align: left" >
                <a class="navbar-brand" href="#">e-Lobby</a>
            </div>
        </div>

    </div>
</nav>

<nav class="navbar navbar-inverse sidebar" role="navigation">
    <div class="container-fluid">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-sidebar-navbar-collapse-1">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">Brand</a>
        </div>
        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-sidebar-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li class="active"><a href="#">Home<span style="font-size:16px;" class="pull-right hidden-xs showopacity glyphicon glyphicon-home"></span></a></li>
                <li ><a href="#">Profile<span style="font-size:16px;" class="pull-right hidden-xs showopacity glyphicon glyphicon-user"></span></a></li>
                <li ><a href="#">Messages<span style="font-size:16px;" class="pull-right hidden-xs showopacity glyphicon glyphicon-envelope"></span></a></li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">Settings <span class="caret"></span><span style="font-size:16px;" class="pull-right hidden-xs showopacity glyphicon glyphicon-cog"></span></a>
                    <ul class="dropdown-menu forAnimate" role="menu">
                        <li><a href="#">Action</a></li>
                        <li><a href="#">Another action</a></li>
                        <li><a href="#">Something else here</a></li>
                        <li class="divider"></li>
                        <li><a href="#">Separated link</a></li>
                        <li class="divider"></li>
                        <li><a href="#">One more separated link</a></li>
                    </ul>
                </li>
                <li><a href="#">Home<span style="font-size:16px;" class="pull-right hidden-xs showopacity glyphicon glyphicon-home"></span></a></li>
                <li ><a href="#">Profile<span style="font-size:16px;" class="pull-right hidden-xs showopacity glyphicon glyphicon-user"></span></a></li>
                <li ><a href="#">Messages<span style="font-size:16px;" class="pull-right hidden-xs showopacity glyphicon glyphicon-envelope"></span></a></li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">Settings <span class="caret"></span><span style="font-size:16px;" class="pull-right hidden-xs showopacity glyphicon glyphicon-cog"></span></a>
                    <ul class="dropdown-menu forAnimate" role="menu">
                        <li><a href="#">Action</a></li>
                        <li><a href="#">Another action</a></li>
                        <li><a href="#">Something else here</a></li>
                        <li class="divider"></li>
                        <li><a href="#">Separated link</a></li>
                        <li class="divider"></li>
                        <li><a href="#">One more separated link</a></li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
</nav>
<div class="container cont" style="grid-auto-columns: auto; width: 100vw !important;margin-left: 0 !important; margin-right: 0 !important" >
    <br>
    <br>
    <div class="row" style="height: 45px" >
        <div class="col myheader" style="grid-auto-columns: auto">
            Check-in
        </div>
    </div>
        <br>
        <br>
    <div class="row" style="height: 45px">
        <div class="col-xs-5">
            <div class="form-question">
                <div class="form-question__title">
                    <span class="items">Desde:</span>
                </div>
                <div class="input-container">
                    <input id="from_date" type="date" name="effective-date" minlength="1" maxlength="64" placeholder=" " autocomplete="nope" required="required"></input>
                    <span class="bar"></span>
                </div>
            </div>
        </div>
        <div class="col-xs-5">
            <div class="form-question">
                <div class="form-question__title">
                    <span class="items">Hasta:</span>
                </div>
                <div class="input-container">
                    <input id="to_date" type="date" name="effective-date" minlength="1" maxlength="64" placeholder=" " autocomplete="nope" required="required"></input>
                    <span class="bar"></span>
                </div>
            </div>
        </div>
    </div>
    <br>
    <div class="row" style="height: 45px">
        <div class="col-xs-5">
            <span class="items">
                Habitacion:
            </span>
            <div id="room_number">
                <select>
                    <option value="blank">-</option>
                    <c:forEach var="room" items="${allRooms}">
                        <c:if test="${room.free == true}">
                        <option value="${room.id}"> ${room.number}</option>
                        </c:if>
                    </c:forEach>
                </select>
            </div>
        </div>
         <div class="col-xs-5">
              <span class="items">Email del titular:</span>
              <div class="input-group">
                 <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                 <input id="email" type="text" class="form-control" name="email" placeholder="Email">
             </div>
         </div>
    </div>

    <div class="row" style="height: 45px">
        <br><br>
        <div class="col-xs-2">
            <button type="button" class="btn btn-danger btn-lg"><a href="/rooms/checkin" style="color: white">Reservar</a> </button>
        </div>
        <div class="col-xs-2">
            <button type="button" class="btn btn-danger btn-lg"><a href="/rooms/checkin" style="color: white">Cancelar</a> </button>
        </div>

    </div>
</div>
</body>

<script>
</script>

</html>

