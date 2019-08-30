<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import="com.frapwise.entities.*,java.util.*,com.frapwise.utils.*"%>
<%
 
%>
    
	<c:set var="u" value="${user}"/>
    <div class="row">
    
    	<div class="col-md-3"></div>
    	<div class="col-md-6">
    		<div class="alert-message"></div>
    		<form action="admin-edit-employee-db.htm" method="post">
    		
    			<div class="form-group">
		    		<label>Employee first name *</label>
		    		<input type="text" name="fname" class="form-control" value="${u.getFname()}" id="" placeholder="Enter first name" required> 
		    	</div>
		    	<div class="form-group">
		    		<label>Employee last name *</label>
		    		<input type="text" name="lname" class="form-control" id="" value="${u.getLname()}" placeholder="Enter last name" required> 
		    	</div>
		    	<div class="form-group">
		    		<label>Employee username *</label>
		    		<input type="text" name="username" class="form-control" id="" value="${u.getUsername()}" placeholder="username" required> 
		    	</div>
		    	<div class="form-group">
		    		<label>Employee email *</label>
		    		<input type="email" name="email" class="form-control" id="" value="${u.getEmail()}" placeholder="Email" required> 
		    	</div>
		    	<div class="form-group">
		    		<label>Employee password *</label>
		    		<input type="password" name="password" class="form-control" id="" value="${AES_Cipher.getDecrypted(u.getPassword())}" placeholder="password" required> 
		    	</div>
		    	<div class="form-group">
		    		<label>Home office *</label>
		    		<input type="text" name="homeOffice" class="form-control" id="" value="${u.getHomeOffice()}" placeholder="Office name" required> 
		    	</div>
		    	<div class="form-group">
		    		<label>Department *</label>
		    		
		    		<select name="department" class="form-control" id="department" required>
		    			<option value="0">Select ...</option>
		    			<c:forEach var="d" items="${departments}">
							<option value="${d.getId()}" <c:if test="${u.getDepartmentId() == d.getId()}" ><c:out value="selected"/></c:if>> 
								<c:out value="${d.getName()}"/>
							</option>
						</c:forEach>
		    		</select>
		    		
		    	</div>
		    	<div class="form-group">
		    		<label>Employee role *</label>
		    		<select name="role" class="form-control" id="role" required>
		    			<option value="Employee" <c:if test="${u.getRole() == 'Employee'}" ><c:out value="selected"/></c:if> >Employee</option>
		    			<option value="Admin" <c:if test="${u.getRole() == 'Admin'}" ><c:out value="selected"/></c:if> >Admin</option> 
		    		</select>
		    		
		    	</div>
		    	<input type="hidden" name="status" value="enabled"/>
		    	<input type="hidden" name="id" value="${u.getId()}"/>
		    	<button type="submit" class="form-btn">Update employee</button>
    		</form>
    	
    	</div>
    	<div class="col-md-3"></div>    	
    
    </div>