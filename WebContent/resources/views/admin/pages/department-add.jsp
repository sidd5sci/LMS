<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

	<div class="row">
	
		<div class="col-md-3"></div>
		<div class="col-md-6">
				<div class="alert-message"></div>
			    <form action="admin-add-department-db.htm" method="post">
			      	
			    	<div class="form-group">
			    		<label>Department Name *</label>
			    		<input type="text" name="departmentName" class="form-control" id="" placeholder="" required> 
			    	</div>
			    	<div class="form-group">
			    		<label>Department description</label>
			    		<textarea class="form-control" name="departmentDesc"></textarea>
			    	</div>
			    	
			    	<button type="submit" class="form-btn">Add department</button>
			    </form>
			    <div class="note">
			    	All marked fields are mendatry 
			    </div>
		</div>
		<div class="col-md-3"></div>
	</div>
    