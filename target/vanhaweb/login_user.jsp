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
<style>
body {
	font-family: "Source Sans Pro", "Helvetica Neue", Arial, sans-serif;
}

.logo_re {
	width: 400px;
	height: 100px;
	background-color: rgb(1, 1, 1);
	rotate: -90deg;
	position: absolute;
	top: 215px;
	right: 200px;
	border-radius: 25px;
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

.heading-in {
	margin: 30px 0px 20px 0px;
	color: rgb(255, 255, 255);
	font-size: 22px;
}

.field {
	display: flex;
	align-items: center;
	justify-content: center;
	gap: 10px;
	border-radius: 25px;
	padding: 10px;
	border: none;
	outline: none;
	color: white;
	background-color: black;
}

.input-field {
	width: 90%;
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

h4 {
	margin: 0;
	color: white;
}

.closebtn {
	float: right;
	position: relative;
	top: 100px;
	right: 100px;
	font-size: 50px;
	color: aliceblue;
}

.closebtn:hover {
	color: #ffd343;
}

.form .btn {
	display: flex;
	justify-content: center;
	flex-direction: row;
	margin: 20px 0px;
}

.button1 {
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

.button1:hover {
	background-color: rgb(102, 160, 219);
	color: white;
}

span {
	color: white;
}

a {
	color: white;
	margin: 0px 0px 10px 20px;
}

.error {
	color: red;
}
</style>
<meta charset="ISO-8859-1">
<title>Login page</title>
</head>
<body>
	<%
	String errorMessage = request.getParameter("errorMessage");
	if (errorMessage != null) {
		out.println("<p class='error'>" + errorMessage + "</p>");
	}
	%>
	<form class="form" action="login" method="get">
		<p class="heading-in">Sign in</p>
		<div>
			<h4>Email:</h4>
		</div>
		<div>
			<input type="email" name="email" class="input-field"
				placeholder="example@abc.com" title="Use proper email"
				autocomplete="off" value="tamil@gmail.com" required>
		</div>
		<div>
			<h4>Password:</h4>
		</div>
		<div>
			<input type="password" name="password" class="input-field"
				placeholder="Password" value="Tami#123" required>
		</div>
		<div class="btn">
			<button type="submit" class="button1" id="login_form">Login</button>

		</div>
		<p>
			<span>Create new account:</span> <a href="user/new">Sign Up</a>
		</p>
	</form>

</body>
</html>