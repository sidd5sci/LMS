<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%
ServletContext ctx = getServletContext();
String baseUrl = ctx.getInitParameter("url");
String viewPath = ctx.getInitParameter("viewPath");

%>
    
    <h3 style="font-size: 30px;font-weight: lighter;">Dashboard</h3>
		<hr style="background:#ccc;width:100%;height:1px;"/>
		<div class="row">
			<div class="col-md-3">
				<div class="db-card">
					<div class="row">
						<div class="col-md-6">
							<img class="db-card-img" src="<%=baseUrl%>resources/uploads/category.svg" height="60px"/>
						</div>
						<div class="col-md-6">
							<div class="db-card-heading"><h2>Leave Types</h2></div>
							<div class="db-card-content"><p>5</p></div>
						</div>
					</div>
				</div>
			</div>
			<div class="col-md-3">
				<div class="db-card">
					<div class="row">
						<div class="col-md-6">
							<img class="db-card-img" src="<%=baseUrl%>resources/uploads/employee.svg" height="60px"/>
						</div>
						<div class="col-md-6">
							<div class="db-card-heading"><h2>Employees</h2></div>
							<div class="db-card-content"><p>2</p></div>
						</div>
					</div>
				</div>
			</div>
			<div class="col-md-3">
				<div class="db-card">
					<div class="row">
						<div class="col-md-6">
							<img class="db-card-img" src="<%=baseUrl%>resources/uploads/category.svg" height="60px"/>
						</div>
						<div class="col-md-6">
							<div class="db-card-heading"><h2>Pending Leaves</h2></div>
							<div class="db-card-content"><p>6</p></div>
						</div>
					</div>
				</div>
			</div>
			<div class="col-md-3">
				<div class="db-card">
					<div class="row">
						<div class="col-md-6">
							<img class="db-card-img" src="<%=baseUrl%>resources/uploads/category.svg" height="60px"/>
						</div>
						<div class="col-md-6">
							<div class="db-card-heading"><h2>Todays Leaves</h2></div>
							<div class="db-card-content"><p>16</p></div>
						</div>
					</div>
				</div>
			</div>
		</div>
    