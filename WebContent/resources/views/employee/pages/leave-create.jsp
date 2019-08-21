<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import="java.util.*,com.frapwise.entities.*"%>
	
<%
 
 List<Object> leavetypes  = (ArrayList)request.getAttribute("leavetypes");
 User user		  = (User)request.getAttribute("user");
%>    
    
    <div class="row">
    	<div class="col-md-3"></div>
    	<div class="col-md-6">
    		<form action="employee-add-leave-db.htm" method="post">
    		
    			<input type="hidden" name="department" value="<%=user.getDepartmentId()%>"/>
    			<input type="hidden" name="user" value="<%=user.getId()%>"/>
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
    			<button type="submit">Create leave request</button>
    		</form>
    	</div>
    	<div class="col-md-3"></div>
    </div>