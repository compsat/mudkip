$(document).ready(function(){
	var modal = $("#modal");

   $("#addToggle").click(function (){
   		$("#modalTitle").html("New");
   		$("#submit").html("Add");
   		modalToggle();
   });

   $("#close").click(function (){
   		modalToggle();
   });

   $("#addStop").click(function (){
   		if($("#stop" + $("#stop").val()).length<1){
	   		if($("#no_stop") != null){
	   			$("#no_stop").remove();
	   		}
	   		var row ="<tr id='stop" + $("#stop").val() + "'><td>" + $("#stop").val() + "</td>";
	   			row += "<td>" + $("#stop option:selected").html() + "</td></tr>"; 
	   		$("#quest_stops").append(row);
   		}
   		
   	});

   function modalToggle(){
   		if(modal.is(":visible")){
   			modal.hide();
   		}else{
   			modal.show();
   		}
   }
}); 