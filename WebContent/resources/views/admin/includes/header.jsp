<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
ServletContext ctx = getServletContext();
String baseUrl = ctx.getInitParameter("url");
String viewPath = ctx.getInitParameter("viewPath");

%>
    <div class="db-header">
    
    	<div class="db-header-brand">
    		<a href="">FrapWise</a>
    	</div>
    	<div class="db-header-toggle">
    		<img src="<%=baseUrl%>resources/uploads/menu.svg" />
    	</div>
    	<div class="db-header-menu">
    		
    		<ul class="db-header-menu-list">
    			<li class="db-header-menu-list-item"><a href="home.htm">Home</a></li>
    			<li class="db-header-menu-list-item"><a href="about.htm">About</a></li>
    			<li class="db-header-menu-list-item"><a href="login.htm">Login</a></li>   			
    		</ul>
    	</div>
    
    </div>