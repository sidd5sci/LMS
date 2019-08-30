<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
        import="java.util.*,com.frapwise.entities.*,com.frapwise.models.*"%>

    
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
	    		<th>Username</th>
	    		<th>Home office</th>
	    		<th>Email</th>
	    		<th>Role</th>
	    		<th>department</th>
	    		<th>Action</th>
	    	</tr>
    	</thead>
    	<tbody>
    	
    		
    		<c:forEach var="user" items="${users}">
   				<tr>
   					<td><c:out value="${user.getId()}"/></td>
   					<td><c:out value="${user.getFname()}"/></td>
   					<td><c:out value="${user.getUsername()}"/></td>
   					<td><c:out value="${user.getHomeOffice()}"/></td>
   					<td><c:out value="${user.getEmail()}"/></td>
   					<td><c:out value="${user.getRole()}"/></td>
   					
   					<td>
   					<c:forEach var="d" items="${departments}">
							<c:if test="${d.getId() == user.getDepartmentId()}" >
								<c:out value="${d.getName()}"/>
							</c:if>
						</c:forEach>
					</td>
   					<td>
   						<a href="<%=baseUrl%>admin-edit-employee.htm?id=${user.getId()}">Edit</a>
   						<a href="<%=baseUrl%>admin-edit-employee.htm?id=${user.getId()}">Remove</a>
   					</td>
   				</tr>
   			</c:forEach>
    	</tbody>
    
    
    </table>
    
    </div>