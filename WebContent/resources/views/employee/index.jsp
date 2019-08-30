<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    
<%
ServletContext ctx = getServletContext();
String baseUrl = ctx.getInitParameter("url");
String viewPath = ctx.getInitParameter("viewPath");

%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Dashboard</title>
		<link rel="stylesheet" href="<%=baseUrl%>resources/css/grid.css"/>
		<link rel="stylesheet" href="<%=baseUrl%>resources/css/main.css"/>
		<link rel="stylesheet" href="<%=baseUrl%>resources/css/jquery-ui.css"/>
		<link rel="stylesheet" href="<%=baseUrl%>resources/css/admin/dashboard.css"/>
		
	</head>
<body>


	<jsp:include page="includes/header.jsp"/>
	<jsp:include page="includes/sidebar.jsp"/>
		
		<div class="db-main-content">
			<h2 class="display-2"><%=request.getAttribute("pageName")%></h2>
			<jsp:include page="pages/${pageName}.jsp" />
		</div>
	
	<jsp:include page="includes/footer.jsp"/>
</body>
</html>

<script src="<%=baseUrl%>resources/js/jquery.3.4.1.js"></script>
<script src="<%=baseUrl%>resources/js/jquery-ui.js"></script>
<script src="<%=baseUrl%>resources/js/employee/main.js"></script>
<script src="<%=baseUrl%>resources/js/employee/create-leave.js"></script>
<script src="<%=baseUrl%>resources/js/jquery.dataTables.min.js"></script>
<script src="<%=baseUrl%>resources/js/jquery.table2excel.js"></script>


<script>






	$( "#from" ).datepicker({
		changeMonth: true,
	  	numberOfMonths: 1,
	   	minDate:0,
	   	dateFormat: 'dd-mm-yy'
	}).on( "change", function() {
	
	var from = $( "#from" ).val().split("-");
	var fdate = new Date(from[2], from[1] - 1, from[0]);
	fdate.setDate(fdate.getDate()+1);
	var to = $( "#to" );
		to.datepicker( "option", "minDate", fdate );
	var act_val = $.datepicker.formatDate( "dd-mm-yy", fdate);  
		$( "#to" ).val(act_val);
		
		/* total(); */
	});
	$( "#to" ).datepicker({
		changeMonth: true,
	  	numberOfMonths: 1,
	   	minDate:0,
	   	dateFormat: 'dd-mm-yy'
	}).on("change",function(){
	
	/* total();  */
	});



	$('#dataTable').DataTable();
	$("#downloadReport").on("click",function(){
		
		
		$("#dataTable").table2excel({
			    exclude: ".noExl",
			    name: "Report",
			    filename: "Report", //do not include extension
			    fileext: ".xls", // file extension
			    preserveColors: true,
			    exclude_img: true,
			    exclude_links: true,
			    exclude_inputs: true


		});
	});
</script>

