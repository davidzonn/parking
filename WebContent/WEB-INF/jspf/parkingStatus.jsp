<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<c:forEach items="${adminParkings}" var="parkingAdmin">
	<h1>Parking ${parkingAdmin.parkingName}.</h1>
	 <c:forEach items="${parkingAdmin.parkingPlaces}" var = "parkingPlace">
	 	${parkingPlace.parkingPlaceNumber}
	 	
	 </c:forEach>
</c:forEach>