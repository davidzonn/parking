<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Reservation Management</title>
</head>
<body>
	<c:if test="${user.accessLevel == 3}">
		<section>
			<form id="newParking">
				<fieldset><legend>Parking Creation</legend>
					<label for = "parkingName">Parking Name:</label>
					<input type="text" id ="name" required="required" > 
					<!--
					<label for = "percentageTrucks">Percentage Trucks:</label>
					<input type="number" min="0" max="100" id ="percentageTrucks"> 
					-->
					<label for = "totalSpaces">Total Number of spaces:</label>
					<input type="number" id ="totalSpaces" required="required" > 
					<button value = "New Parking" id = "newParkingButton">New Parking</button>
				</fieldset>
			</form>
		</section>
		<section>
			<fieldset><legend>Parking Managment</legend>
				<select id = "parkings">
					<option value = "-1">Select a Parking</option>
					<c:forEach items = "${parkings}" var="parking">
						<option value = ${parking.idParking}>${parking.parkingName}</option>
					</c:forEach>
				</select>
				<div id = "adminsParking"></div>
				<!-- button>			
					Enter Parking
				</button-->
			</fieldset>
		</section>
		<section>
			<header>Admin Managment</header>	
			Update Admin
			Assign Admin
		</section>
		<jsp:include page="logout.jsp"/>
	</c:if>
	<c:if test="${user.accessLevel < 3}">
		<h1>NOT ALLOWED</h1>
	</c:if>
	<c:if test="${empty user}">
		<jsp:include page="login.jsp"/>
	</c:if>
<script src="scripts/jquery.js"></script>
<script type="text/javascript">
$( document ).ready(function() {
	$("#newParking").submit(createNewParking);
	function createNewParking() {
		var numVehicles = $("#totalSpaces").val();
		var parkingName = $("#name").val();
		var data = {numVehicles: numVehicles, parkingName: parkingName};
		$.post("NewParking", data, updateParkingViews);
		return false; //Do not send form
	};
	
	var updateParkingViews = function(returnedData) {
		alert (returnedData.name + " successfully created!");
		$("#parkings").append($('<option/>', { 
	        value: returnedData.id,
	        text : returnedData.name 
	    }));
	};
});
</script>
<script>
$( document ).ready(function() {
	$("#parkings").change(postAdmins);
	var parkingNumber;
	function postAdmins() {
		parkingNumber = $("#parkings").val();
		if (parkingNumber != -1) {
		console.log(parkingNumber, " selected.");
			$.post("GetAdmin", {idParking : parkingNumber}).done(addAdmins);
		}
	};
	var admins;
	function addAdmins(data) {
		var adminsParking = $("#adminsParking")
		admins = data.admins;
		adminsParking.empty();	
		if (data.currentAdmin == "") {
			adminsParking.append("No admins currently assigned!");
		} else{
			adminsParking.append("Current admin: " + data.currentAdmin);
		}
		adminsParking.append("<br>");
		var adminsSelect = $("<select>");
		var titleSelect = $("<option>");
		titleSelect.attr("value", "");
		titleSelect.html("Select administrator to assign");
		adminsSelect.append(titleSelect);
		adminsParking.append(adminsSelect);
		//admins.each();
		$.each( admins, function(index, admin) {
			var optionNode = $("<option>");
			optionNode.attr("value", admin);
			optionNode.html(admin);
			adminsSelect.append(optionNode);			
		} );
		var adminAssignButton = $("<button>");
		adminAssignButton.html("Assign Selected Admin");
		adminAssignButton.click(function(){assignAdmin(parkingNumber, adminsSelect.val());});
		adminsParking.append("<br>");
		adminsParking.append(adminAssignButton);
	};
	function assignAdmin(parkingID, adminName) {
		alert(adminName + " set to administer this parking.")
		var data = {parkingID: parkingID, adminName: adminName};
		$.post("AssignAdmin", data).done(function(){
			var obj = {admins: admins, currentAdmin: adminName};
			addAdmins(obj);
		});
	}
});
</script>
</body>