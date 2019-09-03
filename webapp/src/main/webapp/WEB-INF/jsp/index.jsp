<%--@ taglib prefix=“c” uri=“http://java.sun.com/jstl/core_rt”--%>
<html>

<head>

<%--    Slidebar--%>
<link href="/resources/CSS/slideBar.css" rel="stylesheet">
<link href="http://netdna.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="${pageContext.request.contextPath}/WEB-INF/bootstrap/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/WEB-INF/bootstrap/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="/resources/js/slideBar.js"></script>

<%--NavBar--%>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
<script src="/resources/js/jquery.min.js"></script>
<script src="/resources/js/bootstrap.min.js"></script>

    <link href="/resources/CSS/my_style.css" rel="stylesheet">

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
    <div class="row"  >
        <div class="col myheader" style="grid-auto-columns: auto">
            holaaaaa
<%--            <img src="/resouces/WEB-INF//log.png" style="width: 50% ; hight:50%">--%>
        </div>

            <div class="col-xs-6 form-group" style="z-index:9999;grid-auto-columns: auto">
                <label  class="col-form-label">Habitacion</label>
                <select class="chosen-select" id="SexoSeleccionado" name="SexoSeleccionado">
                    <option value="aaa">-</option>
<%--                    @foreach (var sexo in Model.SexosDisponibles)--%>
<%--                    {--%>
<%--                    <option @(Model.SexoSeleccionado == sexo.Id ? "selected" : "") value="@sexo.Id">@sexo.Nombre</option>--%>
<%--                    }--%>
<%--                </select>--%>
                </select>
            </div>


        <div class="col-xs-6" style="grid-auto-columns: auto">Hola pepito</div>
    </div>
</div>
</body>
</html>
<%--=======--%>
<%--<body>--%>
<%--<h2>${greeting}! it's working</h2>--%>
<%--<h3>All Rooms List:</h3>--%>
<%--<ul>--%>
<%--    <c:forEach var="room" items="${RoomList}">--%>
<%--        <li><c:out value="${room}" /></li>--%>
<%--    </c:forEach>--%>
<%--</ul>--%>
<%--<h3>You Selected Room:</h3>--%>
<%--<div>--%>
<%--    <c:out value="${RoomNumber}"></c:out>--%>
<%-->>>>>>> dc8873245c92335f7618badd8b951908f91afef9--%>
<%--</div>--%>
<%--</body>--%>


