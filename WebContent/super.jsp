<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="model.User"%>
<%@page import="java.util.Collection"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<% User user = (User)session.getAttribute("user");%>
<% Collection parkings = (Collection)session.getAttribute("parkings"); %>
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
				<input type="text" id ="name"> 
				<!--
				<label for = "percentageTrucks">Percentage Trucks:</label>
				<input type="number" min="0" max="100" id ="percentageTrucks"> 
				-->
				<label for = "totalSpaces">Total Number of spaces:</label>
				<input type="number" id ="totalSpaces"> 
				<button value = "New Parking" id = "newParkingButton">New Parking</button>
			</form>
			<select>
				<c:forEach items = "parkings" var="parking">
					<option value = "${park.idParking}" id="${park.idParking}">
						P "${park.idParking}": "${park.parkingName}"
					</option>
				</c:forEach>
			</select>
			
			Enter Parking
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
		console.log(returnedData);
	}
});
</script>
</body>