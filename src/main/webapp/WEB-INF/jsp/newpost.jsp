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
<meta charset="utf-8" name="viewport"
	content="width=device-width, initial-scale=1">
</head>
		

<fmt:setBundle basename="locale" var="loc" />
<fmt:message bundle="${loc}" key="local.label.logout" var="logout" />
<fmt:message bundle="${loc}" key="local.label.profile_update"
	var="profile_update" />
<fmt:message bundle="${loc}" key="local.label.return_to_timeline"
	var="return_to_timeline" />
<fmt:message bundle="${loc}" key="local.label.post" var="post" />

<body>

	<div class="header">
		<div class="search-container" style="float: right;">
			<input type="text" placeholder="Search..">
			<button type="button" onclick="location.href='/friends'" value="Find Friends" name="findFriends"><i class="fa fa-search"></i></button>
		</div>
		<h2>Friend Book</h2>
	</div>


	<div class="container-fluid" style="margin-top: 50px">
		<div class="col-md-4 text-center">
			<a href="login.jsp"> 
				 <img style="width: 200px; height: 200px" src="data:image/jpeg;base64,${avatarpic}" class="img-thumbnail" alt="Cinque Terre" id="profilepic">
			</a> <br></br> <a href="logout" onClick=""> ${logout} </a> <br></br> <a
				href="profile" onClick="profile.jsp"> ${profile_update} </a> <br></br> <a
				href="timeline" onClick="timeline.jsp"> ${return_to_timeline} </a> <br></br>
		</div>

		<div class="col-md-8">
			<br></br>
			<form role="form" action="/newpost" method="post" autocomplete="off">
				<div class="form-group">
					<input class="form-control" name="post" value='${param.post}'
						type="text" required>
				</div>
				<div class="form-group">
					<input type="submit" class="btn btn-lg btn-primary btn-block"
						value="${post}">
				</div>
			</form>
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
</body>
</html>
