<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Check Your Expenses</title>
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
<h1 class="modal-title row navbar-default">&nbsp All Expenses List:</h1>
<div class="main container-fluid">
    <table class="table table-striped cont">
        <c:set var="total" value="0" scope="page"/>
        <thead class="thead-dark">
            <tr>
                <th scope="col">Product</th>
                <th scope="col">Amount</th>
                <th scope="col">Price</th>
            </tr>
        </thead>
        <tbody>
        <c:forEach var="products" items="${ProductsList}">
            <tr>
                <td>${products.key.description}</td>
                <td>x${products.value}</td>
                <td><fmt:formatNumber type="currency" maxFractionDigits="2" minFractionDigits="2" value="${products.key.price}"/></td>
                <c:set var="total" value="${total + products.key.price * products.value}" scope="page"/>
            </tr>
        </c:forEach>
        </tbody>
        <tfoot class="thead-light cont modal-footer">
        <tr>
            <td scope="col" class="text-left">Total:</td>
            <td scope="col"></td>
            <td scope="col" class="text-left">
                <fmt:formatNumber type="currency" maxFractionDigits="2" minFractionDigits="2" value="${total}"/>
            </td>
        </tr>
        </tfoot>
    </table>
    <div class="container row">
        <a href="${pageContext.request.contextPath}/user/home" class="btn btn-primary">Back To Home</a>
    </div>
</div>
</body>
</html>
