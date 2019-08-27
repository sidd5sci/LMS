<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import="com.frapwise.entities.*"%>

 <%
 LeaveType dpt = (LeaveType) request.getAttribute("leavetype");
 
%>
    
    <div class="row">
    
    	<div class="col-md-3"></div>
    	<div class="col-md-6">
    		<div class="alert-message"></div>
	    	<form action="admin-edit-leavetype-db.htm" method="post">
		    	<input type="hidden" name="id" value="<%=dpt.getId()%>"/>
		    	
		    	<div class="form-group">
		    		<label>LeaveType name</label>
		    		<input type="text" class="form-control" name="leaveTypeName" value="<%=dpt.getName()%>"/> 
		    	</div>
		    	<div class="form-group">
		    		<label>LeaveType default value *</label>
		    		<input type="number" class="form-control" name="leaveTypeDefault" value="<%=dpt.getDefaultValue()%>" id="leaveTypeDefault" placeholder="default value" required/>
		    	</div>
		    	<div class="form-group">
		    		<label>LeaveType period *</label>
		    		<input type="number" class="form-control" name="leaveTypePeriod" value="<%=dpt.getPeriod()%>" id="leaveTypePeriod" placeholder="months" required/>
		    	</div>
		    	<div class="form-group">
		    		<label>LeaveType description</label>
		    		<textarea class="form-control" name="leaveTypeDesc"><%=dpt.getDescription()%></textarea> 
		    	</div>
		
		    	<button type="submit" class="form-btn">Update LeaveType</button>
		    </form>
		    
    	</div>
    	<div class="col-md-3"></div>
    </div>
    
    
