$(document).ready(function()
{
		$("#alertSuccess").hide();
	 
	 
	 
	 	$("#alertError").hide();
});

// SAVE ============================================
$(document).on("click", "#btnSave", function(event)
{
	// Clear alerts---------------------
	 $("#alertSuccess").text("");
	 $("#alertSuccess").hide();
	 $("#alertError").text("");
	 $("#alertError").hide();

// Form validation-------------------
var status = validateUnitForm();
	if (status != true)
	 {
		 $("#alertError").text(status);
		 $("#alertError").show();
		 return;
     }
 
// If valid------------------------
var type = ($("#hidUnitIDSave").val() == "") ? "POST" : "PUT";
 $.ajax(
 {
 url : "UnitsAPI",
 type : type,
 data : $("#formUnit").serialize(),
 dataType : "text",
 complete : function(response, status)
 {
 onUnitSaveComplete(response.responseText, status);
 }
 });
});



// UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event)
{
$("#hidIDSave").val($(this).closest("tr").find('#hidIDUpdate').val());
 $("#cus_id").val($(this).closest("tr").find('td:eq(0)').text());
 $("#cus_name").val($(this).closest("tr").find('td:eq(1)').text());
 $("#cus_phone").val($(this).closest("tr").find('td:eq(2)').text());
$("#new_read").val($(this).closest("tr").find('td:eq(3)').text());
$("#last_read").val($(this).closest("tr").find('td:eq(4)').text());
$("#used_unit").val($(this).closest("tr").find('td:eq(5)').text());
});

//DELETE==========================================================
$(document).on("click", ".btnRemove", function(event)
{
 $.ajax(
 {
 url : "UnitsAPI",
 type : "DELETE",
 data : "id=" + $(this).data("unitid"),
 dataType : "text",
 complete : function(response, status)
 {
 onUnitDeleteComplete(response.responseText, status);
 }
 });
});


 
// CLIENT-MODEL================================================================
function validateUnitForm()
{
	
// CODE
 var tmpAcc = $("#cus_id").val().trim();
if (!$.isNumeric(tmpAcc)) 
		{
		return "Insert Customer ID.";
		} 



if ($("#cus_name").val().trim() == "")
 {
 return "Insert Customer Name.";
 } 


if ($("#cus_phone").val().trim() == "")
 {
 return "Insert Phone Number.";
 }
// Unit Amount
 var tmpNew = $("new_read").val().trim();
		if (!$.isNumeric(tmpNew)) 
		 {
		 return "Insert New read.";
		 }

 var tmpLast = $("last_read").val().trim();
		if (!$.isNumeric(tmpLast)) 
		 {
		 return "Insert Last read.";
		 }
	
	 var tmpUsed = $("used_unit").val().trim();
		if (!$.isNumeric(tmpUsed)) 
		 {
		 return "Insert Used Unit.";
		 }



function onUnitSaveComplete(response, status)
{
if (status == "success")
 {
 var resultSet = JSON.parse(response);
 if (resultSet.status.trim() == "success")
 {
 $("#alertSuccess").text("Successfully saved.");
 $("#alertSuccess").show();

 $("#divUnitsGrid").html(resultSet.data);
 } else if (resultSet.status.trim() == "error")
 {
 $("#alertError").text(resultSet.data);
 $("#alertError").show();
 }
 } else if (status == "error")
 {
 $("#alertError").text("Error while saving.");
 $("#alertError").show();
 } else
 {
 $("#alertError").text("Unknown error while saving..");
 $("#alertError").show();
 } 
$("#hidUnitIDSave").val("");
 $("#formUnit")[0].reset();
}

function onUnitDeleteComplete(response, status)
{
if (status == "success")
 {
 var resultSet = JSON.parse(response);
 if (resultSet.status.trim() == "success")
 {
 $("#alertSuccess").text("Successfully deleted.");
 $("#alertSuccess").show();
 $("#divUnitsGrid").html(resultSet.data);
 } else if (resultSet.status.trim() == "error")
 {
 $("#alertError").text(resultSet.data);
 $("#alertError").show();
 }
 } else if (status == "error")
 {
 $("#alertError").text("Error while deleting.");
 $("#alertError").show();
 } else
 {
 $("#alertError").text("Unknown error while deleting..");
 $("#alertError").show();
 }
}
return true;
}
