<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
        import="java.util.*,com.frapwise.entities.*"%>

    
<%
ServletContext ctx = getServletContext();
String baseUrl = ctx.getInitParameter("url");
String viewPath = ctx.getInitParameter("viewPath");

%>
    <div class="data-tables">
    
    <table id="dataTable">
    
    	<thead>
    		<th>Id</th>
    		<th>Name</th>
    		<th>Description</th>
    		<th>Action</th>
    	</thead>
    	<tbody>
    	
    		<% 
	    		List<Object> records = (ArrayList)request.getAttribute("leavetypes");
				for(Object d : records){
						LeaveType dpt = (LeaveType)d;
						out.println("<tr>");
							out.println("<td>"+dpt.getId()+"</td>");
							out.println("<td>"+dpt.getName()+"</td>");
							out.println("<td>"+dpt.getDescription()+"</td>"); 
							out.println("<td>"
											+"<a href=\""+baseUrl+"admin-edit-leavetype.htm?id="+dpt.getId()+"\">Edit</a>"  
											+"<a href=\""+baseUrl+"admin-remove-leavetype.htm?id="+dpt.getId()+"\">Remove</a>"
										+"</td>");
						out.println("</tr>");
				}
    		
    		%>
    	
    	</tbody>
    
    
    </table>
    
    </div>