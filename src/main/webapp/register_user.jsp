<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form class="form" action="create" method="post">
		<div>
			<h4>Name:</h4>
		</div>
		<div>
			<input type="text" name="name">
		</div>
		<div>
			<h4>Email:</h4>
		</div>
		<div>
			<input type="text" name="email" required>
		</div>
		<div>
			<h4>Number:</h4>
		</div>
		<div>
			<input type="number" name="number" required>
		</div>
		<div>
			<h4>Location:</h4>
		</div>
		<div>
			<input type="text" name="location" required>
		</div>
		<div>
			<h4>Password:</h4>
		</div>
		<div>
			<input type="text" name="password" required>
		</div>
		<div>
			<h4>Image:</h4>
		</div>
		<div>
			<input type="text" name="image" required>
		</div>


		<button type="submit">Create</button>
	</form>
</body>
</html>