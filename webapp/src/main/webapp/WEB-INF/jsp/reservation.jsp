<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    <c:url value="/rooms/reservationPost" var="postPath"/>
    <form:form modelAttribute="reservationForm" id="myForm" name="myForm" action="${postPath}" method="post">
    <div class="row myheader">
        <div class="col-xs-12 " style="text-align: left"><spring:message code="reservation.new"/></div>
    </div>
    <br>
    <br>
    <div class="row" style="height: 45px">
        <div class="col-xs-6">
            <div class="form-question">
                <div class="form-question__title">
                    <form:label class="items" path="startDate"><spring:message code="room.from"/>: </form:label>

                </div>

                <div class="input-container">
                    <form:input id="from_date" path="startDate" type="date" name="effective-date" minlength="1"
                                maxlength="64" placeholder=" " autocomplete="nope" required="required" />
                    <span class="bar"></span>
                </div>
            </div>
        </div>
        <div class="col-xs-6">
            <div class="form-question">
                <div class="form-question__title">
                    <form:label class="items" path="endDate"><spring:message code="room.until"/>: </form:label>
                </div>
                <div class="input-container">
                    <form:input id="to_date" path="endDate" type="date" name="effective-date" minlength="1"
                                maxlength="64" placeholder=" " autocomplete="nope" required="required" />
                    <span class="bar"></span>
                </div>
            </div>
        </div>
    </div>
    <br>
    <br>

    <div class="row">
        <div class="col" style="text-align: center">
            <button id="search" type="button" class="btn btn-success btn-lg" >
                <div style="color: white"><spring:message code="room.filter"/></div>
            </button>

        </div>
    </div>
    <br>
    <br>

    <div class="row" style="height: 45px">
        <div class="col-xs-6">
            <form:label class="items" path="roomId"><spring:message code="room.plural"/>: </form:label>
            <div id="room_number">
                <form:select path="roomId" id="selec" required="required">
                    <form:option value="0">-</form:option>
                    <c:forEach var="room" items="${allRooms}">
                        <form:option value="${room.id}"> ${room.number}</form:option>
                    </c:forEach>
                </form:select>
            </div>
        </div>
        <div class="col-xs-6">
            <form:label class="items" path="userEmail"><spring:message code="reservation.email"/>: </form:label>
            <div class="input-group">
                <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                <form:input id="email" path="userEmail" type="text" class="form-control" name="email"
                            placeholder="Email" required="required"/>
            </div>
        </div>
    </div>

    <div class="row" style="height: 45px">
        <br><br>
        <div class="col-xs-2">
            <input type="submit" id="reserv" class="btn btn-success btn-lg" value="<spring:message code="reserve"/>" />
        </div>
        <div class="col-xs-2">
            <button type="button" id="back" class="btn btn-danger btn-lg"><a href="${pageContext.request.contextPath}/rooms/home"
                                                                   style="color: white"><spring:message code="cancel"/></a>
            </button>
        </div>

    </div>
</div>
</div>
</form:form>
</body>
</html>

<script>



    $(document).ready(function () {

        $('#search').off().on('click', function (event) {
            var basePath;

            var startDate = $('#from_date').val();
            var endDate = $('#to_date').val();


            basePath = "${pageContext.request.contextPath}" + "/rooms/reservation?startDate=" + startDate + "&endDate=" + endDate;
            event.preventDefault();
            location.href = basePath;
            return false;

        })
    });

    $(document).ready(function () {

        $('#myForm').submit(function () {

            var x = document.forms["myForm"]["selec"].value;
            if (x == 0) {
                alert("Debe elegir una habitacion");

                //disable the submit button
                $("#reserv").attr("disabled", false);

                //disable a normal button
                $("#search").attr("disabled", false);

                $("#back").attr("disabled", false);
                return false;
            }
            else {

                //disable the submit button
                $("#reserv").attr("disabled", true);

                //disable a normal button
                $("#search").attr("disabled", true);

                $("#back").attr("disabled", true);

                return true;
            }

        });
    });


</script>
