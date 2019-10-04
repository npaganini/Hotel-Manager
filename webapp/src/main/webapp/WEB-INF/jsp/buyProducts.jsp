<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:choose>
    <c:when test="${charge}">
        <div>Product Bought!</div>
        <div>Items will be delivered to your room soon!</div>
    </c:when>
    <c:otherwise>
        <div>There was an unexpected error! Please try again.</div>
    </c:otherwise>
</c:choose>
<span><a href="./products">Return to list of products</a></span>
<span><a href="">Look at all your expenses.</a></span>
</body>
</html>
