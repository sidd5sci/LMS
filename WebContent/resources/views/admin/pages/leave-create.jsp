<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import="java.util.*,com.frapwise.entities.*"%>
	
<%
 List<Object> departments = (ArrayList)request.getAttribute("departments");
 List<Object> leavetypes  = (ArrayList)request.getAttribute("leavetypes");
 List<Object> users		  = (ArrayList)request.getAttribute("users");
%>    
    
    <div class="row">
    	<div class="col-md-3"></div>
    	<div class="col-md-6">
    		<form action="admin-add-leave-db.htm" method="post">
    		
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
    				<label>Department *</label>
    				<select class="form-control" name="department" required>
    					<option value="0">Select ...</option>
    					<%for(Object o:departments){ Department dpt = (Department) o;%>
    						<option value="<%=dpt.getId()%>"><%=dpt.getName()%></option>
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
    				<label>Leave from</label>
    				<input type="text" class="form-control" name="from" id="from" autocomplete="off" required/>
    			</div>
    			<div class="form-group">
    				<label>Leave to</label>
    				<input type="text" class="form-control" name="to" id="to" autocomplete="off" required/>
    			</div>
    			<div class="form-group">
    				<label>Time off type</label>
    				<select class="form-control" name="timeOffType" required>
    					<option value="0">Select ...</option>
    					<option value="1">Full day</option>
    					<option value="2">Half day</option>
    				</select>
    			</div>
    			<div class="form-group">
    				<label>Duration</label>
    				<input type="number" class="form-control" name="duration" required/>
    			</div>
    			<button type="submit" class="form-btn">Create leave request</button>
    		</form>
    	</div>
    	<div class="col-md-3"></div>
    </div>