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
   <fmt:setBundle basename="locale" var="loc"/>
   <fmt:message bundle="${loc}" key="local.label.sign_up_here" var="sign_up_here"/>
   <body>
      <div class="header">
         <h2>Friend Book</h2>
      </div>
      <div class="container" style="margin-top: 40px">
         <div class="row centered-form">
            <div
               class="col-xs-12 col-sm-8 col-md-4 col-sm-offset-2 col-md-offset-4">
               <div class="panel panel-default">
                  <div class="panel-heading">
                     <h3 class="form-signin-heading"><strong>Please sign up </strong></h3>
                  </div>
                  <div class="panel-body">
                     <form:form method="POST" modelAttribute="registrationForm"
                        class="form-signin">
                        <spring:bind path="email">
                           <div class="form-group ${status.error ? 'has-error' : ''}">
                              <form:input type="text" path="email" class="form-control"
                                 placeholder="E-Mail" autofocus="true"></form:input>
                              <form:errors path="email"></form:errors>
                           </div>
                        </spring:bind>
                        <div class="row">
                           <div class="col-xs-6 col-sm-6 col-md-6">
                              <spring:bind path="firstName">
                                 <div class="form-group ${status.error ? 'has-error' : ''}">
                                    <form:input type="text" path="firstName" class="form-control"
                                       placeholder="First Name" autofocus="true" ></form:input>
                                    <form:errors path="firstName"></form:errors>
                                 </div>
                              </spring:bind>
                           </div>
                           <div class="col-xs-6 col-sm-6 col-md-6">
                              <spring:bind path="lastName">
                                 <div class="form-group ${status.error ? 'has-error' : ''}">
                                    <form:input type="text" path="lastName" class="form-control"
                                       placeholder="Last Name" autofocus="true"></form:input>
                                    <form:errors path="lastName"></form:errors>
                                 </div>
                              </spring:bind>
                           </div>
                        </div>
                        <div class="row">
                           <div class="col-xs-6 col-sm-6 col-md-6">
                              <spring:bind path="password">
                                 <div class="form-group ${status.error ? 'has-error' : ''}">
                                    <form:input type="password" path="password"
                                       class="form-control" placeholder="Password"></form:input>
                                    <form:errors path="password"></form:errors>
                                 </div>
                              </spring:bind>
                           </div>
                           <div class="col-xs-6 col-sm-6 col-md-6">
                              <spring:bind path="passwordConfirm">
                                 <div class="form-group ${status.error ? 'has-error' : ''}">
                                    <form:input type="password" path="passwordConfirm"
                                       class="form-control" placeholder="Confirm password"></form:input>
                                    <form:errors path="passwordConfirm"></form:errors>
                                 </div>
                              </spring:bind>
                           </div>
                        </div>
                        <button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button>
                     </form:form>
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
                  </div>
               </div>
            </div>
         </div>
      </div>
      <script
         src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
   </body>
</html>
