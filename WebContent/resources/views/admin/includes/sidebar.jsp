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
 		<a href="<%=baseUrl%>admin-dashboard.htm" for="link">
 			<img src="<%=baseUrl%>resources/uploads/dash.svg" for="icon"/>
 			<div class="db-list-item-heading">
 				<span>Dashboard</span>
 			</div>
	 		<div class="db-list-item-triangle"></div>
 		</a>
 	</li>
 	<li class="db-list-item">
 		<a href="<%=baseUrl%>admin-department.htm" for="link">
 			<img src="<%=baseUrl%>resources/uploads/department.svg" for="icon"/>
 			<div class="db-list-item-heading">
 				<span>Department</span>
 			</div>
	 		<div class="db-list-item-triangle"></div>
 		</a>
 	</li>
 	<li class="db-list-item">
 		<a href="<%=baseUrl%>admin-department.htm" for="link">
 			<img src="<%=baseUrl%>resources/uploads/category.svg" for="icon"/>
 			<div class="db-list-item-heading">
 				<span>Leave Type</span>
 			</div>
	 		<div class="db-list-item-triangle"></div>
 		</a>
 	</li>
 	<li class="db-list-item">
 		<a href="<%=baseUrl%>admin-users.htm" for="link">
 			<img src="<%=baseUrl%>resources/uploads/employee.svg" for="icon"/>
 			<div class="db-list-item-heading">
 				<span>Users</span>
 			</div>
	 		<div class="db-list-item-triangle"></div>
 		</a>
 	</li>
 	<li class="db-list-item">
 		<a href="<%=baseUrl%>admin-manage-leave.htm" for="link">
 			<img src="<%=baseUrl%>resources/uploads/leaves.svg" for="icon"/>
 			<div class="db-list-item-heading">
 				<span>Manage leaves</span>
 			</div>
	 		<div class="db-list-item-triangle"></div>
 		</a>
 	</li>
 	<li class="db-list-item">
 		<a href="<%=baseUrl%>admin-logout.htm" for="link">
 			<img src="<%=baseUrl%>resources/uploads/logout.svg" for="icon"/>
 			<div class="db-list-item-heading">
 				<span>Logout</span>
 			</div>
	 		<div class="db-list-item-triangle"></div>
 		</a>
 	</li>
 	
 </ul>
 
</div>