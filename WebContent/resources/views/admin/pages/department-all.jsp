<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
        import="java.util.*,com.frapwise.entities.*"
        %>
<%
ServletContext ctx = getServletContext();
String baseUrl = ctx.getInitParameter("url");
String viewPath = ctx.getInitParameter("viewPath");
%>
    <div class="data-tables">
    
    <table id="dataTable">
    
    	<thead>
    		<tr>
	    		<th>Id</th>
	    		<th>Name</th>
	    		<th>Description</th>
	    		<th>Action</th>
    		</tr>
    	</thead>
    	<tbody>
    		<c:forEach var="d" items="${departments}">
    			<tr>
    				<td><c:out value="${d.getId()}"/></td>
    				<td><c:out value="${d.getName()}"/></td>
    				<td><c:out value="${d.getDescription()}"/></td>
    				<td>
    					<a href="<%=baseUrl%>admin-edit-department.htm?id=${d.getId()}">Edit</a>
    					<a href="<%=baseUrl%>admin-remove-department.htm?id=${d.getId()}">Remove</a>
    				</td>
    				
    			</tr>
    		</c:forEach>
    	</tbody>
    
    
    </table>
    
    </div>