<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<link rel="stylesheet" type="text/css" href="styles/header.css">
	<link rel="stylesheet" type="text/css" href="styles/graphicalParking.css">
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Admin Page</title>
</head>
<body>
	<jsp:include page="/WEB-INF/jspf/header.jsp"></jsp:include>
	<c:if test="${user.accessLevel >= 2}">
		<div id = "tableView" hidden = "hidden">
			<jsp:include page="WEB-INF/jspf/parkingStatus.jsp"></jsp:include>
		</div>
		<div id = "graphicalView">
			<jsp:include page = "WEB-INF/jspf/parkingStatusGraphical.jsp"></jsp:include>
		</div>
			<button id = "switchView" >Switch to Table View</button>
			<jsp:include page="logout.jsp"/>
	</c:if>
	<c:if test="${user.accessLevel < 2}">
		<h1>
			YOU ARE NOT ALLOWED HERE! THIS IS AN ADMIN PAGE!
		</h1>
	</c:if>
	<script src="scripts/jquery.js"></script>
	<script>
	$(function() {
		var switchViews = function() {
			if ($("#graphicalView").is(":visible")) {
				$("#graphicalView").hide();
				$("#tableView").show();
				$("#switchView").text("Switch To Graphical View");
			} else {
				$("#graphicalView").show();
				$("#tableView").hide();
				$("#switchView").text("Switch To Table View");
			}
		}
		$("#switchView").click(switchViews);
	});
	</script>
</body>
</html>