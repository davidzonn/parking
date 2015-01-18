<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Check Out</title>
</head>
<body>
	<h1>Confirm your reservation:</h1>
	<section>
		<h1>Parking ${param.parking }</h1>
		<h2>Reservation Type:</h2>
			${param.reservationType}
		<h2>From</h2>
			${param.fromDate }, ${param.fromTime }
		<h2>To</h2>
			${param.toDate }, ${param.toTime } <br><br>
	</section>
	<section>
		<h2>Transportation</h2>
		<c:if test="${param.transportation == 'yesTransportation'}">
			<c:if test ="${param.transportationType =='onDemand' }">
				On Demand Shuttle &mdash; Custom location, date, and time. <br>
				Distance lower than ${param.range} Kilometers.<br>
				Due:<br>
					&emsp;&emsp;&emsp;${param.onDemandDate }, <br>
					&emsp;&emsp;&emsp;${param.onDemandTime }.
			</c:if>
			<c:if test ="${param.transportationType =='regular' }">
				Regular Transportation: Standard tickets with fixed locations. Departure every full hour. Destination: ${param.destination }.
			</c:if>
		</c:if>
		<c:if test="${param.transportation == 'noTransportation' }">
			No Transportation Required
		</c:if>
		<br>
		<br>
	</section>
	<button id="confirmButton">Confirm</button>
	<script src="scripts/jquery.js"></script>
	<script>
	$(function() {
		var parking = "${param.parking}";
		var reservationType = "${param.reservationType}";
		var fromDate = "${param.fromDate }";
		var fromTime = "${param.fromTime }";
		var toDate = "${param.toDate }";
		var toTime = "${param.toTime }";
		var onDemandRange = "${param.range}";
		var onDemandDate = "${param.onDemandDate }";
		var onDemandTime = "${param.onDemandTime }";
		var regularDestination = "${param.destination }";
		var transportation = "${param.transportation}";
		var transportationRequired = (transportation == "yesTransportation");
		var transportationType = "${param.transportationType}";
		var makeParkingReservation = function() {
			var data = 	{
							parking: parking,
							type: reservationType,
							fromDate: fromDate,
							fromTime: fromTime,
							toDate: toDate,
							toTime: toTime,
							onDemandRange: onDemandRange,
							onDemandDate : onDemandDate,
							onDemandTime : onDemandTime,
							regularDestination : regularDestination,
							transporation : transportationRequired,
							transportationType : transportationType
						};
			$.ajax({
				type: "POST",
				url: "ReserveParking",
				data: data,
				async: false,
				success: function (result) {
					alert("Reservation Successful!");
					$(location).attr('href',".");
				}
			});
		};
		$("#confirmButton").click(makeParkingReservation);
	});
	</script>
</body>
</html>