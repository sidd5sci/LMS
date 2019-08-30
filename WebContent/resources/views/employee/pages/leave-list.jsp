<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
        import="java.util.*,com.frapwise.entities.*,com.frapwise.models.*,com.frapwise.utils.*"%>

    
<%
ServletContext ctx = getServletContext();
String baseUrl = ctx.getInitParameter("url");
String viewPath = ctx.getInitParameter("viewPath");
%>


<div class="filter-box">

	<form action="employee-appy-filter.htm" method="post">
		<label>From</label>
		<input type="text" name="from" id="from" autocomplete="off">
		<label>To</label>
		<input type="text" name="to" id="to" autocomplete="off">	
		<label>Status</label>
		<select name="status">
			<option value="pending">pending</option>
			<option value="approved">approved</option>
		</select>
		<button type="submit" class="form-btn">Apply filter</button>
	</form>
</div>

    <div class="data-tables">
    <button id="downloadReport" class="form-btn"> Download Report</button>
    <table id="dataTable">
    
    	<thead>
    		<tr>
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
	    	</tr>
    	</thead>
    	<tbody>
    	
    		<% 
    		List<Leave> records = (ArrayList)request.getAttribute("leaves");
    		UserModel userModel = new UserModel();
    		User u = new User();
    		LeaveTypeModel ltModel = new LeaveTypeModel();
    		LeaveType lt = new LeaveType();
    		DepartmentModel dptModel = new DepartmentModel();
    		Department dpt1 = new Department();
    		
			for(Leave dpt : records){
					
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
						<td><%=Util.getDaysNoWeekends(dpt.getLeaveFrom(), dpt.getLeaveTo())%></td>
						<td>
							<% if(dpt.getStatus().equals("pending")){ %>
								<a href="<%=baseUrl%>employee-edit-leave.htm?id=<%=dpt.getId()%>">Edit</a> 
								<a href="<%=baseUrl%>employee-remove-leave.htm?id=<%=dpt.getId()%>">Cancel</a>
							<%} %>
							
						</td>
				</tr>
			<%}%>
    	
    	</tbody>
    
    
    </table>
    
    </div>