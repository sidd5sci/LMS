
$(document).ready(function(){
	
	
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
	
	/* total();  */
	});
	
	
	
	
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
	
	
});