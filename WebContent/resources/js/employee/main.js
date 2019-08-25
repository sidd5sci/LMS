$(document).ready(function(){
	
	
	/*Sub menu handler or populator*/
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
	/*hide sub menu*/
	$("body").on("click",function(){
		let menuBar = $(".db-sub-menu");
		menuBar.animate({
			left:'-60px'
			},"slow");
		menuBar.html("");
	});
	
	
	
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
					alert.html("You have 0 leave availible");
					alert.css("display","block");
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
	
	/*handle change in from date*/
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
			
			 total(); 
	});
	
	/*handle change in the to date*/
	$( "#to" ).datepicker({
			changeMonth: true,
		  	numberOfMonths: 1,
		   	minDate:0,
		   	dateFormat: 'dd-mm-yy'
		}).on("change",function(){
	
			total();  
		
	});
	
	/*handle the change in time Off type*/
	$("#timeOff").on("change",function(){
		total();
	});
		
	
	
	
	/*calculate the diffrence between dates */
	function total(){
	    var start= $("#from").datepicker("getDate");
	    var end= $("#to").datepicker("getDate");
	    days = (end- start) / (1000 * 60 * 60 * 24) + 1;
	    
	    let i=0,daysTotal = days;
	    var temp = new Date();
	    for(i=0;i<days;i++){
	    	temp.setDate(start.getDate() + i);
	    	var d = temp.getDay();
	    	if(d == 0 || d == 6){
	    		daysTotal--;
	    	}
	    }
	    console.log("Days:",days,"-",daysTotal);
	    var base = $("#timeOff").val();
	    if(daysTotal < 1){
	    	$("#duration").attr("value",base==1 ? 0*1 : 0*0.5);
		}
	    else{
	    	$("#duration").attr("value",base==1 ? daysTotal*1:daysTotal*0.5);
		}
	    
	}
	
	/*validate the leave if not exisit already*/
	$("#leaveForm").on("submit",function(e){
		e.preventDefault();
		 ValidateLeave(e);
	});
	
	/*Leave form validation*/
	function ValidateLeave(e){
		  	let url = $("#url").val();
    		let uid = $("#user").val();
    		var from = $("#from").val().split("-");
    		var fdate = from[2]+'-'+from[1]+'-'+from[0] ;
    		var to = $("#to").val().split("-");
    		var tdate = to[2]+'-'+to[1]+'-'+to[0] ;
    		let valid = 0;
        	$.get(url+"get-leave-status-user-date.api?uid="+uid+"&from="+fdate+"&to="+tdate,{ async: false },function(result){
    			
    			let alert = $(".alert-message");
    			if(result != ''){
    				console.log("res:",result);
    				valid = result;
    			}  			
    			 
					
				let leaveType = $("#leavetype").val();
				let form = $("#from").val();
				let to = $("#to").val();
				let timeOff = $("#timeOff").val();
				let duration = $("#duration").val();
				let department = $("#department").val();
				alert = $(".alert-message");
				 
				let type = $("#leaveForm").attr("data-type");
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
				
				if( (valid == 0 || valid == 2 || valid == 3) && type == "create"){
					if(valid == 0)
						alert.html("You already have leave start from this date !!");
					else if(valid == 2) 
						alert.html("You already have leave end to this date !!");
					else if(valid == 3)
						alert.html("You date are conflicting with previous leaves !!");
					
					alert.addClass("danger");
					alert.show();
					flag = 1;
				}	
				
				// send form by ajax
				if(flag == 0){
					alert.removeClass("danger");
					
					console.log("user:",uid,
							"leaveType:",leaveType,
							"department:",department,
							"from:",from,
							"to:",to,
							"timeOffType:",timeOff,
							"duration:",duration
							);
					if(type == 'create'){
						
						$.post(url+"employee-add-leave-db.htm",{
							user:uid,
							leaveType:leaveType,
							department:department,
							from:$("#from").val(),
							to:to,
							timeOffType:timeOff,
							duration:duration,
							},function(result){
								
								console.log("final:",result);
								if(result != "" || result != "0"){
									window.location.href=url+'employee-all-leaves.htm';
								}
																      
						});
					}else if(type == 'edit'){
						$.post(url+"employee-edit-leave-db.htm",{
							user:uid,
							leaveType:leaveType,
							department:department,
							from:$("#from").val(),
							to:to,
							timeOffType:timeOff,
							duration:duration,
							id:$("#lid").val(),
							},function(result){
								
								console.log("final:",result);
								if(result != "" || result != "0"){
									window.location.href=url+'employee-all-leaves.htm';
								}
     
						});
					}
					  
					
					
				}
				

    			
    		});
        	
		
	}// end validation
	
	
	
	/*message cookie handler*/
	function getCookie(cname) {
		  var name = cname + "=";
		  var decodedCookie = decodeURIComponent(document.cookie);
		  var ca = decodedCookie.split(';');
		  for(var i = 0; i <ca.length; i++) {
		    var c = ca[i];
		    while (c.charAt(0) == ' ') {
		      c = c.substring(1);
		    }
		    if (c.indexOf(name) == 0) {
		      return c.substring(name.length, c.length);
		    }
		  }
		  return "";
	}
	
	let message = getCookie("message");	
	console.log("message:",message);
	if(message != "" && message != null){
		$(".alert").css("display","block");
		message = message.replace(/_/g, " ");
		//message.replace('/\+/g', ' ');
		console.log(message);
		$(".alert").html(message);
		
	}

	
	
});



