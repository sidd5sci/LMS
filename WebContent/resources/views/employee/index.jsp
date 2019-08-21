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
			<%=request.getAttribute("pageName")%>
			<jsp:include page="pages/${pageName}.jsp" />
		</div>
	
	<jsp:include page="includes/footer.jsp"/>
</body>
</html>

<script src="<%=baseUrl%>resources/js/jquery.3.4.1.js"></script>
<script src="<%=baseUrl%>resources/js/jquery-ui.js"></script>
<script src="<%=baseUrl%>resources/js/admin/main.js"></script>
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




</script>

