
$(document).ready(function(){
	
	/*Leave Type change action*/
	$("#leavetype").on("change",function(){
		
		let lid = $(this).val();
		console.log(lid);
		let url = $("#url").val();
		let uid = $("#user").val();
		$.get(url+"get-availible-leaves-by-user-leavetype.api?uid="+uid+"&leavetype="+lid,function(result){
			
			let alert = $(".alert-message");
			if(result != ''){
				console.log(result);
				
				if(parseInt(result) > 0 )
				{
					alert.html("You have "+result+" leave availible");
					alert.css("display","block");
					$("#submit").attr("disabled",false).removeClass("disabled");
				}else{
					$("#submit").attr("disabled",true).addClass("disabled");
					
				}
				
			}
			else{
				alert.html("You have 0 leave availible");
				alert.css("display","block");
				$("#submit").attr("disabled",true).addClass("disabled");
			}
		}); 
		total();
	});
	
	$("#leaveForm").submit(function(e){
			
		
		let leaveType = $("#leavetype").val();
		let form = $("#from").val();
		let to = $("#to").val();
		let timeOff = $("#timeOff").val();
		let duartion = $("#duration").val();
		let alert = $(".alert-message");
		let type = $(this).attr("data-type");
		
		let flag = 0;
		if(leaveType <= 0 || leaveType == undefined){
				
			alert.html("Leave type is not selected !!");
			alert.addClass("danger");
			alert.show();
			flag = 1;
			
		}
		if(form == '' || leaveType == undefined){
			alert.html("From date is not selected !!");
			alert.addClass("danger");
			alert.show();
			flag = 1;
			
		}
		if(to == '' || leaveType == undefined){
			alert.html("To date is not selected !!");
			alert.addClass("danger");
			alert.show();
			flag = 1;
		}
		if(timeOff <= 0 || leaveType == undefined){
			
			alert.html("Time Off type is not selected !!");
			alert.addClass("danger");
			alert.show();
			flag = 1;
		}
		if(ValidateLeave() == 0 && type == "create"){
			alert.html("You already have leave from these dates !!");
			alert.addClass("danger");
			alert.show();
			flag = 1;
		}
		if(duration <= 0){
			alert.html("Duration is not selected !!");
			alert.addClass("danger");
			alert.show();
			flag = 1;
		}
		console.log("Flag:",flag);
		if(flag == 0){
			alert.removeClass("danger");
			return true;
		}
		else{
			e.preventDefault();
			return false;
		}
		
		
		
  	});
	function ValidateLeave(){
	  	let url = $("#url").val();
		let uid = $("#user").val();
		
		var from = $("#from").val().split("-");
		var fdate = from[2]+'-'+from[1]+'-'+from[0] ;
		var to = $("#to").val().split("-");
		var tdate = to[2]+'-'+to[1]+'-'+to[0] ;
		let flag = 0;
    	$.get(url+"get-leave-status-user-date.api?uid="+uid+"&from="+fdate+"&to="+tdate,function(result){
			
			let alert = $(".alert-message");
			if(result != ''){
				console.log(result);
				flag = result;
			}
		});
    	return flag;
	
	}
	
	
	
	$(".db-list-item").hover(function(){
	
		let menu = $(this).children("ul");
		//menu.removeClass("db-submenu-dropdown");
		if(menu != undefined){
			let menuBar = $(".db-sub-menu");
			menuBar.html("");
			menuBar.show();
			menuBar.append(menu.clone());
			menuBar.children("ul").removeClass("db-submenu-dropdown");
			menuBar.animate({
			    left: '120px'
			  },"slow");
		}
		
		
	});
	
	$("body").on("click",function(){
		let menuBar = $(".db-sub-menu");
		
		menuBar.animate({
			left:'-60px'
		},"slow");
		menuBar.html("");
	});
	
	
	

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
	
	 total();  
	});
	
	$("#timeOff").on("change",function(){
		total();
	});
	
	function total(){
	    var start= $("#from").datepicker("getDate");
	    var end= $("#to").datepicker("getDate");
	    days = (end- start) / (1000 * 60 * 60 * 24);
	    var base = $("#timeOff").val();
	    if(days < 1){
	    	
	    	$("#duration").attr("value",base==1 ? 0*1 : 0*0.5);
			
		}
	    else{
	    	$("#duration").attr("value",base==1 ? days*1:days*0.5);
		}
	    console.log("Base:",base);
	}
	
	
	/*LEAVE ASSIGNMENT*/
	
	$( "#assignFrom" ).datepicker({
		changeMonth: true,
	  	numberOfMonths: 1,
	   	dateFormat: 'dd-mm-yy'
	}).on( "change", function() {
	
	var from = $( "#assignFrom" ).val().split("-");
	var fdate = new Date(from[2], from[1] - 1, from[0]);
	fdate.setDate(fdate.getDate()+30);
	var to = $( "#assignTo" );
		to.datepicker( "option", "minDate", fdate );
	var act_val = $.datepicker.formatDate( "dd-mm-yy", fdate);  
		$( "#assignTo" ).val(act_val);
		
		/* total(); */
	});
	$( "#assignTo" ).datepicker({
		changeMonth: true,
	  	numberOfMonths: 1,
	   	minDate:0,
	   	dateFormat: 'dd-mm-yy'
	}).on("change",function(){
	
	/* total();  */
	});
	
	
	/*DATATABLES */
	$('#dataTable').DataTable();
	
	/*TABLE2EXCEL*/
	$("#downloadReport").on("click",function(){
				
		$("#dataTable").table2excel({
			    exclude: ".noExl",
			    name: "Report",
			    filename: "Leave-Report", //do not include extension
			    fileext: ".xls", // file extension
			    preserveColors: true,
			    exclude_img: true,
			    exclude_links: true,
			    exclude_inputs: true


		});
	});
	
});