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
				
				<label for = "parkingName">Parking Name:</label>
				<input type="text" id ="name" required="required" > 
				<!--
				<label for = "percentageTrucks">Percentage Trucks:</label>
				<input type="number" min="0" max="100" id ="percentageTrucks"> 
				-->
				<label for = "totalSpaces">Total Number of spaces:</label>
				<input type="number" id ="totalSpaces" required="required" > 
				<button value = "New Parking" id = "newParkingButton">New Parking</button>
			</form>
		</section>
		<section>
			<select id = "parkings">
				<c:forEach items = "${parkings}" var="parking">
					<option value = ${parking.idParking}>${parking.parkingName}</option>
				</c:forEach>
			</select>
			<button>			
				Enter Parking
			</button>
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
		$("#parkings").append($('<option/>', { 
	        value: returnedData.id,
	        text : returnedData.name 
	    }));
	};
});
</script>
</body>