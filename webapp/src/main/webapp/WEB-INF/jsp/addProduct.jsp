<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
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
<body class="container cont" style="height: 100vh !important; width: 100vw !important;margin-left: 0 !important; margin-right: 0 !important">
<div>
    <div class="row">
        <div class="col">
            <nav class="navbar navbar-inverse sidebar" style="z-index: initial !important;" role="navigation">
                <div class="container-fluid">
                    <!-- Brand and toggle get grouped for better mobile display -->
                    <div class="navbar-header">
                        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-sidebar-navbar-collapse-1">
                            <span class="sr-only">Toggle navigation</span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                        </button>
                        <a class="navbar-brand" href="${pageContext.request.contextPath}/rooms/home">
                            <spring:message code="logo"/>
                        </a>
                    </div>
                    <!-- Collect the nav links, forms, and other content for toggling -->
                    <div class="collapse navbar-collapse navbar-right" id="bs-sidebar-navbar-collapse-1">
                        <ul class="nav navbar-nav">
                            <li>
                                <a href="${pageContext.request.contextPath}/rooms/registration">
                                    <spring:message code="registration"/>
                                </a>
                            </li>
                            <li>
                                <a href="${pageContext.request.contextPath}/rooms/checkin">
                                    <spring:message code="reservation.checkin"/>
                                </a>
                            </li>
                            <li>
                                <a href="${pageContext.request.contextPath}/rooms/checkout">
                                    <spring:message code="reservation.checkout"/>
                                </a>
                            </li>
                            <li>
                                <a href="${pageContext.request.contextPath}/rooms/reservations">
                                    <spring:message code="reservation.plural"/>
                                </a>
                            </li>
                            <li>
                                <a href="${pageContext.request.contextPath}/products">
                                    <spring:message code="product.plural"/>
                                </a>
                            </li>
                            <li>
                                <a href="${pageContext.request.contextPath}/rooms/orders">
                                    <spring:message code="reservation.order.plural"/>
                                </a>
                            </li>
                            <li>
                                <a href="${pageContext.request.contextPath}/helpList">
                                    <spring:message code="help.request.plural"/>
                                </a>
                            </li>
                            <li class="dropdown">
                                <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                                    <span class="glyphicon glyphicon-user"></span>
                                    <spring:message code="user.account"/>
                                    <span class="caret"></span>
                                </a>
                                <ul class="dropdown-menu">
                                    <li>
                                        <a href="${pageContext.request.contextPath}/logout">
                                            <span class="glyphicon glyphicon-log-in"></span>
                                            <spring:message code="user.logout"/>
                                        </a>
                                    </li>
                                </ul>
                            </li>
                        </ul>
                    </div>
                </div>
            </nav>
        </div>
    </div>
    <div class="row myheader vertical-align">
        <div class="col-xs-12" style="text-align: left">
            <div><spring:message code="product.add"/></div>
        </div>
    </div>
    <br>
    <br>
    <form:form modelAttribute="addProductForm" action='${pageContext.request.contextPath}/products/addProduct'
               method="post" enctype="multipart/form-data"
               id="myForm">
        <div class="row">
            <div class="col-xs-6">
                <label for="description"><spring:message code="product.description"/>: </label>
                <form:input id="description" path="description" required="required"/>
            </div>
            <div class="col-xs-6">
                <label for="price"><spring:message code="user.product.price"/>: </label>
                <form:input id="price" path="price" type="number" min="0" required="required"/>
            </div>
        </div>
        <br><br>
        <div class="row">
            <div class="col-xs-4">
                <label for="img"><spring:message code="product.img"/>: </label>
                <input type="file" name="img" required="required"/>
            </div>
            <div class="col-xs-8">
                <div class="col-xs-2">
                    <input id="submit" class="btn btn-success btn-lg" type="submit" tabindex="5" value="Add">
                </div>
                <div class="col-xs-2">
                    <input id="reset" type="reset" class="btn btn-primary btn-lg" tabindex="4">
                </div>
                <div class="col-xs-2">
                    <button type="button" onclick="location.href='${pageContext.request.contextPath}/products'" id="back"
                            class="btn btn-danger btn-lg">
                        <a style="color: white">
                            <spring:message code="back"/>
                        </a>
                    </button>
                </div>
            </div>
        </div>
    </form:form>
    <br>
</div>
</div>
</body>
</html>
<script>
    $(document).ready(function () {
        $('#myForm').submit(function () {
                //disable the submit button
                $("#submit").attr("disabled", true);
                //disable a normal button
                $("#reset").attr("disabled", true);
                $("#back").attr("disabled", true);
                return true;
        });
    });
</script>
