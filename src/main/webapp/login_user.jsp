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

.divide {
	display: flex;
	justify-content: space-evenly;
}

h1 {
	text-align: center;
}

h4 {
	margin: 10px 0;
}

div {
	margin-bottom: 15px;
}

input[type="text"], input[type="number"], textarea, select {
	width: 90%;
	padding: 10px;
	border: 1px solid #ccc;
	border-radius: 3px;
}

.left-aling {
	display: flex;
	align-items: center;
}

.left_content {
	margin-right: 10px;
}

button {
	display: block;
	width: 50%;
	padding: 10px;
	background-color: #007bff;
	color: white;
	border: none;
	border-radius: 3px;
	cursor: pointer;
}

button:hover {
	background-color: #0056b3;
}
</style>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<form class="form" action="user/profile" method="get">
		<div>
			<h4>Email:</h4>
		</div>
		<div>
			<input type="text" name="email" required>
		</div>

		<div>
			<h4>Password:</h4>
		</div>
		<div>
			<input type="password" name="password" required>
		</div>

		<button type="submit">Login</button>
	</form>
	<a href="user/new"><button>Register</button></a>

</body>
</html>