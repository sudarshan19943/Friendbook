<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page session="true" %>
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
<script type="text/javacscript">
   window.history.pushState(null, "", window.location.href);        
           window.onpopstate = function() {
               window.history.pushState(null, "", window.location.href);
           };
</script>        
<html>
   <head>
      <title>Time Line</title>
      <meta charset="utf-8" name="viewport"
         content="width=device-width, initial-scale=1">
   </head>
   <body>
      <div class="header">
         <h2>Friend Book</h2>
      </div>
      <div class="container-fluid" style="margin-top: 50px">
         <div class="col-md-4 text-center">
            <img style="width: 200px; height: 200px" src="data:image/jpeg;base64,${avatarpic}" class="img-thumbnail" alt="Cinque Terre" id="profilepic">
            <br></br> 
            <a href="logout" onClick="login.jsp"> Logout</a> <br></br> 
            <a href="profile" onClick="profile.jsp"> Update Profile</a> <br></br> 
            <a href="newpost" onClick="newpost.jsp"> Post Update</a> <br></br>
            <a href="friends" onClick="friends.jsp">Find / Manage Friends</a> <br></br>
         </div>
         <div class="search-box">
            <input type="text" placeholder="Find Friends...">
         </div>
         <div class="col-md-8">
            <br></br>
            <c:forEach var="post" items="${types}">
               <form role="form" action="/comment" method="post" autocomplete="off">
                  <div class="postKey">
                     ${post.key}<br></br>
                  </div>
                  <div class="postBody"> ${post.value.getBody()}</div>
                  <div class="button-group">
                     <c:if test = "${fn:contains(post.key, 'Message')}">
                        <div class="commentBody">
                           <input type="text" placeholder="You can comment here..." name = "comment">
                        </div>
                        <input type = "hidden" value="${post.value.getId()}" name = "postId">
                        <input type="submit" class="btn btn-lg btn-primary btn-block" value="comment">
                     </c:if>
                  </div>
                  <c:forEach var="comment" items="${post.value.getComments()}">
                     <input type = "hidden" value="${comment.getBody()}" name = "comment">
                     <div class="commentPersonName">${comment.getBody()} <br></br></div>
                  </c:forEach>
               </form>
            </c:forEach>
         </div>
         <br></br>
      </div>
   </body>
</html>