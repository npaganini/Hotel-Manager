<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>User</title>
</head>
<body>
<div class="main">
    <h3>All Products List:</h3>
    <table>
        <c:set var="total" value="0" scope="page"/>
        <tr>
            <th>Product</th>
            <th>Amount</th>
            <th>Price</th>
        </tr>
        <c:forEach var="products" items="${ProductsList}">
            <tr>
                <td>${products.key.description}</td>
                <td>x${products.value}</td>
                <td><fmt:formatNumber type="currency" maxFractionDigits="2" minFractionDigits="2" value="${products.key.price}"/></td>
                <c:set var="total" value="${total + products.key.price * products.value}" scope="page"/>
            </tr>
        </c:forEach>
        <tr>
            <td>Total:</td>
            <td>
                <fmt:formatNumber type="currency" maxFractionDigits="2" minFractionDigits="2" value="${total}"/>
            </td>
        </tr>
    </table>
</div>
</body>
</html>
