<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="beans.User"%>
<%@ page import="java.util.List" %>
<%
	User user = (User)session.getAttribute("user");
	List carTypes = (List)application.getAttribute("carTypes");
	boolean sessionStarted = (user != null);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Reservation Management</title>
</head>
<body>
	<script src="scripts/jquery.js"></script>
	<header>
		<h1>Make a Reservation</h1>
			<%= carTypes %>
			<% if (!sessionStarted) { %>
				<jsp:include page="login.jsp"/>
			<%} else {%>
				<div id = "welcomeMessage">Welcome <%=user.getUsername()%>!</div>
				<form method = "post" action = "processLogout"><button>Logout</button> </form>
			<%}%>
	</header>
	<section>
		<form method = "post" action = "processReservation">
			<ol>	
				<li id = "reserve" name = "reserve">
					<% //<jsp:include page = "getTypes"></jsp:include> %>
					<c:forEach items="${carTypes}" var="type">
						<c:out value = "${type.name}"></c:out>
					</c:forEach>		
				</li>
				<li>Transportation</li>
				<li>Shuttle</li>
				<li>
			</ol>
			<input type="submit" value = "Make Reservation"/>
		</form>
	</section>
	<script>
/*		if (<%=user != null%>) {
			$("#welcome").html("Welcome ! <button id="logout"></button>");
			
		} else {
			$("#welcome").load("login.jsp");
		}
		function logout() {
			<% session.invalidate(); %>
		}*/
	</script>
</body>
</html>