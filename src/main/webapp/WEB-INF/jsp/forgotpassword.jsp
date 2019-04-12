<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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

<html lang="en">
   <head>
      <meta charset="utf-8">
      <title>Create an account</title>
   </head>
   <h2 align = "center">Instructions:</h2>
   <h4 align="center">Please enter the email address associated with your Friendbook account. A link containing on how to reset your password will be emailed to you.</h4>
   <body>
      <div class="container" style="margin-top: 40px">
         <div class="row centered-form">
            <div
               class="col-xs-12 col-sm-8 col-md-4 col-sm-offset-2 col-md-offset-4">
               <div class="panel panel-default">
                  <div class="panel-heading">
                     <strong>Please enter your Registered E-mail</strong>
                  </div>
                  <div class="panel-body">
                     <form:form method="POST" modelAttribute="forgotPasswordForm"
                        class="form-signin">
                        <h2 class="form-signin-heading">Forgot Password</h2>
                        <spring:bind path="email">
                           <div class="form-group ${status.error ? 'has-error' : ''}">
                              <form:input type="text" path="email" class="form-control"
                                 placeholder="E-Mail" autofocus="true"></form:input>
                              <form:errors path="email"></form:errors>
                           </div>
                        </spring:bind>
                        <button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button>
                     </form:form>
                  </div>
               </div>
            </div>
         </div>
      </div>
      <script
         src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
   </body>
</html>