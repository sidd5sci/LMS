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
		<title>Dashboard</title>
		<link rel="stylesheet" href="<%=baseUrl%>resources/css/grid.css"/>
		<link rel="stylesheet" href="<%=baseUrl%>resources/css/main.css"/>
		<link rel="stylesheet" href="<%=baseUrl%>resources/css/admin/dashboard.css"/>
		
	</head>
<body>



	<jsp:include page="includes/header.jsp"/>
	<jsp:include page="includes/sidebar.jsp"/>
	
	<div class="db-main-content">
	
	<h3 style="font-size: 30px;font-weight: lighter;">Dashboard</h3>
	<hr style="background:#ccc;width:100%;height:1px;"/>
	</div>
	
	<jsp:include page="includes/footer.jsp"/>
</body>
</html>