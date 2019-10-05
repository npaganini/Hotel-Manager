<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Browse Our Products</title>
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
    <div class="form-group" style="grid-auto-columns: auto">
        <div class="modal-title row navbar-default"><h1>Products Ofrecidos:</h1></div>
        <div class="card-deck">
            <c:forEach var="product" items="${ProductsList}">
            <div class="card">
                <img class="card-img-top img-responsive" src="https://via.placeholder.com/150" alt="Card product image">
                <div class="card-body container text-xs-center">
                    <h5 class="card-title text-xs-center">${product.description}</h5>
                    <p class="card-text price text-xs-center">$<fmt:formatNumber type="number" maxFractionDigits="2" minFractionDigits="2" value="${product.price}"/></p>
                    <form:form modelAttribute="productForm" action='buyProducts?reservationId=${pageContext.request.getParameter("reservationId")}' method="post">
                        <form:input type="hidden" path="productId" value="${product.id}"/>
                        <input class="btn btn-primary" type="submit" value="Comprar"/>
                    </form:form>
                </div>
            </div>
            </c:forEach>
        </div>
    </div>
    <div class="container text-center row">
        <span class="col" style="padding-right: 25px;"><a href="./user/home" class="btn btn-primary">Back To Home</a></span>
        <span class="col"><a href="./expenses?reservationId=${pageContext.request.getParameter("reservationId")}" class="btn btn-primary">Check My Expenses</a></span>
    </div>
</div>
</body>
</html>
