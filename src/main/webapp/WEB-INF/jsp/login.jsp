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

<fmt:setBundle basename="locale" var="loc"/>
<fmt:message bundle="${loc}" key="local.label.sign_up_here" var="sign_up_here"/>
<fmt:message bundle="${loc}" key="local.label.new_user" var="new_user"/>
<fmt:message bundle="${loc}" key="local.label.forgot_password" var="forgot_password"/>
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
               <div class="type-text">
                  <h4><strong>Share your posts </strong> with your friends...</h4>
                  <h4><strong>See friends' posts</strong> on your Timeline...</h4>
               </div>
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
                        <div class="form-signin"> 
                           <a href="forgotpassword" onClick="forgotpassword.jsp"> ${forgot_password} </a>
                        </div>
                     </form:form>
                     <div>
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
                  <div class="panel-footer">
                     <strong>${new_user}</strong> 
                     <p>
                        <a href="registration" onClick="registration.jsp"> ${sign_up_here} </a> 
                  </div>
               </div>
            </div>
         </div>
      </div>
      <script
         src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
   </body>
</html>