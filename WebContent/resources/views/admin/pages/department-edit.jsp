<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import="com.frapwise.entities.*"%>
<%
 Department dpt = (Department) request.getAttribute("department");
 
%>

	<div class="row">
		<div class="col-md-3"></div>
		<div class="col-md-6">
			<div class="alert-message"></div>
			<form action="admin-edit-department-db.htm" method="post">
		    	<input type="hidden" name="id" value="<%=dpt.getId()%>"/>
		    	
		    	<div class="form-group">
		    		<label>Department name</label>
		    		<input type="text" class="form-control" name="departmentName" value="<%=dpt.getName()%>"/> 
		    	</div>
		    	<div class="form-group">
		    		<label>Department description</label>
		    		<textarea class="form-control" name="departmentDesc"><%=dpt.getDescription()%></textarea> 
		    	</div>
		
		    	<button type="submit" class="form-btn">Update Department</button>
		    </form>
		
		</div>
		<div class="col-md-3"></div>
	</div>    
    