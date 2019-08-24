<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import="java.util.*,com.frapwise.entities.*"%>
	
<%
ServletContext ctx = getServletContext();
String baseUrl = ctx.getInitParameter("url");
String viewPath = ctx.getInitParameter("viewPath");

 List<Object> leavetypes  = (ArrayList)request.getAttribute("leavetypes");
 User user		  = (User)request.getAttribute("user");
 Leave leave = (Leave) request.getAttribute("leave");
 
%>    
    
    <div class="row">
    	<div class="col-md-3"></div>
    	<div class="col-md-6">
    		<div class="alert-message"></div>
    		<form action="employee-edit-leave-db.htm" id="leaveForm" data-type="edit" method="post">
    		
    			<input type="hidden" name="department" value="<%=user.getDepartmentId()%>"/>
    			<input type="hidden" name="user" id="user" value="<%=user.getId()%>"/>
    			<input type="hidden" name="url" id="url" value="<%=baseUrl%>"/>
    			<div class="form-group">
    				<label>Leave type *</label>
    				<select class="form-control" id="leavetype" name="leaveType" required>
    					<option value="0">Select ...</option>
    					<%for(Object o:leavetypes){ LeaveType lt = (LeaveType) o;%>
    						<option value="<%=lt.getId()%>" <% if(leave.getLeaveTypeId() == lt.getId())out.println("selected"); %> ><%=lt.getName()%></option>
    					<%}%>
    				</select>
    			</div>
    			
    			<div class="form-group">
    				<label>Leave from</label>
    				<input type="text" class="form-control" name="from" id="from" value="<%=leave.getLeaveFrom()%>" autocomplete="off" required/>
    			</div>
    			<div class="form-group">
    				<label>Leave to</label>
    				<input type="text" class="form-control" name="to" id="to" value="<%=leave.getLeaveTo()%>" autocomplete="off" required/>
    			</div>
    			<div class="form-group">
    				<label>Time off type</label>
    				<select class="form-control" name="timeOffType" id="timeOff" required>
    					<option value="0" >Select ...</option>
    					<option value="1" <% if(leave.getTimeOffType() == 1)out.println("selected"); %> >Full day</option>
    					<option value="2" <% if(leave.getTimeOffType() == 1)out.println("selected"); %> >Half day</option>
    				</select>
    			</div>
    			<div class="form-group">
    				<label>Duration</label>
    				<input type="number" class="form-control" name="duration" id="duration" required/>
    			</div>
    			<button type="submit" class="form-btn" id="submit" >Update leave request</button>
    		</form>
    	</div>
    	<div class="col-md-3"></div>
    </div>