<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%
ServletContext ctx = getServletContext();
String baseUrl = ctx.getInitParameter("url");
String viewPath = ctx.getInitParameter("viewPath");

%>
    
<div class="db-sidebar">
 
 <ul class="db-list-group">
 
 	<li class="db-list-item active">
 		<a href="<%=baseUrl%>employee-dashboard.htm" for="link">
 			<img src="<%=baseUrl%>resources/uploads/dash.svg" for="icon"/>
 			<div class="db-list-item-heading">
 				<span>Dashboard</span>
 			</div>
	 		
 		</a>
 	</li>
 	<li class="db-list-item">
 		<a href="#" for="link">
 			<img src="<%=baseUrl%>resources/uploads/leaves.svg" for="icon"/>
 			<div class="db-list-item-heading">
 				<span>Manage leaves</span>
 			</div>
	 		<div class="db-list-item-triangle"></div>
	 		<ul class="db-submenu-dropdown">
	 			<li><a href="employee-add-leave.htm" ><img src="<%=baseUrl%>resources/uploads/minus.svg" /> &nbsp;&nbsp;Create leave</a></li>
		 		<li><a href="employee-all-leaves.htm" ><img src="<%=baseUrl%>resources/uploads/minus.svg" /> &nbsp;&nbsp;All leaves requests</a></li>
		 		<li><a href="employee-status-leave.htm" ><img src="<%=baseUrl%>resources/uploads/minus.svg" /> &nbsp;&nbsp;Leave status</a></li>
		 	</ul>
 		</a>
 	</li>
 	<li class="db-list-item">
 		<a href="<%=baseUrl%>logout.htm" for="link">
 			<img src="<%=baseUrl%>resources/uploads/logout.svg" for="icon"/>
 			<div class="db-list-item-heading">
 				<span>Logout</span>
 			</div>
	 		
 		</a>
 	</li>
 	
 </ul>
 
</div>

<div class="db-sub-menu">
 	
</div>