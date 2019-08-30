<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import="java.util.*,com.frapwise.entities.*"%>
<%
ServletContext ctx = getServletContext();
String baseUrl = ctx.getInitParameter("url");
String viewPath = ctx.getInitParameter("viewPath");
%>
    <div class="row">
    
    
    	<div class="col-md-3"></div>
    	<div class="col-md-6">
    		<div class="alert-message"></div>
    		<form action="<%=baseUrl%>admin-assign-leave-db.htm" method="post">
    		
    			<div class="form-group">
    				<label>Leave type *</label>
    				<select class="form-control" name="leaveType" required>
    					<option value="0">Select ...</option>
    					<c:forEach var="l" items="${leavetypes}">
							<option value="${l.getId()}"><c:out value="${l.getName()}"/></option>
						</c:forEach>
    				</select>
    			</div>
    			<div class="form-group">
    				<label>Employee</label>
    				<select class="form-control" name="user" required>
    					<option value="0">Select ...</option>
    					<c:forEach var="u" items="${users}">
							<option value="${u.getId()}"><c:out value="${u.getUsername()}"/></option>
						</c:forEach>
    				</select>
    			</div>
    			<div class="form-group">
    				<label>Maximum leaves</label>
    				<input type="number" class="form-control" name="max" id="max" autocomplete="off" required/>
    			</div>
    			<button type="submit" class="form-btn">Submit</button>
    		</form>
    	
    	</div>
    	<div class="col-md-3"></div>
    </div>