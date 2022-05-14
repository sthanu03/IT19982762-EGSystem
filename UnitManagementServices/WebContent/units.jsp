<%@page import="com.Unit"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<meta charset="ISO-8859-1">
<title>Unit Management</title>

<link rel="stylesheet" href="Views/bootstrap.min.css"> 
<link rel="stylesheet" type="text/css" href="css\footer.css"> 
<script src="components/jquery.min.js"></script>
<script src="components/units.js"></script>



 <nav class="navbar navbar-expand-md navbar-dark" style="background-color:#2BBBAD">
                   

                    <ul class="navbar-nav">
                        <h2>ElectroGrid Online Platform</h2>
                    </ul>
                 </nav>
               


</head>
<body>




<br>
<br>


<div class="container"> 
		<div class="row">  
		 <br>
            <div class="container col-md-5">
                <div class="card">
                    <div class="card-body">
                       

                        <caption>
                            <h2>
                                Unit Management
                            </h2>
                        </caption>
		
			
				<form id="formUnit" name="formUnit" method="post" action="units.jsp">
 Customer ID:
<input id="cus_id" name="cus_id" type="text"
 class="form-control form-control-sm">
<br> Customer Name:
<input id="cus_name" name="cus_name" type="text"
 class="form-control form-control-sm">
<br> Customer Phone Number:
<input id="cus_phone" name="cus_phone" type="text"
 class="form-control form-control-sm">
 <br> New Read:
<input id="new_read" name="new_read" type="text"
 class="form-control form-control-sm">
  <br> Last Read:
<input id="last_read" name="last_read" type="text"
 class="form-control form-control-sm">
<br> Used Unit:
<input id="used_unit" name="used_unit" type="text"
 class="form-control form-control-sm">
 <br>
<input id="btnSave" name="btnSave" type="button" value="Save"
 class="btn btn-primary">
<input type="hidden" id="hidUnitIDSave" name="hidUnitIDSave" value="">
</form>
						</div>
						</div>
						</div>
						
					 <br>  
					<div id="alertSuccess" class="alert alert-success"></div>
                    <div id="alertError" class="alert alert-danger"></div>
                   
			

            <div class="row">
               
             
                <div class="container">
                 <h3 class="text-center">Unit Details</h3>
                    <hr>
                   
                    <br>
                
                   			<div id="divUnitsGrid">
 <%
 Unit unitObj = new Unit();
  out.print(unitObj.readUnits());
 %>

					
					 
				</div> 
                   
                </div>
            </div>
				  
 			</div>
 		 
 		</div>    
 		
<br>
	 

</body>
</html>