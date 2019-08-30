<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import="java.util.*,com.frapwise.entities.*"%>
	
<%
ServletContext ctx = getServletContext();
String baseUrl = ctx.getInitParameter("url");
String viewPath = ctx.getInitParameter("viewPath");

 User user		  = (User)request.getAttribute("user");
%>    
    
    <div class="row">
    	<div class="col-md-3"></div>
    	<div class="col-md-6">
    		<div class="alert-message"></div>
    		<form action="employee-add-leave-db.htm" id="leaveForm" data-type="create" method="post">
    		
    			<input type="hidden" name="department" id="department" value="<%=user.getDepartmentId()%>"/>
    			<input type="hidden" name="user" id="user" value="<%=user.getId()%>"/>
    			<input type="hidden" name="url" id="url" value="<%=baseUrl%>"/>
    			<div class="form-group">
    				<label>Leave type *</label>
    				<select class="form-control" id="leavetype" name="leaveType" required>
    					<option value="0">Select ...</option>
    					<c:forEach var="o" items="${leavetypes}">
    						<option value="${o.getId()}"><c:out value="${o.getName()}"/></option>
    					</c:forEach>
    				</select>
    			</div>
    			
    			<div class="form-group">
    				<label>Leave from</label>
    				<input type="text" class="form-control" name="from" id="from" autocomplete="off" required/>
    			</div>
    			<div class="form-group">
    				<label>Leave to</label>
    				<input type="text" class="form-control" name="to" id="to" autocomplete="off" required/>
    			</div>
    			<div class="form-group">
    				<label>Time off type</label>
    				<select class="form-control" name="timeOffType" id="timeOff" required>
    					<option value="0">Select ...</option>
    					<option value="1">Full day</option>
    					<option value="2">Half day</option>
    				</select>
    			</div>
    			<div class="form-group">
    				<label>Duration</label>
    				<input type="number" class="form-control" name="duration" id="duration" required/>
    			</div>
    			<button type="submit" class="form-btn" id="submit" >Create leave request</button>
    		</form>
    	</div>
    	<div class="col-md-3"></div>
    </div>