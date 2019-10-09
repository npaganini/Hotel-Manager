<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><spring:message code="user.help"/></title>
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
<div class="container-fluid">
    <div class="form-group" style="grid-auto-columns: auto">
        <nav class="container-fluid message">
            <span>
                <a href="${pageContext.request.contextPath}/logout" class="btn btn-primary user-logout align-self-end">
                    <spring:message code="user.logout"/>
                </a>
            </span>
        </nav>
        <div class="modal-title row navbar-default my-card-title message">
            <span class="user-navbar">
                <spring:message code="user.help"/>
            </span>
        </div>
        <div class="my-card-title">
            <h5 class="card-title text-xs-center message"></h5>
            <form:form modelAttribute="getHelpForm" action='${pageContext.request.contextPath}/user/helpUser?reservationId=${pageContext.request.getParameter("reservationId")}' method="post">
                <div class="col-alone container row">
                    <form:textarea path="text" cols="80" rows="5"/>
                </div>
                <div class="message col-alone container row">
                    <input class="btn btn-primary" type="submit" value="&nbsp;<spring:message code="user.help.submit"/>">
                </div>
            </form:form>
        </div>
    </div>
    <div class="col-alone container text-xs-left row message">
        <span class="col">
            <a href="${pageContext.request.contextPath}/user/home" class="btn btn-primary">
                <spring:message code="user.home"/>
            </a>
        </span>
    </div>
</div>
</body>
</html>
