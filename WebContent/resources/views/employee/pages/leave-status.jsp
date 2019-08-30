<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
			<th>Leave Taken</th>
			<th>Leave Availible</th>
		</thead>

		<tbody>
			
			<c:forEach var="lm" items="${leaveMapper}">
				<tr>
					<td>
						<c:forEach var="l" items="${leavetypes}">
							<c:if test="${l.getId() == lm.getLeaveTypeId()}" >
								<c:out value="${l.getName()}"/>
							</c:if>
						</c:forEach>
					</td>
					<td><c:out value="${lm.getLeaveTaken()}"/></td>
					<td><c:out value="${lm.getLeaveAvailible()}"/></td>
				</tr>
			</c:forEach>
			
			
		</tbody>
	</table>
</div>