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
    <title>New Post</title>
    <meta charset="utf-8" name="viewport" content="width=device-width, initial-scale=1">
</head>

<fmt:setBundle basename="locale" var="loc"/>
<fmt:message bundle="${loc}" key="local.label.logout" var="logout"/>
<fmt:message bundle="${loc}" key="local.label.profile_update" var="profile_update"/>
<fmt:message bundle="${loc}" key="local.label.return_to_timeline" var="return_to_timeline"/>
<fmt:message bundle="${loc}" key="local.label.post" var="post"/>
<fmt:message bundle="${loc}" key="local.label.post_photo" var="post_photo"/>

<body>

<div class="header">
    <h2>Friend Book</h2>
</div>

<div class="container-fluid" style="margin-top:50px">
        <div class="col-md-4 text-center">
           <a href="login.jsp">
           <img class="avatar" src="../../icons/avatar.png"/> 
           </a>
           
           <br></br>
<%--                 <div class="panel-body">
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
                                    <a href="forgotpassword" onClick=""> ${forgot_password} </a>
				             </div>
                        </div>
                    </form>
                     </div>  --%>
                     <a href="logout" onClick=""> ${logout} </a>
                     <br></br>
                     <a href="profile_update" onClick=""> ${profile_update} </a>  
                   <br></br>
             <a href="return_to_timeline" onClick=""> ${return_to_timeline} </a> 
             <br></br> 
    </div>
    <div class="col-md-8">
    <div class="form-group">
  <textarea class="form-control" rows="5" id="comment"></textarea>
  <br></br>
  <div class="form-group">
  	<input type="submit" class="btn btn-lg btn-primary" value="${post}">
  </div>
  <br></br>
  <h4> Or click here to post a photo on your timeline!</h4>
    <div class="form-group">
  	<input type="submit" class="btn btn-lg btn-primary" value="${post_photo}">
  </div>
</div>
</div>
</div>
</body>
</html>
