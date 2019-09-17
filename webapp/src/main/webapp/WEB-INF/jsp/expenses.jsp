<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<html>
<head>
    <title>User</title>
</head>
<body>
<div class="main">
    <!-- Content Here -->
    <h3>All Expenses List:</h3>
    <ul>
<%--        <c:forEach var="services" items="${ToursList}">--%>
<%--            <li><c:out value="${services}" /></li>--%>
<%--        </c:forEach>--%>
        <c:forEach var="products" items="${ProductsList}">
            <li><c:out value="${products.description}" /></li>
        </c:forEach>
    </ul>
</div>
</body>
</html>
