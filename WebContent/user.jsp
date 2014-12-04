<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="beans.User"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Reservation Management</title>
</head>
<body>
	<% User user = (User)request.getAttribute("user"); %>
	<div id = "welcome">Welcome <%=user.getUsername()%> !</div>
	<ol>
		<li>Reserve spot</li>
		<li>Transportation</li>
		<li>Shuttle</li>
		
	</ol>
</body>
</html>