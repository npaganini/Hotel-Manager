<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><spring:message code="user.help.list"/></title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
    <script src="https://code.jquery.com/jquery-3.3.1.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/slideBar.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/CSS/my_style.css">
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
        <div class="col-xs-6" style="text-align: left">
            <div><spring:message code="user.help.list"/></div>
        </div>
    </div>
    <br>
    <c:if test="${updated != null && updated == true}">
        <div class="alert alert-success message" style="size: 45px" role="alert">
            <strong>
                <spring:message code="help.status.update"/>
            </strong>
        </div>
    </c:if>
    <br>
    <div class="row">
        <div class="col-xs-8 form-group" style="z-index:9999;grid-auto-columns: auto">
            <table id="myTable" class="display" style="width:100%;  border: 1px solid black !important;">
                <thead class="my_th">
                    <tr>
                        <th><spring:message code="help.text.plural"/></th>
                        <th><spring:message code="reservation.room.number"/></th>
                        <th><spring:message code="help.status"/></th>
                        <th><spring:message code="help.status.mark"/></th>
                    </tr>
                </thead>
                <tbody>
                <c:forEach var="helpRequest" items="${helpList}">
                    <tr class="my_tr">
                        <td style="max-width: 256px">${helpRequest.helpText}</td>
                        <td>${helpRequest.reservation.room.number}</td>
                        <td>
                            <c:choose>
                                <c:when test="${helpRequest.helpStep == 'UNATTENDED'}">
                                    <spring:message code="help.status.mark.unattended"/>
                                </c:when>
                                <c:when test="${helpRequest.helpStep == 'REQUIRES_FURTHER_ACTION'}">
                                    <spring:message code="help.status.mark.further"/>
                                </c:when>
                                <c:otherwise>
                                    <spring:message code="help.status.mark.resolved"/>
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td class="text-xs-center">
                            <div style="text-align: right">
                                <form:form modelAttribute="getHelpForm" action='${pageContext.request.contextPath}/updateHelpStep' method="post">
                                <span class="text-xs-center">
                                    <form:input type="hidden" name="helpId" path="helpId" value="${helpRequest.id}"/>
                                    <form:select name="helpId" path="status">
                                        <c:if test="${helpRequest.helpStep == 'UNATTENDED'}">
                                            <form:option value="${'REQUIRES_FURTHER_ACTION'}">
                                                <spring:message code="help.status.mark.further"/>
                                            </form:option>
                                        </c:if>
                                        <form:option value="${'RESOLVED'}">
                                            <spring:message code="help.status.mark.resolved"/>
                                        </form:option>
                                    </form:select>
                                </span>
                                <span>&nbsp;</span>
                                <span>
                                    <input class="btn-lg" type="submit" value="<spring:message code="send"/>">
                                </span>
                                </form:form>
                            </div>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
        <br><br>
        <div class="col-xs-4">
            <div class="row" style="height: 45px;text-align: center">
                <div class="col-xs-2">
                    <button type="button" onclick="location.href='${pageContext.request.contextPath}/helpList'" id="refresh" class="btn btn-success btn-lg">
                        <a style="color: white"><spring:message code="refresh"/></a>
                    </button>
                </div>
            </div>
            <br><br>
            <div class="row">
                <div class="col-xs-2">
                    <button type="button" onclick="location.href='${pageContext.request.contextPath}/rooms/home'" id="back" class="btn btn-default btn-lg">
                        <a style="color: black"><spring:message code="user.home"/></a>
                    </button>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
<script>
    $(document).ready(function () {
        $('#myTable').DataTable({
            "order": [[1, "asc"]],
            filter: false
        });
    });
</script>
