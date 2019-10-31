<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>

<head>
    <title><spring:message code="logo"/></title>
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
                        <a class="navbar-brand" href="${pageContext.request.contextPath}/rooms/home"><spring:message code="logo"/></a>
                    </div>
                    <!-- Collect the nav links, forms, and other content for toggling -->
                    <div class="collapse navbar-collapse navbar-right" id="bs-sidebar-navbar-collapse-1">
                        <ul class="nav navbar-nav">
                            <li><a href="${pageContext.request.contextPath}/rooms/checkin"><spring:message code="reservation.checkin"/></a></li>
                            <li><a href="${pageContext.request.contextPath}/rooms/checkout"><spring:message code="reservation.checkout"/></a></li>
                            <li><a href="${pageContext.request.contextPath}/rooms/reservations"><spring:message code="reservation.plural"/></a></li>
                            <li><a href="${pageContext.request.contextPath}/products"><spring:message code="product.plural"/></a></li>
                            <li><a href="${pageContext.request.contextPath}/rooms/orders"><spring:message code="reservation.order.plural"/></a></li>
                            <li class="dropdown">
                                <a class="dropdown-toggle" data-toggle="dropdown" href="#"><span class="glyphicon glyphicon-user"></span> <spring:message code="user.account"/><span class="caret"></span></a>
                                <ul class="dropdown-menu">
                                    <li><a href="${pageContext.request.contextPath}/logout"><span class="glyphicon glyphicon-log-in"></span> <spring:message code="user.logout"/></a></li>
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
            <div><spring:message code="product.plural"/></div>
        </div>
    </div>
    <br>
    <br>
    <div class="row">
        <div class="col-xs-8 form-group" style="z-index:9999;grid-auto-columns: auto">
            <table id="myTable" class="display" style="width:100%;  border: 1px solid black !important;">
                <thead>
                <tr>
                    <th><spring:message code="product.singular"/></th>
                    <th><spring:message code="room.singular"/></th>
                    <th><spring:message code="room.state"/></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="order" items="${orders}">
                    <tr>

                        <td style="text-align: left">${order.product.description}</td>
                        <td style="text-align: left">${order.reservation.room.number}</td>

                        <c:if test="${order.delivered == false}">
                            <td style="text-align: left">
                                <button onclick="disableButtons()" id="finished" value="${order.id}" type="button"
                                        class="btn btn-default btn-lg">
                                    <div style="color: black"><a
                                            style="color: black"><spring:message code="send"/></a></div>
                                </button>
                            </td>
                        </c:if>

                        <c:if test="${order.delivered == true}">
                            <td style="text-align: left">
                                <div><spring:message code="done"/></div>
                            </td>
                        </c:if>

                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
        <br><br>
        <div class="col-xs-4">
            <div class="row" style="height: 45px;text-align: center">
                <div class="col-xs-2">
                    <button type="button" id="refresh" class="btn btn-success btn-lg"><a
                            href="${pageContext.request.contextPath}/rooms/orders" style="color: white"><spring:message code="refresh"/></a>
                    </button>
                </div>
            </div>
            <br><br>
            <div class="row">
                <div class="col-xs-2">
                    <button type="button" id="back" class="btn btn-danger btn-lg">
                        <a href="${pageContext.request.contextPath}/rooms/home" style="color: white"><spring:message code="user.home"/></a>
                    </button>
                </div>
            </div>
        </div>
    </div>

</div>
</div>
</body>
</html>
<script>
    $(document).ready(function () {
        $('#myTable').DataTable({
            "order": [[1, "asc"]],
            filter: false,
        });
    });

    $(document).ready(function () {

        $('#finished').off().on('click', function (event) {
            var basePath;

            var chargeId = $('#finished').val();

            basePath = "${pageContext.request.contextPath}" + "/rooms/orders/sendOrder?chargeId=" + chargeId;
            event.preventDefault();
            location.href = basePath;
            return false;

        })

    });


    function disableButtons() {
        document.getElementById("finished").disabled = true;
        document.getElementById("refresh").disabled = true;
        document.getElementById("back").disabled = true;

    }
</script>


