<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import="com.frapwise.entities.*"%>
<%
 Department dpt = (Department) request.getAttribute("department");
 
%>
    
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

    	<button type="submit">Update Department</button>
    </form>