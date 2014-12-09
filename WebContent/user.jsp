<%@page import="beans.Range"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="beans.User"%>
<%@ page import="java.util.List" %>
<%
	User user = (User)session.getAttribute("user");
	List<String> carTypes = (List<String>)application.getAttribute("carTypes");
	List<String> destinations = (List<String>)application.getAttribute("destinations");
	boolean sessionStarted = (user != null);
	List<Range> ranges = (List<Range>)application.getAttribute("ranges");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Reservation Management</title>
</head>
<body>
	<header>
		<h1>Make a Reservation</h1>
			<% if (!sessionStarted) { %>
				<jsp:include page="login.jsp"/>
			<%} else {%>
				<div id = "welcomeMessage">Welcome <%=user.getUsername()%>!</div>
				<form method = "post" action = "processLogout"><button>Logout</button> </form>
			<%}%>
	</header>
	<section>
		<form id="reservationForm" method = "post" action = "processReservation">
			<ol>	
				<li id = "reserve">
					<fieldset>
						<legend>Reservation Type</legend>
						<c:forEach items="${carTypes}" var="type">
							<label for="${type}" value="${type}">${type}</label>
							<input type="radio" name="reservationType" value="${type}" id="${type}"><br>
						</c:forEach>
					</fieldset>
					<fieldset>
						<legend>Date and time</legend>
						<fieldset>
							<legend>From</legend>
							<label for="fromDate">Starting Date</label>
							<input type="date" name="fromDate" id="fromDate"><br/>
							<label for="fromTime">Starting Time</label>
							<input type="time" name="fromTime" id="fromTime">
						</fieldset>
						<fieldset>
							<legend>To</legend>
							<label for="toDate">Ending Date</label>
							<input type="date" name="toDate" id="toDate"><br/>
							<label for="toTime">Ending Time</label>
							<input type="time" name="toTime" id="toTime">					
						</fieldset>
						<button id="checkAvailability">Check Availability!</button>
					</fieldset>
					<button id = "reserveButton" type="button">Next Step!</button>
				</li>
				<li id="transportationRequired" hidden>
					<fieldset>
						<legend>Transportation Required?</legend>
						<label for="yesTransportation">yes</label>
						<input type="radio" id="yesTransportation" name="transportation" value="yesTransportation"><br/>
						<label for="noTransportation">no</label>
						<input type="radio" id="noTransportation" name="transportation" value = "noTransportation">
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
								<label for="${destination}">${destination}</label>
								<input type="radio" name="destination" id="${destination}"><br>
							</c:forEach>
						</fieldset>
					</fieldset>
				</li>
				<li id = "onDemandShuttle" hidden>
					<fieldset>
						<legend>On Demand Shuttle Service</legend>
						<fieldset>
							<legend>Range Selection</legend>
							<table>
								<thead>
									<tr>
										<th> Distance </th>
										<th> Price </th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${ranges}" var="range">
										<tr>
											<td>
												<label for="${range}">
													${range.distance}
												</label>
											</td>
											<td>
												<label for="${range}">&euro;${range.price}</label>
											</td>
											<td>
												<input type="radio" name="range" id="${range}" value="${range.name}"><br>
											</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</fieldset>
						<fieldset>
							<legend>Select Time</legend>
								<label for="onDemandDate">Date</label>
								<input type="date" name="onDemandDate" id="onDemandDate"><br/>
								<label for="onDemandTime">Time</label>
								<input type="time" name="onDemandTime" id="onDemandTime">
						</fieldset>
					</fieldset>
				</li>
			</ol>
			<!-- jsp:include page="login.jsp" /-->
			<input type="submit" value = "Make Reservation"/>
		</form>
	</section>	
	<script src="scripts/jquery.js"></script>
	<!--  script src="ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script> -->
	<script>
		$(function() {
	$("#loginForm").hide();
			var reserve = $("#reserve");
			var transportationRequired = $("#transportationRequired");
			var transportationType = $("#transportationType");
			var regularShuttle = $("#regularShuttle");
			var onDemandShuttle = $("#onDemandShuttle");
			$("#reserveButton").click(function(){
				transportationRequired.show();
			});
			$("#transportationRequired input:radio").click(function() {
				if ($(this).val() === "yesTransportation") {
					transportationType.show();
				} else {
					transportationType.hide();
					regularShuttle.hide();
					onDemandShuttle.hide();
				}
			});
			$("#transportationType input:radio").click(function() {
				if ($(this).val() === "regular") {
					regularShuttle.show();
					onDemandShuttle.hide();
				} else {
					onDemandShuttle.show();
					regularShuttle.hide();
				}
			});
		});
		$("#reservationForm").submit(function() {
			<% if (sessionStarted) { %>
				window.location = "/checkout.jsp";;
			<% } else { %>
				alert ("Please log in to continue!");
				window.location = "/login.jsp";
			<% } %>
		});
	</script>
</body>
</html>