<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
        import="java.util.*,com.frapwise.entities.*,com.frapwise.utils.*,com.frapwise.models.*"%>

    
<%
ServletContext ctx = getServletContext();
String baseUrl = ctx.getInitParameter("url");
String viewPath = ctx.getInitParameter("viewPath");

%>



<button id="downloadReport" class="form-btn"> Download Report</button>
    <div class="dataTables">
    
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
    
    
