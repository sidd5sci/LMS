<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="java.util.*,com.frapwise.entities.*,com.frapwise.models.*"%>

<%
ServletContext ctx = getServletContext();
String baseUrl = ctx.getInitParameter("url");
String viewPath = ctx.getInitParameter("viewPath");

%>


<div class="dataTable">
<button id="downloadReport" class="form-btn"> Download Report</button>
	<table id="dataTable">

		<thead>
			<th>Leave Type</th>
			<th>Leave Max</th>
			<th>Leave Taken</th>
			<th>Leave Availible</th>
			<th>Duration</th>
			
		</thead>

		<tbody>
			<%
			LeaveTypeModel ltModel = new LeaveTypeModel();
			List<UserLeaveMapper> userLeaveMapper = (ArrayList) request.getAttribute("leaveMapper");
			
			for(UserLeaveMapper lm:userLeaveMapper){
				LeaveType lt = ltModel.getLeaveTypeById(lm.getLeaveTypeId());
			%>
			<tr>
				<td><%=lt.getName()%></td>
				<td><%=lm.getLeaveMax()%></td>
				<td><%=lm.getLeaveTaken()%></td>
				<td><%=lm.getLeaveAvailible()%></td>
				<td><%=lm.getLeaveFrom()%> - <%=lm.getLeaveTo()%></td>
			</tr>
			<%} %>
		</tbody>
	</table>
</div>