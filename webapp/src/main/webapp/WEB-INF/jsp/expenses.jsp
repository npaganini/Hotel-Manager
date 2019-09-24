<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
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
            <th>Price</th>
        </tr>
        <c:forEach var="products" items="${ProductsList}">
            <tr>
                <td>${products.description}</td>
<%--                <td>${products.amount}</td>--%>
                <td>${products.price}</td>
                <c:set var="total" value="${total + products.price}" scope="page"/>
            </tr>
        </c:forEach>
        <tr>
            <td>Total:</td>
            <td>
                <fmt:formatNumber var="total" type="currency" value="${total}" />${total}
            </td>
        </tr>
    </table>
</div>
</body>
</html>
