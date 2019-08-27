<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
        import="java.util.*,com.frapwise.entities.*,
        com.frapwise.vo.*,com.frapwise.utils.*"%>

    
<%
ServletContext ctx = getServletContext();
String baseUrl = ctx.getInitParameter("url");
String viewPath = ctx.getInitParameter("viewPath");

List<ReportVO> report = (ArrayList) request.getAttribute("report");

%>

<a href="apply-file.api?filename=${filename}" class="form-btn"> Apply Report</a>
<a href="<%=baseUrl%>/admin-dashboard.htm" class="form-btn danger">Cancel</a>


<div class="dataTables">
	
    
    <table id="dataTable" class="paper-4">
    
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
	    		
	    	</tr>
    	</thead>
    	<tbody>
    	
    		<% 
			for(int i = 1;i<report.size();i++){
				ReportVO r = report.get(i);
			
					
			%>
				<tr>
						<td><%=r.getLeaveRequest()%></td>
						<td><%=r.getEmployeeName()%></td>
						<td><%=r.getHomeOffice()%></td>
						<td><%=r.getLeaveType()%></td>
						<td><%=r.getDepartmentCode()%></td>
						<td><%=r.getTypeOfTimeOfF()%></td>
						<td><%=r.getDates()%></td>
						<td><%=r.getCreatedBy() %></td>
						<td><%=r.getCreatedWhen()%></td>
						<td><%=r.getApprovalStatus()%></td>
						<td><%=r.getDuration()%></td>
						
				</tr>
			<%}%>
    	
    	</tbody>
    
    
    </table>
    
    </div>