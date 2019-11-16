<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>

<head>
    <title>e-lobby</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
    <script src="https://code.jquery.com/jquery-3.3.1.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/slideBar.js"></script>
    <link href="${pageContext.request.contextPath}/resources/CSS/my_style.css" rel="stylesheet">
    <script src='https://kit.fontawesome.com/a076d05399.js'></script>
    <link rel="stylesheet" href="https://cdn.datatables.net/1.10.20/css/jquery.dataTables.min.css">
    <script src='https://cdn.datatables.net/1.10.20/js/jquery.dataTables.min.js'></script>

</head>
<body class="container cont"
      style="height: 100vh !important; width: 100vw !important;margin-left: 0 !important; margin-right: 0 !important">
<div>
    <div class="row">
        <div class="col">
            <nav class="navbar navbar-inverse sidebar" style="z-index: initial !important;" role="navigation">
                <div class="container-fluid">
                    <!-- Brand and toggle get grouped for better mobile display -->
                    <div class="navbar-header">
                        <button type="button" class="navbar-toggle" data-toggle="collapse"
                                data-target="#bs-sidebar-navbar-collapse-1">
                            <span class="sr-only">Toggle navigation</span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                        </button>
                        <a class="navbar-brand" href="${pageContext.request.contextPath}/user/home">e-lobby</a>
                    </div>
                    <!-- Collect the nav links, forms, and other content for toggling -->
                    <div class="collapse navbar-collapse navbar-right" id="bs-sidebar-navbar-collapse-1">
                        <ul class="nav navbar-nav">
                            <li class="dropdown">
                                <a class="dropdown-toggle" data-toggle="dropdown" href="#"><span
                                        class="glyphicon glyphicon-user"></span> <spring:message
                                        code="user.account"/><span class="caret"></span></a>
                                <ul class="dropdown-menu">
                                    <li><a href="${pageContext.request.contextPath}/logout"><span
                                            class="glyphicon glyphicon-log-in"></span> <spring:message
                                            code="user.logout"/></a></li>
                                </ul>
                            </li>
                        </ul>
                    </div>
                </div>
            </nav>
        </div>
    </div>
    <div class="row myheader vertical-align">
        <div class="col-xs-6" style="text-align: left">
            <div><spring:message code="user.reservation.currents"/></div>
        </div>

    </div>

    <br>
    <br>
    <div class="row">
        <div class="col-xs-12 form-group" style="z-index:9999;grid-auto-columns: auto">
            <table id="myTable" class="display" style="width:100%;  border: 1px solid black !important;">
                <thead>
                <tr>
                    <th><spring:message code="reservation.room.type"/></th>
                    <th><spring:message code="reservation.date.start"/></th>
                    <th><spring:message code="reservation.date.end"/></th>
                    <th><spring:message code="reservation.room.number"/></th>
                    <th>Actions</th>
                    <th>Expenses</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="reservation" items="${ReservationsList}">
                    <tr>
                        <td>${reservation.room.roomType}</td>
                        <td><fmt:formatDate value="${reservation.startDate.time}" pattern="yyyy-MM-dd" /></td>
                        <td><fmt:formatDate value="${reservation.endDate.time}" pattern="yyyy-MM-dd" /></td>
                        <td>${reservation.room.number}</td>
                        <td style="text-align: left">
                            <button id="disable" type="button"
                                    class="btn btn-primary" onclick="location.href='./products?reservationId=${reservation.id}'">
                                <div style="color: white"><a

                                        style="color: white"><spring:message code="user.product.list.buy"/></a></div>
                            </button>
                        </td>
                        <td style="text-align: left">
                            <button type="button"
                                    class="btn btn-primary" onclick="location.href='./expenses?reservationId=${reservation.id}'">
                                <div style="color: white"><a

                                        style="color: white"><spring:message code="user.product.expenses"/></a></div>
                            </button>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>
</body>
</html>


<script>
    $(document).ready(function () {
        $('#myTable').DataTable({
            filter: false,
        });
    });
</script>