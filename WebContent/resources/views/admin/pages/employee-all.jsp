<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
        import="java.util.*,com.frapwise.entities.*,com.frapwise.models.*"%>

    
<%
ServletContext ctx = getServletContext();
String baseUrl = ctx.getInitParameter("url");
String viewPath = ctx.getInitParameter("viewPath");
DepartmentModel dptModel = new DepartmentModel();

%>
    <div class="data-tables">
    
    <table id="dataTable">
    
    	<thead>
    		<tr>
	    		<th>Id</th>
	    		<th>Name</th>
	    		<th>Username</th>
	    		<th>Home office</th>
	    		<th>Email</th>
	    		<th>Role</th>
	    		<th>department</th>
	    		<th>Action</th>
	    	</tr>
    	</thead>
    	<tbody>
    	
    		<% 
    		List<Object> records = (ArrayList)request.getAttribute("users");
			for(Object d : records){
					User dpt = (User)d;
					Department d1 = dptModel.getDepartmentById(dpt.getDepartmentId());
					
					out.println("<tr>");
						out.println("<td>"+dpt.getId()+"</td>");
						out.println("<td>"+dpt.getFname()+" "+dpt.getLname()+"</td>");
						out.println("<td>"+dpt.getUsername()+"</td>");
						out.println("<td>"+dpt.getHomeOffice()+"</td>");
						out.println("<td>"+dpt.getEmail()+"</td>"); 
						out.println("<td>"+dpt.getRole()+"</td>");
						out.println("<td>"+d1.getName()+"</td>");
						out.println("<td>"
										+"<a href=\""+baseUrl+"admin-edit-employee.htm?id="+dpt.getId()+"\">Edit</a>"  
										+"<a href=\""+baseUrl+"admin-remove-employee.htm?id="+dpt.getId()+"\">Remove</a>"
									+"</td>");
					out.println("</tr>");
			}
    		
    		%>
    	
    	</tbody>
    
    
    </table>
    
    </div>