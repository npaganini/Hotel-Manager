<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title><spring:message code="logo"/></title>
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
                        <button type="button" class="navbar-toggle" data-toggle="collapse"
                                data-target="#bs-sidebar-navbar-collapse-1">
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
                                    &nbsp;<spring:message code="user.account"/>
                                    <span class="caret"></span>
                                </a>
                                <ul class="dropdown-menu">
                                    <li>
                                        <a href="${pageContext.request.contextPath}/logout">
                                            <span class="glyphicon glyphicon-log-in"></span>
                                            &nbsp;<spring:message code="user.logout"/>
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
    <form:form modelAttribute="registrationForm" action="${pageContext.request.contextPath}/rooms/registrationPost" method="post">
        <div class="row myheader vertical-align">
            <div class="col-xs-6" style="text-align: left">
                <div><spring:message code="registration"/></div>
            </div>
        </div>
        <br>
        <c:if test="${registered != null && registered == true}">
            <div class="alert alert-success message" style="size: 45px" role="alert">
                <strong>
                        <spring:message code="registered"/>!
                </strong>
            </div>
        </c:if>
        <br>
        <div class="row">
            <div class="col-xs-6">
                <label class="items"><spring:message code="reservation.id"/>: </label>
                <div class="input-group">
                    <span class="input-group-addon"></span>
                    <form:input id="reservation_hash" path="reservation_hash" type="text" class="form-control" name="reservation_hash" required="required"/>
                </div>
            </div>
            <br>
            <div>
                <div class="col-xs-3" style="text-align:center">
                    <input id="regist" type="submit" class="btn btn-success btn-lg" value="<spring:message code="register"/>"/>
                </div>
                <div class="col-xs-3">
                    <button type="button" onclick="location.href='${pageContext.request.contextPath}/rooms/home'"
                            class="btn btn-default btn-lg"><a
                            style="color: black"><spring:message code="cancel"/></a>
                    </button>
                </div>
            </div>
        </div>
        <br>
        <br>
        <div class="row">
            <div class="col-xs-6">
                <label class="items"><spring:message code="firstname"/>: </label>
                <div class="input-group">
                    <span class="input-group-addon"></span>
                    <form:input path="name_1" type="text" class="form-control" name="name_1" required="required"/>
                </div>
            </div>
            <div class="col-xs-6">
                <label class="items"><spring:message code="lastname"/>: </label>
                <div class="input-group">
                    <span class="input-group-addon"></span>
                    <form:input path="last_name_1" type="text" class="form-control" name="last_name_1" required="required"/>
                </div>
            </div>
        </div>
        <br>
        <div class="row">
            <div class="col-xs-6">
                <label class="items"><spring:message code="firstname"/>: </label>
                <div class="input-group">
                    <span class="input-group-addon"></span>
                    <form:input path="name_2" type="text" class="form-control" name="name_2" />
                </div>
            </div>
            <div class="col-xs-6">
                <label class="items"><spring:message code="lastname"/>: </label>
                <div class="input-group">
                    <span class="input-group-addon"></span>
                    <form:input path="last_name_2" type="text" class="form-control" name="last_name_2"/>
                </div>
            </div>
        </div>
        <br>
        <div class="row">
            <div class="col-xs-6">
                <label class="items"><spring:message code="firstname"/>: </label>
                <div class="input-group">
                    <span class="input-group-addon"></span>
                    <form:input path="name_3" type="text" class="form-control" name="name_3"/>
                </div>
            </div>
            <div class="col-xs-6">
                <label class="items"><spring:message code="lastname"/>: </label>
                <div class="input-group">
                    <span class="input-group-addon"></span>
                    <form:input path="last_name_3" type="text" class="form-control" name="last_name_3"/>
                </div>
            </div>
        </div>
        <br>
        <div class="row">
            <div class="col-xs-6">
                <label class="items"><spring:message code="firstname"/>: </label>
                <div class="input-group">
                    <span class="input-group-addon"></span>
                    <form:input path="name_4" type="text" class="form-control" name="name_4"/>
                </div>
            </div>
            <div class="col-xs-6">
                <label class="items"><spring:message code="lastname"/>: </label>
                <div class="input-group">
                    <span class="input-group-addon"></span>
                    <form:input path="last_name_4" type="text" class="form-control" name="last_name_4"/>
                </div>
            </div>
        </div>
        <br>
        <div class="row">
            <div class="col-xs-6">
                <label class="items"><spring:message code="firstname"/>: </label>
                <div class="input-group">
                    <span class="input-group-addon"></span>
                    <form:input path="name_5" type="text" class="form-control" name="name_5"/>
                </div>
            </div>
            <div class="col-xs-6">
                <label class="items"><spring:message code="lastname"/>: </label>
                <div class="input-group">
                    <span class="input-group-addon"></span>
                    <form:input path="last_name_5" type="text" class="form-control" name="last_name_5"/>
                </div>
            </div>
        </div>
        <br>
        <div class="row">
            <div class="col-xs-6">
                <label class="items"><spring:message code="firstname"/>: </label>
                <div class="input-group">
                    <span class="input-group-addon"></span>
                    <form:input path="name_6" type="text" class="form-control" name="name_6"/>
                </div>
            </div>
            <div class="col-xs-6">
                <label class="items"><spring:message code="lastname"/>: </label>
                <div class="input-group">
                    <span class="input-group-addon"></span>
                    <form:input path="last_name_6" type="text" class="form-control" name="last_name_6"/>
                </div>
            </div>
        </div>

    </form:form>
</div>
</body>
</html>

