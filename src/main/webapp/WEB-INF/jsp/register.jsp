<%--@elvariable id="errorMessage" type="String"--%>
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
    <%@include file="js/avatar.js"%>
    <%@include file="js/bootstrap.min.js"%>
    <%@include file="js/bootstrap-formhelpers.min.js"%>
</script>

<html>
<head>
    <title>Registration</title>
    <meta charset="utf-8" name="viewport" content="width=device-width, initial-scale=1">
</head>

<fmt:setBundle basename="locale" var="loc"/>
<fmt:message bundle="${loc}" key="local.label.sign_up_here" var="sign_up_here"/>
<script type="text/javascript">
    

function validateUser(){

    var email_pattern = /^\w+[\+\.\w-]*@([\w-]+\.)*\w+[\w-]*\.([a-z]{2,4}|\d+)$/i;
    var email_value=document.form.email.value;
    var email_value_1=email_pattern.test(document.form.email.value);
    var first_name_value=document.form.first_name.value;
    var last_name_value=document.form.last_name.value;
    var password_value=document.form.password.value;
    var confirm_password_value=document.form.confirm_password.value;


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
    if(first_name_value=="")
    {
        alert("First Name can't be empty");
        document.form.first_name.focus();
        return false;
    }
    if(last_name_value=="")
    {
        alert("Last Name can't be empty");
        document.form.last_name.focus();
        return false;
    }
    if(password_value=="")
    {
        alert("Password can't be empty");
        document.form.password.focus();
        return false; 
    }
    if(password_value.length<=5 && password_value>=10)
    {
        alert("Password must be between 5 to 10 characters");
        document.form.password.focus();
        return false; 
    }
    if(confirm_password_value=="")
    {
        alert("Please enter password again!!!");
        document.form.confirm_password.focus();
        return false; 
    }
    if(password_value!=confirm_password_value)
    {
        alert("Passwords don't match, Please try again!!!");
        document.form.confirm_password.focus();
        return false; 
    }
}



</script>


<body>

<div class="header">
    <h2>Friend Book</h2>
</div>

<div class="container" style="margin-top:40px">
    <div class="row centered-form">
        <div class="col-xs-12 col-sm-8 col-md-4 col-sm-offset-2 col-md-offset-4">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <strong>Please sign up </strong>
                </div>
                <div class="panel-body">
                    <form role="form" action="/register" method="post" autocomplete="off" name="form">
                        <div class="form-group">
                            <input type="email" name="email" id="email" class="form-control" placeholder="E-Mail" required maxlength="255" value='${param.email}'>
                        </div>
                        
                            
                                <div class="form-group">
                                    <input type="text"  value='${param.first_name}' name="first_name" id="first_name" class="form-control" placeholder="First name" required maxlength="255">
                                </div>
                          
                                <div class="form-group">
                                    <input type="text" value='${param.last_name}' name="last_name" id="last_name" class="form-control" placeholder="Last name" required maxlength="255" >
                                </div>
                           
                        
                        
                            
                                <div class="form-group">
                                    <input type="password" name="password" id="password" class="form-control" required maxlength="255" placeholder="Password">
                                </div>
                            
                            
                                <div class="form-group">
                                    <input type="password" name="confirm_password" id="confirm_password" class="form-control" required maxlength="255" placeholder="Confirm password">
                                </div>
                           
                        </div>
                        <c:if test="${not empty errorMessage}">
                            <div class="alert alert-danger">
                                <strong>
                                        ${errorMessage}
                                </strong>
                            </div>
                        </c:if>
                        <div class="form-group">
                            <input type="submit" class="btn btn-lg btn-primary btn-block" value="Sign Up" onclick="validateUser()" width="50px">
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>
