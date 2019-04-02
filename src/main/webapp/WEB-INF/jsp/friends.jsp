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
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js">
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

    <div class="container" style="margin-top:40px">
        <div class="row">
            <div class="col-sm-4"><br></br> 
            <img style="width: 200px; height: 200px" src="data:image/jpeg;base64,${avatarpic}" class="img-thumbnail" alt="Cinque Terre" id="profilepic">
                <a href="logout" onClick=""> ${logout} </a> <br></br> 
                <a href="profile" onClick="profile.jsp"> ${profile_update} </a> <br></br> 
                <a href="timeline" onClick="timeline.jsp">Return to Timeline</a> <br></br>
            </div>
        
            <div class="col-sm-3">
                <div class="panel panel-default" style="margin-top: 10px">
                    <div class="panel-body">
                    
						<form role="form" action="/friends" method="post"
							autocomplete="off">
							<div class="form-group">
								<input type="text" name="firstName" id="firstName" class="form-control"
									placeholder="First Name" maxlength="255"
									value='${param.firstName}'>
							</div>
							<div class="form-group">
								<input type="text" name="lastName" id="lastName" class="form-control"
									placeholder="Last name" maxlength="255"
									value='${param.lastName}'>
							</div>
							
							<div class="form-group">
								<input type="text" name="cityId" id="cityId" class="form-control"
									placeholder="City" maxlength="255"
									value='${param.cityId}'>
							</div>
							
							<div class="form-group">
								<select>
									<option value="country">Country</option>
								</select>
							</div>
							<div class="form-group">
								<select>
									<option value="state">State/Province</option>
								</select>
							</div>
							<div class="form-group">
								<input type="submit" class="btn btn-lg btn-primary btn-block"
									value="Find Friends" name="findFriends">
							</div>
						</form>
					</div>
                </div>
                <h4 style="margin-top: 50px;">Results</h4>
                <c:forEach var="users" items="${users}" varStatus="status">
                <form:form role="form" action="/addfriends" method="post" modelAttribute = "addfriendsForm">
				<p> ${users.getFriendToken()} ${users.getFriendConfirmationToken()} ${users.getId()} ${users.getFirstName()} ${users.getLastName()}, ${users.getCityId()}, ${users.getStateId()}, ${users.getCountryId()} </p>
				 <div class="add-friends">
				<input type = "hidden" value="${users.getId()}" name = "addFriends">
                   <input type = "submit" class="btn btn-lg btn-primary btn-block" value="Add friend"/>
                </div>
		
				</form:form>
				</c:forEach>
<%-- 				<div>${enableConfirmButton}</div>
				<div>${enableRemoveButton}</div>
				<p>${friends.get(0).getFirstName()} ${friends.get(0).getLastName()}</p> --%>
                <h4 style="margin-top: 20px;">My Friends</h4>
                <c:forEach var="friends" items="${friends}" varStatus="status">
                 <c:choose>
                <c:when test="${enableRemoveButton == true}">
                <form:form role="form" action="/removefriends" method="post" modelAttribute = "removefriendsForm">
				<p>${friends.getFriendToken()} ${friends.getFriendConfirmationToken()} ${friends.getId()} ${friends.getFirstName()} ${friends.getLastName()}, ${friends.getCityId()}, ${friends.getStateId()}, ${friends.getCountryId()} </p>
				<div>

				<input type = "hidden" value="${friends.getId()}" name = "removeFriends">
                   <input type = "submit" class="btn btn-lg btn-primary btn-block" value="Remove friends"/>

                <h4 style="margin-top: 20px;">Friend Request Pending</h4>
                </div>
				</form:form>
				</c:when>
				<c:otherwise>
 				 <c:if test="${enableConfirmButton == true}">
				<form:form role="form" action="/confirmfriend" method="post" modelAttribute = "confirmfriendForm">
				<div class="confirm-friend">
				<p>${friends.getFriendToken()} ${friends.getFriendConfirmationToken()} ${friends.getId()} ${friends.getFirstName()} ${friends.getLastName()}, ${friends.getCityId()}, ${friends.getStateId()}, ${friends.getCountryId()} </p>
                <input type = "hidden" value="${friends.getId()}" name = "confirmfriend">
                    <input type="submit" class="btn btn-lg btn-primary btn-block" value="Confirm Friend"/>
                </div>
                </form:form>
                  </c:if> 
                  </c:otherwise>
                  </c:choose>
				</c:forEach>
        </div>

       </div>
    </div>    
</body>
</html>