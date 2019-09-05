<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<html>

<head>

<%--    Slidebar--%>
<link href="/resources/CSS/slideBar.css" rel="stylesheet">
<link href="http://netdna.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="${pageContext.request.contextPath}/WEB-INF/bootstrap/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/WEB-INF/bootstrap/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="/resources/js/slideBar.js"></script>

<%--    Table--%>
    <link href="http://cdn.datatables.net/1.10.19/css/jquery.dataTables.min.css" rel="stylesheet">
    <script type="text/javascript" src="/resources/js/jquery.dataTables.min.js"></script>
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
            Habitaciones
        </div>
        <br><br>
        <div class="col-xs-6 form-group" style="z-index:9999;grid-auto-columns: auto">
<%--                <label  class="col-form-label">Habitacion</label>--%>
<%--                <select class="chosen-select" id="HabSeleccionada" name="HabSeleccionada">--%>
<%--                    <option value="-">-</option>--%>
        <table id="myTable" >
            <thead>
            <tr>
                <th>ID</th>
                 <th>Numero</th>
                <th>Ocupada</th>
             </tr>
            </thead>
            <tbody>
            <c:forEach var="room" items="${RoomList}">
             <tr>

            <td>${room.id}</td>
            <td>${room.number}</td>
           <c:if test="${room.free == false}">
            <td>No</td>
           </c:if>
            <c:if test="${room.free != false}">
                <td>Si</td>
            </c:if>

             </tr>
            </c:forEach>
            </tbody>
         </table>
<%--                    <c:forEach var="room" items="${RoomList}">--%>
<%--                        <option value="${room.id}">${room.number}</option>--%>
<%--                    </c:forEach>--%>
<%--                </select>--%>
        </div>

    </div>
</div>
</body>

<script>$(document).ready( function () {
$('#myTable').DataTable();
} );
</script>
</html>
