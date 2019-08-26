<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import="java.util.*,com.frapwise.entities.*,com.frapwise.utils.*"%>
<%
ServletContext ctx = getServletContext();
String baseUrl = ctx.getInitParameter("url");
String viewPath = ctx.getInitParameter("viewPath");


List<Object> leavetypes  = (ArrayList)request.getAttribute("leavetypes");
List<Object> users		  = (ArrayList)request.getAttribute("users");
UserLeaveMapper ulm = (UserLeaveMapper) request.getAttribute("leavemap");


%>
    <div class="row">
    
    
    	<div class="col-md-3"></div>
    	<div class="col-md-6">
    		<div class="alert-message"></div>
    		<form action="<%=baseUrl%>admin-edit-max-leave-db.htm" method="post">
    		
    			<input type="hidden" name="id" id="ulmId" value="<%=ulm.getId()%>"/>
    			<input type="hidden" name="leaveType" id="" value="<%=ulm.getLeaveTypeId()%>"/>
    			<input type="hidden" name="user" id="" value="<%=ulm.getUid()%>"/>
    			<div class="form-group">
    				<label>Leave type *</label>
    				  
   					<%for(Object o:leavetypes){ LeaveType lt = (LeaveType) o;%>
   						<%if(ulm.getLeaveTypeId() == lt.getId()){%>
   							<input type="text" class="form-control"  value="<%=lt.getName()%>" disabled/>
   						<%} %>
   					<%}%> 
    			
    			</div>
    			<div class="form-group">
    				<label>Employee</label>
    				
    				<%for(Object o:users){ User u = (User) o;%>
   						<%if(ulm.getUid() == u.getId()){%>
   							<input type="text" class="form-control"  value="<%=u.getFname()+" "+u.getLname()%>" disabled/>
   						<%} %>
   					<%}%> 
    			</div>
    			<div class="form-group">
    				<label>Maximum leaves</label>
    				<input type="number" class="form-control" name="max" id="max" value="<%=ulm.getLeaveMax()%>" autocomplete="off" required/>
    			</div>
    			<div class="form-group">
    				<label>Valid from</label>
    				<input type="text" class="form-control" name="from" id="assignFrom" value="<%=Util.sqlToJquery(ulm.getLeaveFrom())%>" autocomplete="off" required/>
    			</div>
    			<div class="form-group">
    				<label>Valid to</label>
    				<input type="text" class="form-control" name="to" id="assignTo" value="<%=Util.sqlToJquery(ulm.getLeaveTo())%>" autocomplete="off" required/>
    			</div>
    			
    			<button type="submit" class="form-btn">Update</button>
    		</form>
    	
    	</div>
    	<div class="col-md-3"></div>
    </div>