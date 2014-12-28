<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="model.User"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<% User user = (User)session.getAttribute("user");%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<c:if test="${user.accessLevel >= 2}">
		HI!!!
		<jsp:include page="logout.jsp"/>
	</c:if>
	<c:if test="${user.accessLevel < 2}">
		<h1>
			YOU ARE NOT ALLOWED HERE! THIS IS AN ADMIN PAGE!
		</h1>
	</c:if>
</body>
</html>