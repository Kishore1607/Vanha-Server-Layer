<%@page import="in.fssa.vanha.model.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<style>
.form {
	text-aling: center;
	max-width: 600px;
	margin: 0 auto;
	padding: 20px;
	border: 1px solid #ccc;
	border-radius: 5px;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
	font-family: Arial, sans-serif;
	max-width: 600px;
}

::placeholder {
	font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto,
		Oxygen, Ubuntu, Cantarell, ' Open Sans ', ' Helvetica Neue ',
		sans-serif;
}

.flex-column>label {
	color: #151717;
	font-weight: 600;
}

.inputForm {
	border: 1.5px solid #ecedec;
	border-radius: 10px;
	height: 50px;
	display: flex;
	align-items: center;
	padding-left: 10px;
	transition: 0.2s ease-in-out;
}

.input {
	margin-left: 10px;
	border-radius: 10px;
	border: none;
	width: 100%;
	height: 100%;
}

.input:focus {
	outline: none;
}

.inputForm:focus-within {
	border: 1.5px solid #2d79f3;
}

.flex-row {
	display: flex;
	flex-direction: row;
	align-items: center;
	gap: 10px;
	justify-content: space-between;
}

.flex-row>div>label {
	font-size: 14px;
	color: black;
	font-weight: 400;
}

.btn {
	width: 100%;
	height: 50px;
	border-radius: 10px;
	font-weight: 500;
	gap: 10px;
	border: 1px solid #2d79f3;
	background-color: #2d79f3;
	color: white;
	cursor: pointer;
}

.btn:hover {
	border: 1px solid black;
}

.error {
	color: red;
}
</style>
<meta charset="ISO-8859-1">
<title>Edit user form</title>
</head>
<body>
	<jsp:include page="header.jsp" />
	<%
	String errorMessage = request.getParameter("errorMessage");
	if (errorMessage != null) {
		out.println("<p class='error'>" + errorMessage + "</p>");
	}
	%>
	<%
	HttpSession sessionData = request.getSession();
	User user = (User) sessionData.getAttribute("user");
	%>
	<form class="form" action="update" method="post">
		<h2>Update your profile</h2>
		<div class="flex-column">
			<p>Name:</p>
		</div>
		<div class="inputForm">
			<input type="text" name="name" class="input"
				value="<%=user.getName()%>">
		</div>
		<div>
			<input type="hidden" name="email" class="input"
				value="<%=user.getEmail()%>">
		</div>
		<div class="flex-column">
			<p>Number:</p>
		</div>
		<div class="inputForm">
			<input type="number" name="number" class="input"
				value="<%=user.getNumber()%>" required>
		</div>
		<div class="flex-column">
			<p>Location:</p>
		</div>
		<div class="inputForm">
			<input type="text" name="location" class="input"
				value="<%=user.getLocation()%>" required>
		</div>
		<div class="flex-column">
			<p>Image:</p>
		</div>
		<div class="inputForm">
			<input type="text" name="image" class="input"
				value="<%=user.getImage()%>" required>
		</div>


		<button class="btn" type="submit">Save changes</button>
	</form>
</body>
</html>