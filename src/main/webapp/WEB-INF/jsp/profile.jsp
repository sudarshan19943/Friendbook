<%--@elvariable id="errorMessage" type="String"--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<style type="text/css">
    <%@include file="css/chat.css"%>
    <%@include file="css/bootstrap.min.css"%>
    <%@include file="css/bootstrap-formhelpers.min.css"%>
    <%@include file="css/profile_style.css"%>
</style>
<script>
    <%@include file="js/jquery.min.js"%>
    <%@include file="js/avatar.js"%>
    <%@include file="js/bootstrap.min.js"%>
    <%@include file="js/bootstrap-formhelpers.min.js"%>
</script>

<html>
<head>
    <title>Profile</title>
    <meta charset="utf-8" name="viewport" content="width=device-width, initial-scale=1">
</head>

<fmt:setBundle basename="locale" var="loc"/>
<fmt:message bundle="${loc}" key="local.label.sign_up_here" var="sign_up_here"/>

 <script>
  
  
     $("#upload-file-message").text("File succesfully uploaded");
        },
        error: function () 
        {
          $("#upload-file-message").text(
              "File not uploaded (perhaps it's too much big)");
        }
      });
    }  
    
    var loadImage = function(event){
    	
    	var output = document.getElementById('output');
    	output.src = URL.createObjectURL(event.target.files[0]);  
    
 </script>
  

<body>

<div class="header">
    <h2>Friend Book</h2>
</div>

<div class="container" style="margin-top:40px">
    <div class="row centered-form">
        <div class="col-xs-12 col-sm-8 col-md-4 col-sm-offset-2 col-md-offset-4">
           <a href="login.jsp">
            
           <img class="avatar" src="../../icons/avatar.png"/> 
           <form id="upload-file-form" class="avatar">
			    <input id="upload-file-input" type="file" name="uploadfile" accept="*" />
			    <br />
			    <span id="upload-file-message"></span>
			  </form>
           </a>
            <div class="panel panel-default" style="margin-top: 10px">
                <div class="panel-body">
                    <form role="form" action="/profile" method="post" autocomplete="off">
                        <div class="form-group-first_name">
                            <label for="first_name">First Name</label>
                        </div>
                        <div class="form-group-last_name">
                            <label for="last_name">Last Name</label>
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
                        <div class="button-group">
                            <input type="submit" class="btn btn-lg btn-primary btn-block" value="Update Profile">
                        </div>
                        <div class="button-group">
                            <input type="submit" class="btn btn-lg btn-primary btn-block" value="Skip">
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>
