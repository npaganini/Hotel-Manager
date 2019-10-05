<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>Welcome</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.2/css/bootstrap.min.css" integrity="sha384-y3tfxAZXuh4HwSYylfB+J125MxIs6mR5FOHamPBG064zB+AFeWH94NdvaCBm8qnd" crossorigin="anonymous">
    <script src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>

    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/slideBar.js"></script>

    <link href="http://cdn.datatables.net/1.10.19/css/jquery.dataTables.min.css" rel="stylesheet">
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.dataTables.min.js"></script>

    <link href="${pageContext.request.contextPath}/resources/CSS/my_style.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <div class="panel-title">
        <h1 class="text-xs-center modal-title row navbar-default">Welcome!</h1>
    </div>
        <table class="table text-xs-center">
            <thead class="thead-dark">
                <tr>
                    <th class="col-2 text-xs-center">Other Actions</th>
                    <th class="text-xs-center">History</th>
                </tr>
            </thead>
            <tbody class="container">
                <tr>
                    <td rowspan="${fn:length(ReservationsList) + 1}" class="col-2 text-xs-center">
                        <div class="container text-xs-center col-2 card-deck">
                            <div class="row"><a href="./products" class="btn btn-primary">Buy Products</a></div>
                            <div class="w-100" style="padding-bottom: 15px"></div>
                            <div class="row"><a href="./expenses" class="btn btn-primary">Check My Expenses</a></div>
                        </div>
                    </td>
                    <td class="text-xs-center">
                        <table class="container">
                            <thead class="thead-light modal-footer">
                                <th>Reservation</th>
                                <th>Start Date</th>
                                <th>End Date</th>
                            </thead>
                            <tbody>
                                <c:forEach var="reservation" items="${ReservationsList}">
                                    <td class="text-xs-left">${reservation.hash}</td>
                                    <td class="text-xs-left">${reservation.startDate}</td>
                                    <td class="text-xs-left">${reservation.endDate}</td>
                                </c:forEach>
                            </tbody>
                        </table>
                    </td>
                </tr>
            </tbody>
        </table>
</div>
</body>
</html>
