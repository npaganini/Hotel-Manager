<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
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
<body class="container cont"
      style="height: 100vh !important; width: 100vw !important;margin-left: 0 !important; margin-right: 0 !important">
<div >
    <div class="row myheader vertical-align">
        <div class="col-xs-12" style="text-align: left">
            <div><spring:message code="error"/></div>
        </div>
    </div>
    <br>
    <br>
    <div class="row" style="font-size:x-large ">
        <div class="col" style="margin-left: 25px">
            <spring:message code="error.404"/>
        </div>
    </div>
    <br>
    <div class="row">
        <div class="col" style="margin-left: 25px">
            <button type="button" onclick="location.href='${pageContext.request.contextPath}/logout'" class="btn btn-success btn-lg"><a  style="color: white"><spring:message code="user.home"/></a></button>
        </div>
    </div>


</div>
</body>
</html>