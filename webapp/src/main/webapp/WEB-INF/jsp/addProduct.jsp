<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
<div class="container cont"
     style="height: 100vh !important; width: 100vw !important;margin-left: 0 !important; margin-right: 0 !important">
    <div class="row">
        <div class="col">
            <nav class="navbar navbar-inverse sidebar" style="z-index: initial !important;" role="navigation">
                <div class="container-fluid">
                    <!-- Brand and toggle get grouped for better mobile display -->
                    <div class="navbar-header">
                        <button type="button" class="navbar-toggle" data-toggle="collapse"
                                data-target="#bs-sidebar-navbar-collapse-1">
                            <span class="sr-only">Toggle navigation</span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                        </button>
                        <a class="navbar-brand" href="${pageContext.request.contextPath}/rooms/home">e-lobby</a>
                    </div>
                    <!-- Collect the nav links, forms, and other content for toggling -->
                    <div class="collapse navbar-collapse" id="bs-sidebar-navbar-collapse-1">
                        <ul class="nav navbar-nav">
                            <li><a href="${pageContext.request.contextPath}/rooms/checkin">Check-In</a></li>
                            <li><a href="${pageContext.request.contextPath}/rooms/checkout">Check-Out</a></li>
                            <li><a href="${pageContext.request.contextPath}/rooms/reservations">Reservas</a></li>
                            <li><a href="${pageContext.request.contextPath}/home">Productos</a></li>
                        </ul>
                    </div>
                </div>
            </nav>
        </div>
    </div>

    <div class="row myheader vertical-align">
        <div class="col-xs-12" style="text-align: left">
            <div>Agregar producto!</div>
        </div>
    </div>
    <br>
    <br>
    <form:form modelAttribute="productForm" action="addProduct" method="post" enctype="multipart/form-data">
    <div class="row">
        <div class="col-xs-6">
            <label for="description">Description: </label>
            <form:input id="description" path="description" />
        </div>
        <div class="col-xs-6">
            <label for="price">Price: </label>
            <form:input id="price" path="price" type="number" />
        </div>
    </div>
        <br><br>
        <div class="row">
            <div class="col-xs-6">
                <label for="img">Product Image: </label>
                <input type="file" name="img" />
            </div>
            <div class="col-xs-6">
                <div class="col-xs-2">
                    <input id="reset" type="reset" tabindex="4">
                </div>
                <div class="col-xs-2">
                    <input id="submit" class="btn btn-success btn-lg" type="submit" tabindex="5" value="Add Product">
                </div>
                <div class="col-xs-2" style="margin-left: 25px">
                    <button type="button" class="btn btn-danger btn-lg"><a href="${pageContext.request.contextPath}/rooms/home" style="color: white">Volver</a></button>
                </div>
            </div>
        </div>
    </form:form>
    <br>
    </div>


</div>
</body>
</html>