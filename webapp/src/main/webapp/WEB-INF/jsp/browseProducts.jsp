<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
    <title>User</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.2/css/bootstrap.min.css" integrity="sha384-y3tfxAZXuh4HwSYylfB+J125MxIs6mR5FOHamPBG064zB+AFeWH94NdvaCBm8qnd" crossorigin="anonymous">
    <script src="/resources/js/jquery.min.js"></script>
    <script src="/resources/js/bootstrap.min.js"></script>

    <script type="text/javascript" src="/resources/js/slideBar.js"></script>

    <link href="http://cdn.datatables.net/1.10.19/css/jquery.dataTables.min.css" rel="stylesheet">
    <script type="text/javascript" src="/resources/js/jquery.dataTables.min.js"></script>

    <link href="/resources/CSS/my_style.css" rel="stylesheet">
</head>
<body>
<div class="form-group" style="grid-auto-columns: auto">
    <h3>Products Ofrecidos:</h3>
    <c:set var="colCounter" value="0" scope="page"/>
    <form:form method="POST" action="buyProducts" modelAttribute="ProductForm">
        <c:forEach var="product" items="${ProductsList}">
            <c:if test="${colCounter%4 == 0}">
                <div class="row text-center">
            </c:if>
                <div class="col-sm-3">
                    <div class="card text-center">
                        <img src="https://via.placeholder.com/150" class="card-img-top">
                        <div class="card-body text-center">
                            <h5 class="card-title text-center">${product.description}</h5>
                            <p class="card-text price text-center">$${product.price}</p>
                            <c:set var="productID" value="${product.id}"/>
                            <input type="hidden" name="product" value="<c:out value='${productID}'/>"/>
                            <input class="btn btn-primary" type="submit" value="Comprar"/>
                        </div>
                    </div>
                </div>
                <c:set var="colCounter" value="${colCounter + 1}" scope="page"/>
            <c:if test="${colCounter%4 == 0}">
                </div>
            </c:if>
        </c:forEach>
    </form:form>
</div>
</body>
</html>
