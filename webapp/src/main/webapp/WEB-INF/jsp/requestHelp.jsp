<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><spring:message code="user.help.requested"/></title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.2/css/bootstrap.min.css" integrity="sha384-y3tfxAZXuh4HwSYylfB+J125MxIs6mR5FOHamPBG064zB+AFeWH94NdvaCBm8qnd" crossorigin="anonymous">
    <script src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>

    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/slideBar.js"></script>

    <link href="http://cdn.datatables.net/1.10.19/css/jquery.dataTables.min.css" rel="stylesheet">
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.dataTables.min.js"></script>

    <link href="${pageContext.request.contextPath}/resources/CSS/my_style.css" rel="stylesheet">

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
    <script src="https://code.jquery.com/jquery-3.3.1.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/slideBar.js"></script>
    <link href="${pageContext.request.contextPath}/resources/CSS/my_style.css" rel="stylesheet">
    <script src='https://kit.fontawesome.com/a076d05399.js'></script>
    <link rel="stylesheet" href="https://cdn.datatables.net/1.10.20/css/jquery.dataTables.min.css">
    <script src='https://cdn.datatables.net/1.10.20/js/jquery.dataTables.min.js'></script>
</head>
<body class="cont">
<div class="container-fluid message">
    <nav class="container-fluid message">
        <span>
            <a href="${pageContext.request.contextPath}/logout" class="btn btn-primary user-logout align-self-end">
                <spring:message code="user.logout"/>
            </a>
        </span>
    </nav>
    <div class="container">
    <c:choose>
        <c:when test="${fn:length(helpRequest) eq 0}">
            <div class="col-alone message row confirTable"><spring:message code="user.help.error"/></div>
        </c:when>
        <c:otherwise>
            <div class="modal-title row navbar-default my-card-title message">
                <div class="col-alone items text-xs-center"><spring:message code="user.help.requested"/></div>
                <div class="col-alone form-question__title"><spring:message code="user.help.yourMessage"/></div>
                <div class="col-alone user-navbar">${helpRequest}</div>
            </div>
        </c:otherwise>
    </c:choose>
        <div class="row message my-card-title">
            <span class="col">
                <a href="./home?reservationId=${pageContext.request.getParameter("reservationId")}" class="btn btn-primary">
                    <spring:message code="user.home"/>
                </a>
            </span>
        </div>
    </div>
</div>
</body>
</html>
