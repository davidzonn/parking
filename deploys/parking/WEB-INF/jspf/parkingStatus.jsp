<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:forEach items="${adminParkings}" var="parkingAdmin">
	<h1>Parking ${parkingAdmin.parkingName}.</h1>
 	<table>
 		<thead>
 			<tr><th>&#35;</th><th>Status</th><th>Type</th><th>Client</th></tr>
 		</thead>
 		<tbody>
			<c:forEach items="${parkingAdmin.parkingPlaces}" var = "parkingPlace">
			 	<tr class="${empty parkingPlace.regularReservations ? 'free' : 'occupied'}">
			 		<td>${parkingPlace.parkingPlaceNumber}</td>
			 		<td>${parkingPlace.status.statusName}</td>
			 		<td>${parkingPlace.typeReservation.reservationType}</td>
			 		<%/* 
			 		<td>${empty parkingPlace.regularReservations ? '-' : parkingPlace.regularReservations.idRegularReservation}<td>
				 		<c:choose>
							<c:when test="${empty parkingPlace.regularReservations }"><td>Empty</td></c:when>
							<c:when test="${not empty parkingPlace.regularReservations }">
								<td class = "occupied">Occupied</td>
							</c:when>
							<c:otherwise></c:otherwise>
						</c:choose>
					*/%>
			 	<tr>
			</c:forEach>
 		</tbody>	
	 </table>
</c:forEach>