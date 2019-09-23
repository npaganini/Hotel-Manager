<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>User</title>
</head>
<body>
<div class="col-xs-10 form-group" style="z-index:9999;grid-auto-columns: auto">
    <h3>All Products List:</h3>
    <table>
        <thead>
            <tr>
                <th>Product</th>
                <th>Price</th>
                <th>Amount</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="products" items="${ProductsList}">
                <tr>
                    <td>${products.description}</td>
                    <td>${products.price}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
