<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import="com.frapwise.entities.*"%>

 <%
 LeaveType dpt = (LeaveType) request.getAttribute("leavetype");
 
%>
    
    <form action="admin-edit-leavetype-db.htm" method="post">
    	<input type="hidden" name="id" value="<%=dpt.getId()%>"/>
    	
    	<div class="form-group">
    		<label>LeaveType name</label>
    		<input type="text" class="form-control" name="leaveTypeName" value="<%=dpt.getName()%>"/> 
    	</div>
    	<div class="form-group">
    		<label>LeaveType description</label>
    		<textarea class="form-control" name="leaveTypeDesc"><%=dpt.getDescription()%></textarea> 
    	</div>

    	<button type="submit">Update LeaveType</button>
    </form>
    
