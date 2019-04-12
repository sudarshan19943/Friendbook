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
<script>
   
   $(document).ready(function(){
       $("div.form-group-lastname label.tbh:empty").parent().hide()
   });
</script>
<html>
   <head>
      <title>Profile</title>
      <meta charset="utf-8" name="viewport" content="width=device-width, initial-scale=1">
   </head>
   <fmt:setBundle basename="locale" var="loc"/>
   <fmt:message bundle="${loc}" key="local.label.sign_up_here" var="sign_up_here"/>
   <style>
      #upload-file-input{
      display:none;
      }
      #profilepic{
      cursor:pointer;
      }
   </style>
   <script>

      $(document).ready(function() {
        
        $("#profilepic").on("click", function(){
      	  $('#upload-file-input').trigger('click');
        })
        function readURL(input) {
      
      	  if (input.files && input.files[0]) {
      	    var reader = new FileReader();
      
      	    reader.onload = function(e) {
      	      $('#profilepic').attr('src', e.target.result);
      	    }
      
      	    reader.readAsDataURL(input.files[0]);
      	  }
      	}
      
      	$("#upload-file-input").change(function() {
      	  readURL(this);
      	});
      	window.history.pushState(null, "", window.location.href);        
          window.onpopstate = function() {
              window.history.pushState(null, "", window.location.href);
          };
      });
      
   </script>
   <body>
      <div class="header">
         <h2>Friend Book</h2>
      </div>
      <div class="container" style="margin-top:40px">
         <div class="row centered-form">
            <div class="col-xs-12 col-sm-8 col-md-4 col-sm-offset-2 col-md-offset-4">
               <div class="panel panel-default" style="margin-top: 10px">
                  <div class="panel-body">
                     <form:form method="POST" modelAttribute="profileForm" enctype="multipart/form-data" class="form-signin" autocomplete="off">
                        <div class="form-group-profilepic" align="center">
                           <img style="width: 200px; height: 200px" src="data:image/jpeg;base64,${avatarpic}" class="img-thumbnail" alt="Cinque Terre" id="profilepic">
                        </div>
                        <div class="form-group-profilepic">
                           <input id="upload-file-input" type="file" name="profilepic" id="profilepic"   accept="*" />	
                        </div>
                        <div class="form-group-firstname" style="text-align:center">
                           <label for="fullName">${fullName}</label>
                        </div>
                        <div class="form-group-lastname">
                           <label for="city" class="tbh">${city}</label>
                        </div>
                        <div class="form-group">
                           <spring:bind path="countryId">
                              <form:select name="country" class="countries" id="countryId" path="countryId" >
                                 <form:option value="" path="countryId">Select Country</form:option>
                              </form:select>
                              <form:errors path="email"></form:errors>
                           </spring:bind>
                           <spring:bind path="countryId">
                              <form:select name="state" class="states" id="stateId" path="stateId">
                                 <form:option value="" path="stateId">Select State</form:option>
                              </form:select>
                           </spring:bind>
                           <spring:bind path="cityId">
                              <form:select name="city" class="cities" id="cityId" path="cityId">
                                 <form:option value="" path="cityId">Select City</form:option>
                              </form:select>
                           </spring:bind>
                           <script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
                           <script src="//geodata.solutions/includes/countrystatecity.js"></script>
                        </div>
                        <!-- update and Skip button -->
                        <div class="button-group" style="margin:auto;max-width:50%">
                           <input type="submit" class="btn btn-lg btn-primary btn-block" style="margin-bottom:20px" value="Update Profile" name="Update">
                        </div>
                        <div class="button-group" style="margin:auto;max-width:50%">
                           <input type="submit" class="btn btn-lg btn-primary btn-block" value="Skip" name="Skip">
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