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


<html>
<head>
<title>Friends</title>
<meta charset="utf-8" name="viewport"
    content="width=device-width, initial-scale=1">
</head>

<fmt:setBundle basename="locale" var="loc"/>
<fmt:message bundle="${loc}" key="find_friends" var="find_friends"/>

<body>

    <div class="header">
        <h2>Friend Book</h2>
    </div>
            <div class="col-sm-4">
                <a href="login.jsp"> <img class="avatar"
                    src="../../icons/avatar.png" />
                </a> <br></br> 
                <a href="logout" onClick=""> ${logout} </a> <br></br> 
                <a href="profile_update" onClick=""> ${profile_update} </a> <br></br> 
                <a href="post_update" onClick="">Return to Timeline</a> <br></br>
            </div>
        
            <div class="col-sm-3">
                <div class="panel panel-default" style="margin-top: 10px">
                    <div class="panel-body">
                    
						<form:form method="POST" modelAttribute="friendsFrom" class="form-signin">
						
						<h2 class="form-signin-heading">Sign in</h2>
							<spring:bind path="email">
								<div class="form-group ${status.error ? 'has-error' : ''}">
									<form:input type="text" path="firstName" class="form-control"
										placeholder="First Name" autofocus="true"></form:input>
									<form:errors path="firstName"></form:errors>
								</div>
							</spring:bind>
							<spring:bind path="lastName">
								<div class="form-group ${status.error ? 'has-error' : ''}">
									<form:input type="text" path="lastName"
										class="form-control" placeholder="Password"></form:input>
									<form:errors path="lastName"></form:errors>
								</div>
							</spring:bind>
							
							<div class="col-sm-3">
							<div class="button-group">
								<input type="submit" class="btn btn-lg btn-primary btn-block"
									value="Find Friends" name="findFriends">
							</div>
							</div>
						</form:form>
					</div>
                </div>
                </div>
                
                <form:form method="POST" modelAttribute="removefriendsForm" class="form-signin">
                <c:forEach var="friends" items="${friends}" varStatus="status">
                    <a href="login.jsp"> <img class="avatar" src="../../icons/avatar.png" /></a>
				<p>${friends.getId()} ${friends.getFirstName()} ${friends.getLastName()}, ${friends.getCityId()}, ${friends.getStateId()}, ${friends.getCountryId()} </p>
				<div>
                    <input type = "submit" id ="${friends.getId()}" class="btn btn-lg btn-primary btn-block" name="removeFriends" value="Remove friends"/>
                </div>
				</c:forEach>
				</form:form>
                
                
</body>
</html>
