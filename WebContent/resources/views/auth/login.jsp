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
</body>
</html>	