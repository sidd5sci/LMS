<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import="java.util.*,com.frapwise.entities.*"%>
<%
ServletContext ctx = getServletContext();
String baseUrl = ctx.getInitParameter("url");
String viewPath = ctx.getInitParameter("viewPath");
%>
<br/>

    <div class="data-tables">
    
    <table id="dataTable">
    
    	<thead>
    		<tr>
	    		<th>Id</th>
	    		<th>Name</th>
	    		<th>Default</th>
	    		<th>Description</th>
	    		<th>Period</th>
	    		<th>Status</th>
	    		<th>Action</th>
	    	</tr>
    	</thead> 
    	<tbody>
    		<c:forEach var="l" items="${leavetypes}">
					<tr>
						<td><c:out value="${l.getId()}"/></td>
						<td><c:out value="${l.getName()}"/></td>
						<td><c:out value="${l.getDefaultValue()}"/></td>
						<td><c:out value="${l.getDescription()}"/></td>
						<td><c:out value="${l.getPeriod()}"/></td>
						<td><c:out value="${l.getStatus()}"/></td>
						<td>
							<a href="<%=baseUrl%>admin-edit-leavetype.htm?id=${l.getId()}">Edit</a>&nbsp;
							<a href="<%=baseUrl%>admin-remove-leavetype.htm?id=${l.getId()}">Remove</a>&nbsp;
							<a href="<%=baseUrl%>admin-renew-leavetype.htm?id=${l.getId()}">Renew</a>
						</td>
					</tr>
			</c:forEach>
    	</tbody>
    </table>
    
    </div>