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
							<img class="db-card-img" src="<%=baseUrl%>resoureces/uploads/category.svg" height="60px"/>
						</div>
						<div class="col-md-6">
							<div class="db-card-heading"><h2>Max Leaves</h2></div>
							<div class="db-card-content"><p>26</p></div>
						</div>
					</div>
				</div>
			</div>
			<div class="col-md-3">
				<div class="db-card">
					<div class="row">
						<div class="col-md-6">
							<img class="db-card-img" src="<%=baseUrl%>resoureces/uploads/category.svg" height="60px"/>
						</div>
						<div class="col-md-6">
							<div class="db-card-heading"><h2>Heading</h2></div>
							<div class="db-card-content"><p>456</p></div>
						</div>
					</div>
				</div>
			</div>
			<div class="col-md-3">
				<div class="db-card">
					<div class="row">
						<div class="col-md-6">
							<img class="db-card-img" src="<%=baseUrl%>resoureces/uploads/category.svg" height="60px"/>
						</div>
						<div class="col-md-6">
							<div class="db-card-heading"><h2>Pending leaves</h2></div>
							<div class="db-card-content"><p>2</p></div>
						</div>
					</div>
				</div>
			</div>
			<div class="col-md-3">
				<div class="db-card">
					<div class="row">
						<div class="col-md-6">
							<img class="db-card-img" src="<%=baseUrl%>resoureces/uploads/category.svg" height="60px"/>
						</div>
						<div class="col-md-6">
							<div class="db-card-heading"><h2>Total leaves </h2></div>
							<div class="db-card-content"><p>46</p></div>
						</div>
					</div>
				</div>
			</div>
		</div>
    