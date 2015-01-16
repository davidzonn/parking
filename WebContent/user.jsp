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
	<header>
		<h1>Go Park Yourself</h1>
	</header>
	<section>
			<c:if test="${user != null}">
				<div id = "welcomeMessage">Welcome ${user.username}!</div>
				<jsp:include page="logout.jsp"/>
			</c:if>
			<c:if test="${user == null}">
				<jsp:include page="login.jsp"/>
			</c:if>
		<form id = "reservationForm" method = "get" action = "checkout.jsp">
			<ol>	
				<li id = "reserve">
					<fieldset>
						<legend>Reservation Type</legend>
						<c:forEach items="${carTypes}" var="type">
							<label for="${type.reservationType}" value="${type.reservationType}">${type.reservationType}</label>
							<input type="radio" name="reservationType" value="${type.reservationType}" id="${type.reservationType}" required = "required"><br>
						</c:forEach>
					</fieldset>
					<fieldset>
						<legend>Date and time</legend>
						<fieldset>
							<legend>From</legend>
							<label for="fromDate">Starting Date</label>
							<input type="date" name="fromDate" id="fromDate"  value = "${today}" min = "${today}"><br/>
							<label for="fromTime">Starting Time</label>
							<input type="time" name="fromTime" id="fromTime" value = "${currentHour}">
						</fieldset>
						<fieldset>
							<legend>To</legend>
							<label for="toDate">Ending Date</label>
							<input type="date" name="toDate" id="toDate" value = "${today}"><br/>
							<label for="toTime">Ending Time</label>
							<input type="time" name="toTime" id="toTime" value = "${currentHour}">					
						</fieldset>
						<button id="checkAvailability">Check Availability!</button>
					</fieldset>
				</li>
				<li id="transportationRequired">
					<fieldset>
						<legend>Transportation Required?</legend>
						<label for="yesTransportation">yes</label>
						<input type="radio" id="yesTransportation" name="transportation" value="yesTransportation" required = "required"><br/>
						<label for="noTransportation">no</label>
						<input type="radio" id="noTransportation" name="transportation" value = "noTransportation" required = "required">
					</fieldset>
				</li>
				<li id="transportationType" hidden>
					<fieldset>
						<legend>Select Transportation Type</legend>
						<label for="shuttle">Regular Shuttle Service</label>
						<input type="radio" id="regular" name="transportationType" placeholder="For Existing Locations" value="regular"><br/>
						<label for="onDemand">Shuttle On Demand</label>
						<input type="radio" id="onDemand" name="transportationType" placeholder="For Custom locations" value="onDemand">
					</fieldset>
				</li>
				<li id="regularShuttle" hidden>
					<fieldset>
						<legend>Regular Shuttle Service</legend>
						<fieldset>
							<legend>Destination Selection</legend>
							<c:forEach items="${destinations}" var="destination">
								<label for="${destination.destinationName}">${destination.destinationName}</label>
								<input type="radio" name="destination" id="${destination.destinationName}" value="${destination.destinationName}"><br>
							</c:forEach>
						</fieldset>
					</fieldset>
				</li>
				<li id = "onDemandShuttle" hidden>
					<jsp:include page="WEB-INF/jspf/onDemand.jsp"></jsp:include>
				</li>
			</ol>
			<!-- jsp:include page="login.jsp" /-->
		<button id = "makeReservation" type = "submit" hidden = "hidden"></button>
		</form>
		<input id = "reservationProceed" type="submit" value = "Make Reservation"/>
		<div id = "endLogin" hidden = "hidden">
			<jsp:include page="login.jsp"/>
		</div>
	</section>	
	<script src="scripts/jquery.js"></script>
	<!--  script src="ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script> -->
	<script>
		$(function() {
			var reserve = $("#reserve");
			var transportationRequired = $("#transportationRequired");
			var transportationType = $("#transportationType");
			var regularShuttle = $("#regularShuttle");
			var onDemandShuttle = $("#onDemandShuttle");
			$("#transportationRequired input:radio").click(function() {
				if ($(this).val() === "yesTransportation") {
					transportationType.show();
					$("#transportationType input:radio").attr("required", "required");
				} else {
					$("#transportationType input:radio").removeAttr("required");
					$("#regularShuttle input:radio").removeAttr("required");
					$("#onDemandShuttle input:radio").removeAttr("required");
					transportationType.hide();
					regularShuttle.hide();
					onDemandShuttle.hide();
				}
			});
			$("#transportationType input:radio").click(function() {
				if ($(this).val() === "regular") {
					$("#regularShuttle input:radio").attr("required", "required");
					$("#onDemandShuttle input:radio").removeAttr("required");
					regularShuttle.show();
					onDemandShuttle.hide();
				} else {
					$("#onDemandShuttle input:radio").attr("required", "required");
					$("#regularShuttle input:radio").removeAttr("required");
					onDemandShuttle.show();
					regularShuttle.hide();
				}
			});
		});
		/*
		function crossValidations() {
			jQuery.validator.addMethod("validateEndTime", endTimeValidation, "Ending Time Should be After Starting Time!");
			alert("DDD");
		}
		var endTimeValidation = function () {
			return false;
		}
		*/
		$("#reservationProceed").click(function() {
			var endLogin = $("#endLogin");
			$.ajax({
				type: "POST",
				url: "CheckLogin",
				async: false,
				success: function (result) {
					if (result === "true") {
						$("#makeReservation").click();
						//crossValidations();
						//$("#reservationForm").submit();
						//window.location = "/checkout.jsp";
					} else {
						alert("Please log in to continue!");
						return false;
						//endLogin.show();
					}
				}
			});
		});
	</script>
	<script>
	</script>
</body>
</html>