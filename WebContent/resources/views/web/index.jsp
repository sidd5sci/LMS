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
		<title>Home | LMS</title>
		<link rel="stylesheet" href="<%=baseUrl%>resources/css/grid.css"/>
		<link rel="stylesheet" href="<%=baseUrl%>resources/css/main.css"/>
		<link rel="stylesheet" href="<%=baseUrl%>resources/css/home.css"/>
		
		
	</head>
<body>

	<jsp:include page ="includes/header.jsp"/>
	
	<div style="    width: 100%;
			    height: 89.6vh;
			    background: linear-gradient(45deg, #4d2673, #05d0c6);
			    overflow: hidden;
			    float: left;
			    position: absolute;
			    top: 70px;
    ">
		<div class="container">	
			
			<div class="" style="width:100%;display:flex;">
			
				<div class="col-md-6 ">
					<p style="height:50px;">&nbsp;</p>
					<h1 class="display-1">
						Leave Managment System
					</h1>
					<p class="display-text-1">
						Click below link for demo<br>
						Admin login : admin@example.com / 123456<br>
						Agent login : agent@example.com / 123456<br>
					</p>
					<p style="height:20px;">&nbsp;</p>
					<a class="btn" href="login.htm"> Check Demo </a>
	
				</div>
				<div class="col-md-6 ">
					<p style="height:50px;">&nbsp;</p>
					<img src="<%=baseUrl%>resources/uploads/Screenshot_19.png"/>
				</div>
			</div>
			
		</div>
	
	</div>
</body>
</html>	