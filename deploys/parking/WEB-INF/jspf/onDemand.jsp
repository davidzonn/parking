<%@page import="java.util.Vector"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="java.util.List"%>
<%@page import="model.Range"%>
<% Vector<Range> ranges = (Vector<Range>)application.getAttribute("ranges"); %>
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
							${range.rangeKm} Km.
						</label>
					</td>
					<td>
						<label for="${range}">&euro;${range.rangePrice}</label>
					</td>
					<td>
						<input type="radio" name="range" id="${range}" value="${range.rangeKm}"><br>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</fieldset>
	<fieldset>
		<legend>Select Time</legend>
			<label for="onDemandDate">Date</label>
			<input type="date" name="onDemandDate" id="onDemandDate" value = "${today}"><br/>
			<label for="onDemandTime">Time</label>
			<input type="time" name="onDemandTime" id="onDemandTime" value = "${currentHour}">
	</fieldset>
</fieldset>