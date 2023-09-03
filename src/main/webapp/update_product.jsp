<%@page import="in.fssa.vanha.model.Product"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<style>
.form {
	display: flex;
	flex-direction: column;
	gap: 10px;
	background-color: #ffffff;
	padding: 30px;
	width: 450px;
	border-radius: 20px;
	font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto,
		Oxygen, Ubuntu, Cantarell, 'Open Sans', 'Helvetica Neue', sans-serif;
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
</style>

<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<%
	Product product = (Product) request.getAttribute("detail");
	%>
	<form class="form" action="update" method="post">
		<div class="flex-column">
			<label>Name </label>
		</div>
		<div class="inputForm">
			<input placeholder="Enter the Name" name="name" class="input" type="text" value="<%= product.getName() %>">
		</div>

		<div class="flex-column">
			<label>Used Period </label>
		</div>
		<div class="inputForm">
			<input placeholder="Enter the number of Year/month" name="period" class="input"
				type="number" value="<%= product.getUsedPeriod() %>">
		</div>

		<div class="flex-column">
			<label>Used Duration </label>
		</div>
		<div class="inputForm">
			<input placeholder="Enter the duration year/month" name="duration" class="input"
				type="text" value="<%= product.getUsedDuration() %>">
		</div>

		<div class="flex-column">
			<label>Description </label>
		</div>
		<div class="inputForm">
			<input placeholder="Enter the text" class="input" name="description" type="text" value="<%= product.getDescription() %>">
		</div>

		<div class="flex-column">
			<label>Price </label>
		</div>
		<div class="inputForm">
			<input placeholder="Enter the price" class="input" name="price" type="number" value="<%= product.getPrice() %>">
		</div>

		<div class="flex-column">
			<label>Minimum Price </label>
		</div>
		<div class="inputForm">
			<input placeholder="Enter the minimum price" name="min_price" class="input"
				type="number" value="<%= product.getMinPrice() %>">
		</div>
		<div class="flex-column">
			<label>Product Id: </label>
		</div>
		<div class="inputForm">
			<input placeholder="Enter the Name" name="product_id" class="input" type="text" value="<%= product.getProductId()%>" readonly>
		</div>

		<button class="btn" type="submit">Update Product</button>
	</form>
</body>
</html>