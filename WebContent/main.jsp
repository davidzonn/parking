<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Login</title>
	<link rel="stylesheet" href="styles/forms.css" />
</head>
<body>
	<form method = "post" action = "processLogin">
		<fieldset>
			<legend>
				Login
			</legend>
			<label for="username">Username:</label>
			<input type="text" id="username" name="username"/><br/>
			<label for="password">Password:</label>
			<input type="password" id="password" name="password"/><br/>
			<input class= "submit" type="submit" value="Login now!"/>
		</fieldset>
	</form>
</body>
</html>