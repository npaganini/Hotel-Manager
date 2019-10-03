<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <title>Add Product Form</title>
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