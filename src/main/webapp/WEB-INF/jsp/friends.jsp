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
         <div class="container-fluid" style="margin-top: 50px;">
            <div class="col-md-4 text-center" style="margin-right:200px;">
               <img style="width: 200px; height: 200px" src="data:image/jpeg;base64,${avatarpic}" class="img-thumbnail" alt="Cinque Terre" id="profilepic">
               <br></br> 
               <a href="logout" onClick="login.jsp">Logout</a> <br></br> 
               <a href="profile" onClick="profile.jsp">Update Profile </a> <br></br> 
               <a href="timeline" onClick="timeline.jsp">Return to Timeline</a> <br></br>
            </div>
            <div class="col-sm-3">
               <div class="panel panel-default" style="margin-top: 10px">
                  <div class="panel-body" style="width:300px;">
                     <form:form role="form" action="/friends" method="post" autocomplete="off" modelAttribute="usersForm">
                        <div class="form-group">
                           <form:input path="firstName" type="text" name="firstName" id="firstName" class="form-control"
                              placeholder="First Name" maxlength="255"
                              value='${param.firstName}'/>
                        </div>
                        <div class="form-group">
                           <form:input path="lastName" type="text" name="lastName" id="lastName" class="form-control"
                              placeholder="Last name" maxlength="255"
                              value='${param.lastName}'/>
                        </div>
                        <div class="form-group">
                           <form:input path="cityId" type="text" name="cityId" id="cityId" class="form-control"
                              placeholder="City" maxlength="255"
                              value='${param.cityId}'/>
                        </div>
                        <div class="form-group">
                           <form:select name="country" class="countries" id="countryId" path="countryId">
                              <form:option value='${param.countryId}' >Select Country</form:option>
                           </form:select>
                           <form:select name="state" class="states" id="stateId" path="stateId" >
                              <form:option value='${param.stateId}'>Select State</form:option>
                           </form:select>
                           <script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
                           <script src="//geodata.solutions/includes/countrystatecity.js"></script>
                        </div>
                        <div class="form-group">
                           <input type="submit" class="btn btn-lg btn-primary btn-block"
                              value="Find Friends" name="findFriends">
                        </div>
                     </form:form>
                  </div>
               </div>
               <h4 style="margin-top: 20px;">My Friends</h4>
               <c:forEach var="friend" items="${friends}" varStatus="status">
                  <c:if test="${enableRemoveButton == true}">
                     <form:form role="form" action="/removefriends" method="post" modelAttribute = "removefriendsForm">
                        <p>
                           ${friend.getId()} ${friend.getFirstName()} ${friend.getLastName()}
                           <c:if test="${friend.getCityId()}">, ${friend.getCityId()}</c:if>
                           <c:if test="${friend.getStateId()}">, ${friend.getStateId()}</c:if>
                           <c:if test="${friend.getCountryId()}">, ${friend.getCountryId()}</c:if>
                        </p>
                        <div>
                           <input type = "hidden" value="${friend.getId()}" name = "removeFriends">
                           <input type = "submit" class="btn btn-lg btn-primary btn-block" value="Remove friends"/>
                        </div>
                     </form:form>
                  </c:if>
                  <c:if test="${enableConfirmButton == true}">
                     <form:form role="form" action="/confirmfriend" method="post" modelAttribute = "confirmfriendForm">
                        <div class="confirm-friend">
                           <p>
                              ${friend.getId()} ${friend.getFirstName()} ${friend.getLastName()}
                              <c:if test="${friend.getCityId()}">, ${friend.getCityId()}</c:if>
                              <c:if test="${friend.getStateId()}">, ${friend.getStateId()}</c:if>
                              <c:if test="${friend.getCountryId()}">, ${friend.getCountryId()}</c:if>
                           </p>
                           <input type = "hidden" value="${friend.getId()}" name = "confirmfriend">
                           <input type="submit" class="btn btn-lg btn-primary btn-block" value="Confirm Friend"/>
                        </div>
                     </form:form>
                  </c:if>
               </c:forEach>
               <h4 style="margin-top: 50px;">Results</h4>
               <c:forEach var="users" items="${users}" varStatus="status">
                  <form:form role="form" action="/addfriends" method="post" modelAttribute = "addfriendsForm">
                     <p>
                        ${users.getId()} ${users.getFirstName()} ${users.getLastName()} 
                        <c:if test="${users.getCityId()}">, ${users.getCityId()},
                        </c:if>
                        <c:if test="${users.getStateId()}"> ${users.getStateId()}, </c:if>
                        <c:if test="${users.getCountryId()}"> ${users.getCountryId()}</c:if>
                     </p>
                     <div class="add-friends">
                        <input type = "hidden" value="${users.getId()}" name = "addFriends">
                        <input type = "submit" class="btn btn-lg btn-primary btn-block" value="Add friend"/>
                     </div>
                  </form:form>
               </c:forEach>
            </div>
         </div>
      </div>
   </body>
</html>