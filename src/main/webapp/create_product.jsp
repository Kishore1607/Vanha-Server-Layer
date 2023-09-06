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

.divide {
	display: flex;
	justify-content: space-evenly;
}

h1 {
	text-align: center;
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
	<%
	User user = (User) session.getAttribute("user");
	%>
	<form class="form" action="create" method="post">
		<h1>Add Your Product</h1>
		<div class="divide">
			<section>
				<div>
					<p>Product Id:</p>
				</div>
				<div>
					<input type="text" name="product_id">
				</div>
				<div>
					<p>Your email:</p>
				</div>
				<div>
					<input type="text" name="seller_id" value="<%=user.getEmail()%>"
						readonly>
				</div>
				<div>
					<p>Product Name:</p>
				</div>
				<div>
					<input type="text" name="name" required>
				</div>
				<div>
					<div>
						<p>Product Price:</p>
					</div>
					<div>
						<input type="number" name="price" required>
					</div>
				</div>
				<div>
					<div>
						<p>Image 1</p>
					</div>
					<div>
						<input type="text" name="asset1" required>
					</div>
				</div>
				<div>
					<div>
						<p>Image 3</p>
					</div>
					<div>
						<input type="text" name="asset2" required>
					</div>
				</div>
			</section>
			<section>

				<div>
					<div>
						<div>
							<p>Used</p>
						</div>
						<div>
							<input type="number" name="period" required>
						</div>
					</div>
					<div>
						<div>
							<p>Category</p>
						</div>
						<div>
							<select name="category" required>
								<option value="car">Car</option>
								<option value="bike">Bike</option>
								<option value="computer">Computer</option>
								<option value="mobile">Mobile</option>
							</select>
						</div>
					</div>

					<div>
						<div>
							<p>Duration</p>
						</div>
						<div>
							<select name="duration" required>
								<option value="month">Month</option>
								<option value="year">Year</option>
							</select>
						</div>
					</div>
					<div>
						<div>
							<p>Minimum Price:</p>
						</div>
						<div>
							<input type="number" name="min_price" required>
						</div>
					</div>
					<div>
						<div>
							<p>Image 2</p>
						</div>
						<div>
							<input type="text" name="asset3" required>
						</div>
					</div>
					<div>
						<div>
							<p>Image 4</p>
						</div>
						<div>
							<input type="text" name="asset4" required>
						</div>
					</div>
				</div>
			</section>
		</div>
		<div>
			<p>Product Description:</p>
		</div>
		<div>
			<textarea name="description" required></textarea>
		</div>
		<button type="submit">Add Product</button>
	</form>

</body>
</html>