<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
        import="java.util.*,com.frapwise.entities.*,com.frapwise.models.*,com.frapwise.utils.*"%>

    
<%
ServletContext ctx = getServletContext();
String baseUrl = ctx.getInitParameter("url");
String viewPath = ctx.getInitParameter("viewPath");

List<Object> users = (ArrayList) request.getAttribute("users");

%>


<div class="filter-box">
	<form action="admin-apply-filter.htm" method="post">
		<label>From</label>
		<input type="text" name="from" id="from" autocomplete="off">
		<label>To</label>
		<input type="text" name="to" id="to" autocomplete="off">	
		<label>Status</label>
		<select name="status">
			<option value="">Select ...</option>
			<option value="rejected">Rejected</option>
			<option value="pending">Pending</option>
			<option value="approved">Approved</option>
		</select>
		<label>Employee</label>
		<select name="uid">
			<option value="0">Select ... </option>		
			<%for(Object o :users){ User u = (User)o;%>
				<option value="<%=u.getId()%>"><%=u.getFname()+" "+u.getLname()%></option>
			<%} %>
		</select>
		<button type="submit" class="form-btn">Apply filter</button>
		
	</form>
</div>

<div class="dataTables">
	<button id="downloadReport" class="form-btn"> Download Report</button>
    
    <table id="dataTable">
    
    	<thead>
    		<th>Leave Request</th>
    		<th>Employee name</th>
    		<th>Home office</th>
    		<th>Leave Type</th>
    		<th>Department code</th>
    		<th>Type of Time Off</th>
    		<th>Dates</th>
    		<th>Created by</th>
    		<th>Created when</th>
    		<th>Approval status</th>
    		<th>Duration</th>
    		<th>Action</th>
    	</thead>
    	<tbody>
    	
    		<% 
    		List<Object> records = (ArrayList)request.getAttribute("leaves");
    		UserModel userModel = new UserModel();
    		User u = new User();
    		LeaveTypeModel ltModel = new LeaveTypeModel();
    		LeaveType lt = new LeaveType();
    		DepartmentModel dptModel = new DepartmentModel();
    		Department dpt1 = new Department();
    		
			for(Object d : records){
					Leave dpt = (Leave)d;
					u = userModel.getUserById(dpt.getUserId());
					lt = ltModel.getLeaveTypeById(dpt.getLeaveTypeId());
					dpt1 = dptModel.getDepartmentById(dpt.getDepartmentId());
			%>
				<tr>
						<td><%=u.getFname()%> - <%=dpt.getLeaveFrom()%> - <%=dpt.getLeaveTo()%></td>
						<td><%=u.getFname()%></td>
						<td><%=u.getHomeOffice()%></td>
						<td><%=lt.getName()%></td>
						<td><%=dpt1.getName()%></td>
						<td><% if(dpt.getTimeOffType() == 1) out.println("Full day"); else out.println("Half day");%></td>
						<td><%=dpt.getLeaveFrom()%> - <%=dpt.getLeaveTo()%></td>
						<td><%=u.getFname() %></td>
						<td><%=dpt.getAppliedDate()%></td>
						<td><%=dpt.getStatus()%></td>
						<td><%=Util.getDaysNoWeekends(dpt.getLeaveFrom(),dpt.getLeaveTo())%></td>
						<td>
							<%if(dpt.getStatus().equals("pending")){ %>
								<!-- <a href="<%=baseUrl%>admin-edit-leave.htm?id=<%=dpt.getId()%>">Edit</a> -->
								<a href="<%=baseUrl%>admin-reject-leave.htm?id=<%=dpt.getId()%>">Reject</a>
								<a href="<%=baseUrl%>admin-leave-approve.htm?id=<%=dpt.getId()%>">Approve</a>
							<%} %>
						</td>
				</tr>
			<%}%>
    	
    	</tbody>
    
    
    </table>
    
    </div>