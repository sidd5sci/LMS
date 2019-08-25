<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="java.util.*,com.frapwise.entities.*,com.frapwise.models.*"%>

<%
ServletContext ctx = getServletContext();
String baseUrl = ctx.getInitParameter("url");
String viewPath = ctx.getInitParameter("viewPath");

%>

<button id="downloadReport" class="form-btn"> Download Report</button>
<div class="data-tables">

	<table id="dataTable">

		<thead>
			<th>Employee</th>
			<th>Leave Type</th>
			<th>Leave Max</th>
			<th>Leave Taken</th>
			<th>Leave Availible</th>
			<th>Duration</th>
			<th>Action</th>
		</thead>

		<tbody>
			<%
			LeaveTypeModel ltModel = new LeaveTypeModel();
			List<UserLeaveMapper> userLeaveMapper = (ArrayList) request.getAttribute("leaveMapper");
			UserModel userModel = new UserModel();
			for(UserLeaveMapper lm:userLeaveMapper){
				LeaveType lt = ltModel.getLeaveTypeById(lm.getLeaveTypeId());
				User u = userModel.getUserById(lm.getUid());
			%>
			<tr>
				<td><%=u.getFname()+" "+u.getLname()%> </td>
				<td><%=lt.getName()%></td>
				<td><%=lm.getLeaveMax()%></td>
				<td><%=lm.getLeaveTaken()%></td>
				<td><%=lm.getLeaveAvailible()%></td>
				<td><%=lm.getLeaveFrom()%> - <%=lm.getLeaveTo()%></td>
				<td>
					<a href="<%=baseUrl%>admin-edit-max-leave.htm?uid=<%=lm.getUid()%>&leaveType=<%=lm.getLeaveTypeId()%>" >edit</a>
				</td>
			</tr>
			<%} %>
		</tbody>
	</table>
</div>