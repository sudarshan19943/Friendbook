<%--@elvariable id="errorMessage" type="String"--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<link rel="stylesheet" type="text/css"
    href="webjars/bootstrap/3.3.7/css/bootstrap.min.css" />

<style type="text/css">
    <%@include file="css/bootstrap.min.css"%>
    <%@include file="css/bootstrap-formhelpers.min.css"%>
    <%@include file="css/friends_style.css"%>
</style>
<script>
    <%@include file="js/jquery.min.js"%>
    <%@include file="js/bootstrap.min.js"%>
    <%@include file="js/bootstrap-formhelpers.min.js"%>
</script>

<html>
<head>
<title>Add Friends</title>
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

    <div class="container" style="margin-top:40px">
        <div class="row">
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
                        <form role="form" action="/profile" method="post" autocomplete="off">
                            <div class="form-group-firstname">
                                <label for="firstname">First Name</label>
                            </div>
                            <div class="form-group-lastname">
                                <label for="lastname">Last Name</label>
                            </div>
                            <div class="form-group">
                                <input type="text" name="city" id="city" class="form-control" placeholder="City" required maxlength="255" value='${param.city}'>
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
                        </form>
                    </div>
                </div>
                <h4 style="margin-top: 50px;">Results</h4>
                <a href="login.jsp"> <img class="avatar"
                    src="../../icons/avatar.png" />
                </a> <br></br>

                <a href="login.jsp"> <img class="avatar"
                    src="../../icons/avatar.png" />
                </a> <br></br>

                <h4 style="margin-top: 20px;">My Friends</h4>

                <a href="login.jsp"> <img class="avatar"
                    src="../../icons/avatar.png" />
                </a> <br></br>

                <a href="login.jsp"> <img class="avatar"
                    src="../../icons/avatar.png" />
                </a> <br></br>
            </div>


            <div class="col-sm-3">
                <div class="button-group">
                        <input type="submit" class="btn btn-lg btn-primary btn-block" value="Find Friends">
                </div>
                <label></label>

                <div class="add-friends">
                    <input type="submit" class="btn btn-lg btn-primary btn-block" value="Add Friend">
                </div>

                <div class="add-friends">
                    <input type="submit" class="btn btn-lg btn-primary btn-block" value="Add Friend">
                </div>

                <div class="my-friends">
                    <input type="submit" class="btn btn-lg btn-primary btn-block" value="Remove Friend">
                </div>

                <div class="my-friends">
                    <input type="submit" class="btn btn-lg btn-primary btn-block" value="Confirm Friend">
                </div>
            </div>
        </div>

       
    </div>    
</body>
</html>
