<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>

<head>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.3.1.js"></script>
<script src="/resources/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/resources/js/slideBar.js"></script>
<link href="/resources/CSS/my_style.css" rel="stylesheet">
<script src='https://kit.fontawesome.com/a076d05399.js'></script>
<link rel="stylesheet" href="https://cdn.datatables.net/1.10.20/css/jquery.dataTables.min.css">
<script src='https://cdn.datatables.net/1.10.20/js/jquery.dataTables.min.js'></script>

</head>
<body >
<div class="container cont" style="height: 100vh !important; width: 100vw !important;margin-left: 0 !important; margin-right: 0 !important" >
<div class="row">
    <div class="col">
    <nav class="navbar navbar-inverse sidebar" style="z-index: initial !important;" role="navigation">
    <div class="container-fluid">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-sidebar-navbar-collapse-1">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="/">e-lobby</a>
        </div>
        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-sidebar-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li ><a href="#">Chrck-In</a></li>
                <li ><a href="#">Check-Out</a></li>
<%--                <li class="dropdown">--%>
<%--                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">Reservas<span class="caret"></span></a>--%>
<%--                    <ul class="dropdown-menu forAnimate" role="menu">--%>
<%--                        <li><a href="#">Action</a></li>--%>
<%--                        <li><a href="#">Another action</a></li>--%>
<%--                        <li><a href="#">Something else here</a></li>--%>
<%--                        <li class="divider"></li>--%>
<%--                        <li><a href="#">Separated link</a></li>--%>
<%--                        <li class="divider"></li>--%>
<%--                        <li><a href="#">One more separated link</a></li>--%>
<%--                    </ul>--%>
<%--                </li>--%>
                <li><a href="/rooms/reservations">Reservas</a></li>
                <li><a href="/">Productos</a></li>
            </ul>
        </div>
    </div>
</nav>
</div>
</div>
    <div class="row myheader">
        <div class="col-xs-6 " style="text-align: left" >Habitaciones Ocupadas</div>
        <div class="col-xs-6" style="text-align: right">
            <button type="button" class="btn btn-danger btn-lg" style="height: 35px !important;" ><a href="/rooms/reservation" style="color: white">Nueva Reserva</a> </button>

        </div>
    </div>
        <br>
        <br>
        <div class="row">
        <div class="col-xs-12 form-group" style="z-index:9999;grid-auto-columns: auto">
        <table id="myTable" class="display" style="width:100%" >
            <thead>
            <tr>
                <th>ID</th>
                <th>Numero</th>
                <th>Tipo</th>
                <th>Ocupada</th>
                <th>Desde</th>
                <th>Hasta</th>
             </tr>
            </thead>
            <tbody>
            <c:forEach var="room" items="${RoomList}">
             <tr>

            <td style="text-align: left">${room.id}</td>
            <td style="text-align: left">${room.number}</td>
            <td>${room.roomType}</td>
           <c:if test="${room.freeNow == true}">
                <td style="text-align: left">Si</td>
                <td style="text-align: left"></td>
               <td style="text-align: left"></td>

           </c:if>
            <c:if test="${room.freeNow == false}">
                <td style="text-align: left">No</td>
                <td style="text-align: left">-</td>
                <td style="text-align: left">-</td>

            </c:if>


             </tr>
            </c:forEach>
            </tbody>
         </table>
        </div>
        </div>

    </div>
</div>
</body>

<script>
$(document).ready( function () {
$('#myTable').DataTable({
    "info" : false,
    "search" : false
});
} );
</script>

</html>
