<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title><spring:message code="user.landing"/></title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.2/css/bootstrap.min.css" integrity="sha384-y3tfxAZXuh4HwSYylfB+J125MxIs6mR5FOHamPBG064zB+AFeWH94NdvaCBm8qnd" crossorigin="anonymous">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.2/css/bootstrap.min.css"
          integrity="sha384-y3tfxAZXuh4HwSYylfB+J125MxIs6mR5FOHamPBG064zB+AFeWH94NdvaCBm8qnd" crossorigin="anonymous">
    <script src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/slideBar.js"></script>

    <link href="http://cdn.datatables.net/1.10.19/css/jquery.dataTables.min.css" rel="stylesheet">
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/resources/js/jquery.dataTables.min.js"></script>

    <link href="${pageContext.request.contextPath}/resources/CSS/my_style.css" rel="stylesheet">
</head>
<body class="cont">
<div class="container-fluid">
    <div class="panel-title">
        <div class="modal-title row navbar-default my-card-title message">
            <span class="user-navbar text-xs-center text-center">
                &nbsp<spring:message code="user.reservation.currents"/>
            </span>
            <span>
                <a href="${pageContext.request.contextPath}/logout" class="btn btn-primary user-logout align-self-end vertical-center">
                    <spring:message code="user.logout"/>
                </a>
            </span>
        </div>
    </div>
    <table class="table text-xs-center">
        <thead class="thead-dark">
        </thead>
        <tbody class="container-fluid text-xs-center">
        <tr>
            </td>
            <td class="text-xs-center">
                <table class="container few-items-table">
                    <thead class="thead-light modal-footer">
                        <tr>
                            <th class="text-xs-center align-middle">
                                <spring:message code="reservation.room.type"/>
                            </th>
                            <th class="text-xs-center align-middle">
                                <spring:message code="reservation.date.start"/>
                            </th>
                            <th class="text-xs-center align-middle">
                                <spring:message code="reservation.date.end"/>
                            </th>
                            <th class="text-xs-center align-middle">
                                <spring:message code="reservation.room.number"/>
                            </th>
                            <th class="col-alone text-xs-center align-middle">
                                <spring:message code="user.landing.other"/>
                            </th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:if test="${fn:length(ReservationsList) != 0}">
                            <c:forEach var="resRoomDTO" items="${ReservationsList}">
                                <td class="text-xs-center align-middle">${resRoomDTO.room.roomType}</td>
                                <td class="text-xs-center align-middle">${resRoomDTO.reservation.startDate}</td>
                                <td class="text-xs-center align-middle">${resRoomDTO.reservation.endDate}</td>
                                <td class="text-xs-center align-middle">${resRoomDTO.room.number}</td>
                                <td class="col-alone align-middle" style="inline-size: auto">
                                    <div class="container text-xs-center col-2 card-deck">
                                        <div class="row">
                                          <a href="./products?reservationId=${resRoomDTO.reservation.id}" class="btn btn-primary btn-block action-btn">
                                            <spring:message code="user.product.list.buy"/>
                                          </a>
                                      </div>
                                        <div class="w-100" style="padding-bottom: 15px"></div>
                                        <div class="row" onclick="">
                                          <a href="./expenses?reservationId=${resRoomDTO.reservation.id}" class="btn btn-primary btn-block action-btn">
                                            <spring:message code="user.product.expenses"/>
                                          </a>
                                      </div>
                                    </div>
                                </td>
                            </c:forEach>
                        </c:if>
                    </tbody>
                </table>
                <c:if test="${fn:length(ReservationsList) == 0}">
                    <div class="message no-reservs text-xs-center">
                        <p><spring:message code="user.reservation.currents.none"/></p>
                    </div>
                </c:if>
            </td>
        </tr>
        </tbody>
    </table>
</div>
</body>
</html>
