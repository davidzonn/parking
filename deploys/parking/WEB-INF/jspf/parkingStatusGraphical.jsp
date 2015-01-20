<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:forEach items="${adminParkings}" var="parkingAdmin">
	<h1>Parking ${parkingAdmin.parkingName}.</h1>
	<div id="parkingPlaces">
		<c:forEach items="${parkingAdmin.parkingPlaces}" var = "parkingPlace">
		<div class = "${empty parkingPlace.regularReservations ? 'free' : 'occupied'}">
			${parkingPlace.parkingPlaceNumber} : ${parkingPlace.typeReservation.reservationType}
		</div>
		</c:forEach>
	</div>
</c:forEach>