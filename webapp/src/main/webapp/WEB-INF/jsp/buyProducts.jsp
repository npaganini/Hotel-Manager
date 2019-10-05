<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<head>
    <title><spring:message code="user.product.bought"/></title>
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
<body>
<c:choose>
    <c:when test="${charge}">
        <h1 class="modal-title row navbar-default">
            &nbsp;<spring:message code="user.product.bought"/>
        </h1>
        <div class="container">
            <div class="message row"><spring:message code="user.product.itemsDelivered"/></div>
    </c:when>
    <c:otherwise>
            <div class="message row"><spring:message code="user.product.error"/></div>
    </c:otherwise>
</c:choose>
            <div class="row">
                <span class="col" style="padding-right: 25px;">
                    <a href="./products?reservationId=${pageContext.request.getParameter("reservationId")}" class="btn btn-primary">
                        <spring:message code="user.product.list.browse"/>
                    </a>
                </span>
                <span class="col">
                    <a href="./expenses?reservationId=${pageContext.request.getParameter("reservationId")}" class="btn btn-primary">
                        <spring:message code="user.product.list.expenses"/>
                    </a>
                </span>
            </div>
        </div>
</body>
</html>
