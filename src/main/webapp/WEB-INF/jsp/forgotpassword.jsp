<%--@elvariable id="errorMessage" type="String"--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<link rel="stylesheet" type="text/css"
	href="webjars/bootstrap/3.3.7/css/bootstrap.min.css" />

<style type="text/css">
    <%@include file="css/bootstrap.min.css"%>
    <%@include file="css/bootstrap-formhelpers.min.css"%>
</style>
<script>
    <%@include file="js/jquery.min.js"%>
    <%@include file="js/bootstrap.min.js"%>
    <%@include file="js/bootstrap-formhelpers.min.js"%>
</script>

<html>
<head>
    <title>Forgot password</title>
    <meta charset="utf-8" name="viewport" content="width=device-width, initial-scale=1">
</head>

<fmt:setBundle basename="locale" var="loc"/>
<fmt:message bundle="${loc}" key="local.label.forgot_password" var="forgot_password"/>

<body>
<h2 align = "center">Instructions:</h2>
<h4 align="center">Please enter the email address associated with your Friendbook account. A link containing on how to reset your password will be emailed to you.</h4>
<div class="container" style="margin-top:40px">
    <div class="row">
        <div class="col-sm-6 col-md-4 col-md-offset-4">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <strong>${forgot_password}</strong>
                </div>
                <div class="panel-body">
                    <form role="form" action="/login" method="post">
                        <div class="form-group"> 
                                <p>Email Address:</p>
                            	<input class="form-control" placeholder='${email}' value='${param.email}' name="email" type="text"> 
                            </div>
                            <div class="form-group">
                                <input type="submit" class="btn btn-lg btn-primary btn-block" value=${forgot_password} />
                            </div>
                    </form>
                </div>
            </div>
        </div>
      </div>
   </div>
</body>
</html>