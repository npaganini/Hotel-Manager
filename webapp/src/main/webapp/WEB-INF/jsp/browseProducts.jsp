<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title><spring:message code="user.product.offered"/></title>
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
<div class="">
    <nav class="container-fluid message">
        <span>
            <a href="${pageContext.request.contextPath}/logout" class="btn btn-primary user-logout align-self-end">
                <spring:message code="user.logout"/>
            </a>
        </span>
    </nav>
    <div class="form-group" style="grid-auto-columns: auto">
        <div class="modal-title row navbar-default my-card-title message">
            <span class="user-navbar">
                <spring:message code="user.product.offered"/>
            </span>
        </div>
        <div class="card-deck my-card-title">
            <c:forEach var="product" items="${ProductsList}">
            <div class="card product-card">
                <img class="card-img-top img-responsive" src='${pageContext.request.contextPath}/product/img?productId=${product.id}' alt="Card product image">
                <div class="card-body text-xs-center">
                    <h5 class="card-title text-xs-center message">${product.description}</h5>
                    <p class="card-text price text-xs-center"><fmt:formatNumber type="currency" maxFractionDigits="2" minFractionDigits="2" value="${product.price}"/></p>
                    <form:form modelAttribute="buyProductForm" action='${pageContext.request.contextPath}/user/buyProducts?reservationId=${pageContext.request.getParameter("reservationId")}' method="post">
                        <form:input type="hidden" name="productId" path="productId" value="${product.id}"/>
                        <input class="btn btn-primary" type="submit" value="<spring:message code="user.product.buy"/>">
                    </form:form>
                </div>
            </div>
            </c:forEach>
        </div>
    </div>
    <div class="container text-xs-left row message">
        <span class="col" style="padding-right: 25px;">
            <a href="${pageContext.request.contextPath}/user/home" class="btn btn-primary">
                <spring:message code="user.home"/>
            </a>
        </span>
        <span class="col">
            <a href="${pageContext.request.contextPath}/user/expenses?reservationId=${pageContext.request.getParameter("reservationId")}" class="btn btn-primary">
                <spring:message code="user.product.list.expenses"/>
            </a>
        </span>
    </div>
</div>
</body>
</html>
