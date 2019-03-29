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
    <%@include file="js/bootstrap.min.js"%>
    <%@include file="js/bootstrap-formhelpers.min.js"%>
</script>

<script>
/* Be sure that ur dom is loaded */    
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

<<<<<<< HEAD
<style>




#upload-file-input{
display:none;
}
#profilepic{
cursor:pointer;
}
</style>

 <script>
  
    // bind the on-change event for the input element (triggered when a file
    // is chosen)
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
    });
    
  </script>
<body>
=======
 <script type="text/javascript">
  
    
    var loadImage = function(event){
        
        var output = document.getElementById('output');
        output.src = URL.createObjectURL(event.target.files[0]);

    function openfileDialog() {
    $("#fileLoader").click();
}

   
    
 </script>
    
    
  

<body>  
>>>>>>> dc3672108c40cee56e8314ee40fcd272868d1357

<div class="header">
    <h2>Friend Book</h2>
</div>

<div class="container" style="margin-top:40px">
    <div class="row centered-form">
        <div class="col-xs-12 col-sm-8 col-md-4 col-sm-offset-2 col-md-offset-4">
<<<<<<< HEAD
          <!--   <img class="avatar" id="profilepic" src="../../icons/avatar.png"/>
		    <input id="upload-file-input" type="file" name="profilepic" id="profilepic"   accept="*" />			  -->
           
=======
           <a href="function()">
            
           <img class="avatar" src="../../icons/avatar.png"/> 
           </a>
>>>>>>> dc3672108c40cee56e8314ee40fcd272868d1357
            <div class="panel panel-default" style="margin-top: 10px">
                <div class="panel-body">
                    <form role="form" action="/profile" enctype="multipart/form-data" method="post" autocomplete="off">
                    
                        <div class="form-group-profilepic" align="center">
                       <img style="width: 200px; height: 200px" src='${avatarpic}' class="img-thumbnail" alt="Cinque Terre" id="profilepic">
                        </div> 
                        
                        <div class="form-group-profilepic">
                           <input id="upload-file-input" type="file" name="profilepic" id="profilepic"   accept="*" />	
                        </div>
                        <div class="form-group-firstname">
                            <label for="fullName">${fullName}</label>
                        </div>
<<<<<<< HEAD
                        <div class="form-group-lastname">
                            <label for="city" class="tbh">${city}</label>
=======
                        <div class="form-group">
                            <select id="countries">
                                  <option value="country">Country</option>
                            </select>
>>>>>>> dc3672108c40cee56e8314ee40fcd272868d1357
                        </div>
                       <%--  <div class="form-group">
                            <input type="text" name="city" id="city" class="form-control" placeholder="City" required maxlength="255" value='${city}'>
                        </div> --%>
							<div class="form-group">
								<select name="country" class="countries" id="countryId">
									<option value="">Select Country</option>
								</select> <select name="state" class="states" id="stateId">
									<option value="">Select State</option>
								</select> <select name="city" class="cities" id="cityId">
									<option value="">Select City</option>
								</select>
								<script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
								<script src="//geodata.solutions/includes/countrystatecity.js"></script>
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
