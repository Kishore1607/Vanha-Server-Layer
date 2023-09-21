<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link rel="icon"
	href="../assets/img/illustration/v-removebg-preview.png">
<link rel="dns-prefetch" href="//fonts.googleapis.com">
<link
	href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700,"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.1/css/all.min.css"
	integrity="sha512-MV7K8+y+gLIBoVD59lQIYicR65iaqukzvf/nwasF0nqhPay5w/9lJmVM2hMDcnK1OnMGCdVK+iQrJ7lzPJQd1w=="
	crossorigin="anonymous" referrerpolicy="no-referrer">
<meta charset="ISO-8859-1">
<title>Register page</title>
<style>
body {
	font-family: "Source Sans Pro", "Helvetica Neue", Arial, sans-serif;
}

.form {
	position: absolute;
	top: 50%;
	left: 50%;
	transform: translate(-50%, -50%);
	-ms-transform: translate(-50%, -50%);
	margin-left: 30px;
	width: 330px;
	display: flex;
	flex-direction: column;
	gap: 10px;
	padding: 0px 20px 5px 20px;
	background-color: black;
	border-radius: 25px;
	-webkit-transition: .4s ease-in-out;
	transition: .4s ease-in-out;
}

.heading {
	margin: 30px 0px 20px 0px;
	color: rgb(255, 255, 255);
	font-size: 22px;
}

h4 {
	color: white;
	margin: 0;
}

.input-field {
	width: 80%;
	border: 1px solid black;
	border-radius: 15px;
	padding: 15px;
	color: white;
	background-color: #363636;
	box-shadow: 2px 5px 10px rgba(0, 0, 0, 0.3);
	transition: 300ms ease-in-out;
}

.input-field:hover {
	border: 1px solid whitesmoke;
}

.form .btn {
	display: flex;
	justify-content: center;
	flex-direction: row;
	margin: 20px 0px;
}

.button {
	padding: 10px 20px;
	border-radius: 5px;
	margin-right: 10px;
	border: none;
	outline: none;
	-webkit-transition: .4s ease-in-out;
	transition: .4s ease-in-out;
	background-color: #252525;
	color: white;
}

.button:hover {
	background-color: rgb(102, 160, 219);
	color: white;
}

.error {
	color: red;
}
</style>
</head>
<body>
	<jsp:include page="header.jsp" />
	<%
	String errorMessage = request.getParameter("errorMessage");
	if (errorMessage != null) {
		out.println("<p class='error'>" + errorMessage + "</p>");
	}
	%>
	<form class="form" action="create" method="post">
		<p class="heading">Sign up</p>
		<div class="field">
			<h4>Name:</h4>
		</div>
		<div>
			<input type="text" name="name" placeholder="Your name"
				class="input-field" value="Kishore">
		</div>
		<div class="field">
			<h4>Email:</h4>
		</div>
		<div>
			<input type="text" name="email" placeholder="Your email"
				class="input-field" value="kishore@gmail.com" required>
		</div>
		<div class="field">
			<h4>Number:</h4>
		</div>
		<div>
			<input type="number" name="number" placeholder="Your number"
				class="input-field" value="8870825039" required>
		</div>
		<div class="field">
			<h4>Location:</h4>
		</div>
		<div>
			<input type="text" name="location" placeholder="Your location"
				class="input-field" value="Chennai" required>
		</div>
		<div class="field">
			<h4>Password:</h4>
		</div>
		<div>
			<input type="text" name="password" placeholder="Password"
				class="input-field" value="Kish#123" required>
		</div>
		<div class="field">
			<h4>Image:</h4>
		</div>
		<div>
			<input type="text" name="image" placeholder="Image url"
				class="input-field"
				value="https://source.unsplash.com/featured/?people" required>
		</div>

		<div class="btn">
			<button type="submit" class="button">Create</button>
		</div>
	</form>
</body>
</html>