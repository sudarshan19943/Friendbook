<%--@elvariable id="errorMessage" type="String"--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<link rel="stylesheet" type="text/css"
	href="webjars/bootstrap/3.3.7/css/bootstrap.min.css" />

<style type="text/css">
    <%@include file="css/bootstrap.min.css"%>
    <%@include file="css/bootstrap-formhelpers.min.css"%>
    <%@include file="css/timeline_style.css"%>
</style>
<script>
    <%@include file="js/jquery.min.js"%>
    <%@include file="js/bootstrap.min.js"%>
    <%@include file="js/bootstrap-formhelpers.min.js"%>
</script>

<html>
<head>
<title>Time Line</title>
<meta charset="utf-8" name="viewport"
	content="width=device-width, initial-scale=1">
</head>

<!-- <fmt:setBundle basename="locale" var="loc" />
<fmt:message bundle="${loc}" key="local.label.logout" var="logout" />
<fmt:message bundle="${loc}" key="local.label.profile_update"
	var="profile_update" />
<fmt:message bundle="${loc}" key="local.label.return_to_timeline"
	var="return_to_timeline" />
<fmt:message bundle="${loc}" key="local.label.post" var="post" /> -->

<body>

	<div class="header">
		<h2>Friend Book</h2>
	</div>

	<div class="container-fluid" style="margin-top: 50px">
		<div class="col-md-4 text-center">
			<a href="login.jsp"> <img class="avatar"
				src="../../icons/avatar.png" />
			</a> <br></br> 
			<a href="logout" onClick=""> ${logout} </a> <br></br> 
			<a href="profile_update" onClick=""> ${profile_update} </a> <br></br> 
			<a href="post_update" onClick=""> Post Update</a> <br></br>
			<a href="manage_friends" onClick="">Find / Manage Friends</a> <br></br>
		</div>

		<div class="search-box">
		<input type="text" placeholder="Find Friends...">
		</div>
		<div class="col-md-8">
			<br></br>
			 <div>Friends: ${message.get(0).getSender().getId()}</div> 
<%-- 			<c:forEach var="friends" items="${friends}" varStatus="status">
				<div>${friends.getFirstName()} ${friends.getLastName()} </div> --%>
				<c:forEach var="post" items="${message}" varStatus="status">
					<div>Posted on<%-- ${post.getDate()} --%></div>
					<div>${post.getBody()}</div>
					<c:set var="sender_id" value="${post.getSender().getId()}" />
					<c:set var="recipient_id" value="${post.getRecipient().getId()}" />
					<c:if test="${sender_id != recipient_id}">
						<form role="form" action="/timeline" method="post" modelAttribute = "commentForm"
							autocomplete="off">
							<div class="form-group-post-details">
								<label for="post-details"></label>
							</div>
							<div class="form-group">
								<input style="height: 50px" class="form-control" name="post"
									value='${param.post}' type="hidden">
							</div>
							<div class="form-group">
								<input style="height: 50px" class="form-control" name="comment"
									value='${param.comment}' type="text">
							</div>
							<div class="button-group">
								<input type="submit" class="btn btn-lg btn-primary btn-block"
									value="comment">
							</div>
							<br></br>
						</form>
					</c:if>
				</c:forEach>
			<%-- </c:forEach> --%>
		</div>
		<br></br>
		<div class="col-md-8">
			<br></br>
<%-- 			<form role="form" action="/timeline" method="post" autocomplete="off">
				<div class="form-group-post-details">
                            <label for="post-details"></label>
                 </div>
				<div class="form-group">
					<input style="height:50px" class="form-control" name="post" value='${param.post}'
						type="text">
				</div>
				<div class="button-group">
					<input type="submit" class="btn btn-lg btn-primary btn-block"
						value="comment">
				</div>
				<br></br>
			</form> --%>
		</div>
	</div>
	</div>
</body>
</html>
