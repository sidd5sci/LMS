<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%
ServletContext ctx = getServletContext();
String baseUrl = ctx.getInitParameter("url");
String viewPath = ctx.getInitParameter("viewPath");

%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Login | LMS</title>
		<link rel="stylesheet" href="<%=baseUrl%>resources/css/grid.css"/>
		<link rel="stylesheet" href="<%=baseUrl%>resources/css/main.css"/>
		<link rel="stylesheet" href="<%=baseUrl%>resources/css/auth/login.css"/>
		
		
	</head>
<body>

	<jsp:include page ="../web/includes/header.jsp"/>
	
	<div style="width:100%;height:90vh;background: linear-gradient(45deg, #4d2673, #05d0c6);">
		<div class="container">	
			<p style="min-height:50px;"></p>
			<div class="login-box">
				<div class="container">	
					<div class="login-box-img">
						<img src="<%=baseUrl%>resources/uploads/logo.png"/>
					</div>
					<h1 class="display-2 primary">Sign In</h1>
					<p style="min-height:22px;"></p>
					<form action="login-db.htm" method="post" class="login-form">
					
						<div class="form-group">
							<label>Email Address</label>
							<input class="form-control" type="email" name="email" id="email" placeholder="demo@frapwise.com" required/>
						</div>
						<div class="form-group">
							<label>Password</label>
							<input class="form-control" type="password" name="password" id="password" required/>
						</div>
						<button class="btn-login primary" type="submit">Sign In</button>
						
					</form>
					<a href="forget-password.htm">Forgot password?</a>
				</div>
			</div>
			
		</div>
	
	</div>
	<div class="alert" >
	<p>${message}</p>
</div>
</body>
</html>	




<script src="<%=baseUrl%>/resources/js/jquery.3.4.1.js"></script>
<script>


/*message cookie handler*/
function getCookie(cname) {
	  var name = cname + "=";
	  var decodedCookie = decodeURIComponent(document.cookie);
	  var ca = decodedCookie.split(';');
	  for(var i = 0; i <ca.length; i++) {
	    var c = ca[i];
	    while (c.charAt(0) == ' ') {
	      c = c.substring(1);
	    }
	    if (c.indexOf(name) == 0) {
	      return c.substring(name.length, c.length);
	    }
	  }
	  return "";
}

let message = getCookie("message");	
console.log("message:",message);
if(message != "" && message != null){
	$(".alert").css("display","block");
	message = message.replace(/_/g, " ");
	//message.replace('/\+/g', ' ');
	console.log(message);
	$(".alert").html(message);
	
}
</script>