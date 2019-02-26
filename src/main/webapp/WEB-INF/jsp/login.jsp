<%--@elvariable id="errorMessage" type="String"--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<link rel="stylesheet" type="text/css"
	href="webjars/bootstrap/3.3.7/css/bootstrap.min.css" />

<style type="text/css">
    <%@include file="css/bootstrap.min.css"%>
    <%@include file="css/bootstrap-formhelpers.min.css"%>
    <%@include file="css/style.css"%>
</style>
<script>
    <%@include file="js/jquery.min.js"%>
    <%@include file="js/bootstrap.min.js"%>
    <%@include file="js/bootstrap-formhelpers.min.js"%>
</script>

<html>
<head>
    <title>Login</title>
    <meta charset="utf-8" name="viewport" content="width=device-width, initial-scale=1">
</head>

<fmt:setBundle basename="locale" var="loc"/>
<fmt:message bundle="${loc}" key="local.label.sign_in_continue" var="sign_in_continue"/>
<fmt:message bundle="${loc}" key="local.button.sign_in" var="sign_in"/>
<fmt:message bundle="${loc}" key="local.label.sign_up_here" var="sign_up_here"/>
<fmt:message bundle="${loc}" key="local.label.new_user" var="new_user"/>
<fmt:message bundle="${loc}" key="local.label.forgot_password" var="forgot_password"/>

<body>

<div class="header">
    <h2>Friend Book</h2>
</div>

<div class="container-fluid" style="margin-top:50px">
    <div class="row">
        <div class="col-sm-6 order-sm-1">
            <h2>Connect with your friends around the world</h2>
        </div>
        <div class="col-sm-6 col-md-4 col-md-offset-6">
            
            <div class="panel panel-default">
                <div class="panel-heading">
                    <strong>${sign_in}</strong>
                </div>
                <div class="panel-body">
                    <form role="form" action="/login" method="post" autocomplete="off">
                        <div class="col-sm-12 col-md-10 col-md-offset-1 ">
                            <div class="form-group">
                                    <input class="form-control" placeholder='${email}' value='${param.email}' name="email" type="text" autofocus required>
                                
                            </div>
                            <div class="form-group">
                                    <input class="form-control" placeholder='${password}' name="password" type="password" value="" autocomplete="off" required maxlength="255">
                            </div>
	                            <c:if test="${not empty errorMessage}">
                                <div class="alert alert-danger">
                                    <strong>
                                        ${errorMessage}
                                    </strong>
                                </div>
                                </c:if>
                            <div class="form-group">
                                <input type="submit" class="btn btn-lg btn-primary btn-block" value="${sign_in}">
                            </div>
                            <div class="form-group"> 
                                    <a href="forgotpassword.jsp" onClick=""> ${forgot_password} </a>
				             </div>
                        </div>
                    </form>
                     </div>
                 <div class="panel-footer">
                     <strong>${new_user}</strong> 
                     <p>
                     <a href="registration.jsp" onClick=""> ${sign_up_here} </a> 
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>