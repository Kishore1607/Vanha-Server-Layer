<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Set"%>
<%@page import="in.fssa.vanha.model.ProductDetailDTO"%>
<%@page import="in.fssa.vanha.model.Assets"%>
<%@page import="in.fssa.vanha.model.Product"%>
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

textarea {
	height: 100px;
}

button {
	display: block;
	width: 30%;
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

a {
	text-decoration: none;
}

a button {
	display: block;
	width: 130%;
	padding: 10px;
	background-color: #007bff;
	color: white;
	border: none;
	border-radius: 3px;
	cursor: pointer;
}

a button:hover {
	background-color: #0056b3;
}

.btn {
	display: flex;
	justify-content: space-evenly;
}
</style>

<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<%
	ProductDetailDTO product = (ProductDetailDTO) request.getAttribute("detail");
	%>
	<form class="form" action="update" method="post">
		<h2>Update your product</h2>
		<div class="divide">
			<section>
				<div class="flex-column">
					<label>Name </label>
				</div>
				<div class="inputForm">
					<input placeholder="Enter the Name" name="name" class="input"
						type="text" value="<%=product.getProductName()%>">
				</div>

				<div class="flex-column">
					<label>Used Period </label>
				</div>
				<div class="inputForm">
					<input placeholder="Enter the number of Year/month" name="period"
						class="input" type="number" value="<%=product.getUsedPeriod()%>">
				</div>

				<div class="flex-column">
					<label>Used Duration </label>
				</div>
				<div class="inputForm">
					<input placeholder="Enter the duration year/month" name="duration"
						class="input" type="text" value="<%=product.getUsedDuration()%>">
				</div>
				<div class="inputForm"></div>

				<div class="flex-column">
					<label>Price </label>
				</div>
				<div class="inputForm">
					<input placeholder="Enter the price" class="input" name="price"
						type="number" value="<%=product.getPrice()%>">
				</div>

				<div class="flex-column">
					<label>Minimum Price </label>
				</div>
				<div class="inputForm">
					<input placeholder="Enter the minimum price" name="min_price"
						class="input" type="number" value="<%=product.getMinPrice()%>">
				</div>
			</section>
			<section>
				<div class="flex-column">
					<label>Product Id: </label>
				</div>
				<div class="inputForm">
					<input placeholder="Enter the Name" name="product_id" class="input"
						type="text" value="<%=product.getProductId()%>" readonly>
				</div>
				<%
				Set<Assets> assetsSet = product.getAssets();
				List<Assets> assetsList = new ArrayList<>(assetsSet);
				for (int i = 0; i < 4; i++) {
					Assets asset = (i < assetsList.size()) ? assetsList.get(i) : new Assets();
				%>
				<input class="input" name="asetId" type="hidden"
					value="<%=asset.getId()%>">
				<div class="flex-column">
					<label>Image <%=i + 1%>:
					</label>
				</div>
				<div class="inputForm">
					<input placeholder="Enter the URL" class="input" name="url"
						type="text"
						value="<%=(asset.getValue() != null) ? asset.getValue() : ""%>">
				</div>
				<%
				}
				%>
			</section>
		</div>
		<div>
			<p>Product Description:</p>
		</div>
		<div>
			<textarea placeholder="Enter the text" class="textarea"
				name="description"><%=product.getDescription()%></textarea>
		</div>
		<div class="btn">
			<button type="submit">Update Product</button>
			<a href="../productdetail?productId=<%=product.getProductId()%>">
				<button type="button">Back</button>
			</a>
		</div>
	</form>
</body>
</html>