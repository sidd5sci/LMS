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
    		<form action="admin-add-employee-db.htm" method="post">
    			
    			<input type="hidden" name="url" id="url" value="<%=baseUrl%>"/>
    			<div class="form-group">
		    		<label>Employee first name *</label>
		    		<input type="text" name="fname" class="form-control" id="fname" placeholder="Enter first name" required> 
		    	</div>
		    	<div class="form-group">
		    		<label>Employee last name *</label>
		    		<input type="text" name="lname" class="form-control" id="lname" placeholder="Enter last name" required> 
		    	</div>
		    	<div class="form-group">
		    		<label>Employee username *</label>
		    		<input type="text" name="username" class="form-control" id="username" autocomplete="off" placeholder="username" required> 
		    	</div>
		    	<div class="form-group">
		    		<label>Employee email *</label>
		    		<input type="email" name="email" class="form-control" id="email" placeholder="Email" required> 
		    	</div>
		    	<div class="form-group">
		    		<label>Employee password *</label>
		    		<input type="password" name="password" class="form-control" id="" placeholder="password" required> 
		    	</div>
		    	<div class="form-group">
		    		<label>Home office *</label>
		    		<input type="text" name="homeOffice" class="form-control" id="" placeholder="Office name" required> 
		    	</div>
		    	<div class="form-group">
		    		<label>Department *</label>
		    		
		    		<select name="department" class="form-control" id="department" required>
		    			<option value="0">Select ...</option>
		    			<% 
		    				List<Object> departments = (ArrayList) request.getAttribute("departments");
		    				
		    				for(Object o : departments){
		    					Department d = (Department) o;
		    					out.println("<option value=\""+d.getId()+"\">"+d.getName()+"</option>");
		    				}
		    			%>
		    			
		    		</select>
		    		
		    	</div>
		    	<div class="form-group">
		    		<label>Employee role *</label>
		    		<select name="role" class="form-control" id="role" required>
		    			<option value="Employee">Employee</option>
		    			<option value="Admin">Admin</option>
		    		</select>
		    		
		    	</div>
		    	<input type="hidden" name="status" value="enabled"/>
		    	<button type="submit" class="form-btn">Add employee</button>
    		</form>
    	
    	</div>
    	<div class="col-md-3"></div>    	
    
    </div>