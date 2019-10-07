<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <title>e-lobby</title>
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
<div id="global">
    <form:form modelAttribute="productForm" action="addProduct" method="post" enctype="multipart/form-data">
        <fieldset>
            <legend>Add a product</legend>
            <p>
                <label for="description">Description: </label>
                <form:input id="description" path="description" />
            </p>
            <p>
                <label for="price">Price: </label>
                <form:input id="price" path="price" type="number" />
            </p>
            <p>
                <label for="img">Product Image: </label>
                <input type="file" name="img" />
            </p>
            <p id="buttons">
                <input id="reset" type="reset" tabindex="4">
                <input id="submit" type="submit" tabindex="5" value="Add Product">
            </p>
        </fieldset>
    </form:form>
</div>
</body>
</html>