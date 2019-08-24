<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import="java.util.*,com.frapwise.entities.*"%>
<%
ServletContext ctx = getServletContext();
String baseUrl = ctx.getInitParameter("url");
String viewPath = ctx.getInitParameter("viewPath");

List<Object> departments = (ArrayList)request.getAttribute("departments");
List<Object> leavetypes  = (ArrayList)request.getAttribute("leavetypes");
List<Object> users		  = (ArrayList)request.getAttribute("users");
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
    					<%for(Object o:leavetypes){ LeaveType lt = (LeaveType) o;%>
    						<option value="<%=lt.getId()%>"><%=lt.getName()%></option>
    					<%}%>
    				</select>
    			</div>
    			<div class="form-group">
    				<label>Employee</label>
    				<select class="form-control" name="user" required>
    					<option value="0">Select ...</option>
    					<%for(Object o:users){ User u = (User) o;%>
    						<option value="<%=u.getId()%>"><%=u.getUsername()%></option>
    					<%}%>
    				</select>
    			</div>
    			<div class="form-group">
    				<label>Maximum leaves</label>
    				<input type="number" class="form-control" name="max" id="max" autocomplete="off" required/>
    			</div>
    			<div class="form-group">
    				<label>Valid from</label>
    				<input type="text" class="form-control" name="from" id="assignFrom" autocomplete="off" required/>
    			</div>
    			<div class="form-group">
    				<label>Valid to</label>
    				<input type="text" class="form-control" name="to" id="assignTo" autocomplete="off" required/>
    			</div>
    			
    			<button type="submit" class="form-btn">Submit</button>
    		</form>
    	
    	</div>
    	<div class="col-md-3"></div>
    </div>