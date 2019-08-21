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
	 		
 		</a>
 	</li>
 	<li class="db-list-item">
 		<a href="<%=baseUrl%>admin-department.htm" for="link" >
 			<img src="<%=baseUrl%>resources/uploads/department.svg" for="icon"/>
 			<div class="db-list-item-heading">
 				<span>Department</span>
 			</div>
	 		<div class="db-list-item-triangle"></div>
	 		
	 		<ul class="db-submenu-dropdown">
		 		<li><a href="admin-add-department.htm" ><img src="<%=baseUrl%>resources/uploads/minus.svg" /> &nbsp;&nbsp;Add department</a></li>
		 		<li><a href="admin-all-department.htm" ><img src="<%=baseUrl%>resources/uploads/minus.svg" /> &nbsp;&nbsp;All department</a></li>
		 	</ul>
 		</a>
 	</li>
 	<li class="db-list-item">
 		<a href="#" for="link">
 			<img src="<%=baseUrl%>resources/uploads/category.svg" for="icon"/>
 			<div class="db-list-item-heading">
 				<span>Leave Type</span>
 			</div>
	 		<div class="db-list-item-triangle"></div>
	 		
	 		<ul class="db-submenu-dropdown">
		 		<li><a href="admin-add-leavetype.htm" ><img src="<%=baseUrl%>resources/uploads/minus.svg" /> &nbsp;&nbsp;Add leave type</a></li>
		 		<li><a href="admin-all-leavetype.htm" ><img src="<%=baseUrl%>resources/uploads/minus.svg" /> &nbsp;&nbsp;All leave types</a></li>
		 	</ul>
 		</a>
 	</li>
 	<li class="db-list-item">
 		<a href="#" for="link">
 			<img src="<%=baseUrl%>resources/uploads/employee.svg" for="icon"/>
 			<div class="db-list-item-heading">
 				<span>Employees</span>
 			</div>
	 		<div class="db-list-item-triangle"></div>
	 		<ul class="db-submenu-dropdown">
		 		<li><a href="admin-add-employee.htm" ><img src="<%=baseUrl%>resources/uploads/minus.svg" /> &nbsp;&nbsp;Add user</a></li>
		 		<li><a href="admin-all-employee.htm" ><img src="<%=baseUrl%>resources/uploads/minus.svg" /> &nbsp;&nbsp;All users</a></li>
		 	</ul>
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
	 			<li><a href="admin-add-leave.htm" ><img src="<%=baseUrl%>resources/uploads/minus.svg" /> &nbsp;&nbsp;Create leave</a></li>
		 		<li><a href="admin-today-leaves.htm" ><img src="<%=baseUrl%>resources/uploads/minus.svg" /> &nbsp;&nbsp;Todays leaves</a></li>
		 		<li><a href="admin-all-leaves.htm" ><img src="<%=baseUrl%>resources/uploads/minus.svg" /> &nbsp;&nbsp;All leaves</a></li>
		 		<li><a href="admin-assign-leaves.htm" ><img src="<%=baseUrl%>resources/uploads/minus.svg" /> &nbsp;&nbsp;Assign Max leaves</a></li>
		 		<li><a href="admin-max-leaves-status.htm" ><img src="<%=baseUrl%>resources/uploads/minus.svg" /> &nbsp;&nbsp;Max leaves status</a></li>
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