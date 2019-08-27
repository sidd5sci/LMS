<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

	<div class="row">
		<div class="col-md-3"></div>
		<div class="col-md-6">
		
			<div class="alert-message"></div>
		    <form action="admin-add-leavetype-db.htm" method="post">
		    
		    	<div class="form-group">
		    		<label>LeaveType name *</label>
		    		<input type="text" class="form-control" name="leaveTypeName" id="leaveTypeName" placeholder="name" required/>
		    	</div>
		    	<div class="form-group">
		    		<label>LeaveType default value *</label>
		    		<input type="number" class="form-control" name="leaveTypeDefault" id="leaveTypeDefault" placeholder="default value" required/>
		    	</div>
		    	<div class="form-group">
		    		<label>LeaveType period *</label>
		    		<input type="number" class="form-control" name="leaveTypePeriod" id="leaveTypePeriod" placeholder="months" required/>
		    	</div>
		    	<div class="form-group">
		    		<label>LeaveType description</label>
		    		<textarea class="form-control" name="leaveTypeDesc" id="leaveTypeDesc" placeholder="Enter description"></textarea>
		    	</div>
		    	
		    	<button type="submit" class="form-btn">Add leaveType</button>
		    	
		    </form>
		    
		</div>
		<div class="col-md-3"></div>
		
	</div>
	