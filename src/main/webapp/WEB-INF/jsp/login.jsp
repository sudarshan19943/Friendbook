<<<<<<< HEAD
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<style type="text/css">
    <%@include file="css/chat.css"%>
    <%@include file="css/bootstrap.min.css"%>
    <%@include file="css/bootstrap-formhelpers.min.css"%>
    <%@include file="css/style.css"%>
</style>
<script>
    <%@include file="js/jquery.min.js"%>
    <%@include file="js/bootstrap.min.js"%>
    <%@include file="js/bootstrap-formhelpers.min.js"%>
</script>

<fmt:setBundle basename="locale" var="loc"/>
<fmt:message bundle="${loc}" key="local.label.sign_in_continue" var="sign_in_continue"/>
<fmt:message bundle="${loc}" key="local.button.sign_in" var="sign_in"/>
<fmt:message bundle="${loc}" key="local.label.sign_up_here" var="sign_up_here"/>
<fmt:message bundle="${loc}" key="local.label.new_user" var="new_user"/>
<fmt:message bundle="${loc}" key="local.label.forgot_password" var="forgot_password"/>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>Login</title>
</head>
<body>
<div class="header">
    <h2>Friend Book</h2>
</div>
	<div class="container-fluid" style="margin-top:50px">
	<div class="row">
        <div class="col-sm-7">
            <h3>Connect with your friends around the world</h3>
            <img src="../../icons/promotional_image.jpg" alt="promotional image" border="2">
        </div>
		<div class="col-sm-5">
				<div class="panel panel-default">
					<div class="panel-heading">
						<strong>Please Login </strong>
					</div>
					<div class="panel-body">
						<form:form method="POST" modelAttribute="loginForm"
							class="form-signin">
							<div class="col-sm-12 col-md-10 col-md-offset-1 ">
							<h2 class="form-signin-heading">Sign in</h2>
							<spring:bind path="email">
								<div class="form-group ${status.error ? 'has-error' : ''}">
									<form:input type="text" path="email" class="form-control"
										placeholder="E-Mail" autofocus="true"></form:input>
									<form:errors path="email"></form:errors>
								</div>
							</spring:bind>
							<spring:bind path="password">
								<div class="form-group ${status.error ? 'has-error' : ''}">
									<form:input type="password" path="password"
										class="form-control" placeholder="Password"></form:input>
									<form:errors path="password"></form:errors>
								</div>
							</spring:bind>
							<button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button>
							</div>
							 <c:if test="${not empty errorMessage}">
                                <div class="alert alert-danger">
                                    <strong>
                                        ${errorMessage}
                                    </strong>
                                </div>
                                </c:if>
                                <c:if test="${not empty successMessage}">
                                <div class="alert alert-success">
                                    <strong>
                                        ${successMessage}
                                    </strong>
                                </div>
                                </c:if>
                                  <div class="form-signin"> 
                                    <a href="forgotpassword" onClick="forgotpassword.jsp"> ${forgot_password} </a>
				             </div>
						</form:form>
					</div>
					<div class="panel-footer">
                     <strong>${new_user}</strong> 
                     <p>
                     <a href="register" onClick="registration.jsp"> ${sign_up_here} </a> 
                </div>
				</div>
			</div>
		</div>
	</div>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<%-- <script src="${contextPath}/resources/js/bootstrap.min.js"></script> --%>
</body>
</html>
=======
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

<script type="text/javascript">
    function validateUser()
    {
        var email_pattern = /^\w+[\+\.\w-]*@([\w-]+\.)*\w+[\w-]*\.([a-z]{2,4}|\d+)$/i;
        var email_value=document.form.email.value;
        var email_value_1=email_pattern.test(document.form.email.value);
        var password_value=document.form.password.value;

        if(email_value=="")
        {
            alert("Please Enter Email ID");
            document.form.email.focus();
            return false;
        }
        if(email_value_1==false)
        {
            alert("Please Enter a valid Email ID");
            document.form.email.focus();
            return false;
        }
        if(password_value=="")
        {
            alert("Password don't match");
            document.form.password.focus();
            return false; 
        }
    }

</script>

<body>

<div class="header">
    <h2>Friend Book</h2>
</div>

<div class="container-fluid" style="margin-top:50px">
    <div class="row">
        <div class="col-sm-7">
            <h3>Connect with your friends around the world</h3>
            <img src="../../icons/promotional_image.jpg" alt="promotional image" border="2">
        </div>
        <div class="col-sm-5">
            
            <div class="panel panel-default">
                <div class="panel-heading">
                    <strong>${sign_in}</strong>
                </div>
                <div class="panel-body">
                    <form role="form" action="/login" name="form" method="post" autocomplete="off">
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
                                    <a href="forgotpassword" onClick=""> ${forgot_password} </a>
				             </div>
                        </div>
                    </form>
                     </div>
                 <div class="panel-footer">
                     <strong>${new_user}</strong> 
                     <p>
                     <a href="register" onClick=""> ${sign_up_here} </a> 
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
>>>>>>> dc3672108c40cee56e8314ee40fcd272868d1357
