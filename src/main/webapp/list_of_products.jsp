<%@page import="in.fssa.vanha.model.ProductDTO"%>
<%@page import="java.util.Set"%>
<%@page import="in.fssa.vanha.service.ProductService"%>
<%@page import="in.fssa.vanha.model.Product"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link rel="dns-prefetch" href="//fonts.googleapis.com">
<link
	href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<style>
body {
	font-family: "Source Sans Pro", "Helvetica Neue", Arial, sans-serif;
}

.product_img {
	max-width: 100%;
	width: 270px;
	height: 250px;
	border-radius: 10px;
}

.card {
	width: 90%;
	height: auto;
	border-radius: 20px;
	background: #f5f5f5;
	padding: 10px;
	border: 2px solid #c3c6ce;
	transition: 0.5s ease-out;
}

.card-details {
	color: black;
	display: grid;
	place-content: center;
}

.text-body {
	display: flex;
}

.text-body span {
	padding-right: 5px;
}

.text-title {
	font-weight: bold;
}

.card:hover {
	border-color: var(--primary);
	background-color: rgb(233, 234, 255);
	box-shadow: 0 4px 18px 0 rgba(0, 0, 0, 0.25);
}

.grid-container {
	display: grid;
	grid-template-columns: repeat(3, 300px);
	justify-content: space-evenly;
	row-gap: 40px;
	margin: 50px 0px;
}

.algn {
	margin-top: 50px;
}
.button {
    background-color: #007bff; 
    color: #fff; 
    padding: 10px 20px;
    border: none;
    border-radius: 5px;
    cursor: pointer; 
}

.button:hover {
    background-color: #0056b3;
}
</style>
<meta charset="ISO-8859-1">
<title>All products of category</title>
</head>
<body>
	
	<a href="products/user">
		<button class="button">Login</button>
	</a>

	<%
	Set<ProductDTO> productList = (Set<ProductDTO>) request.getAttribute("products");
	%>
	<div class="grid-container">
		<%
		for (ProductDTO product : productList) {
		%>
		<div class="card">
			<div class="card-details">
				<a
					href="products/productdetail?productId=<%=product.getProduct().getProductId()%>">
					<img src="<%=product.getAsset().getValue()%>"
					alt="<%=product.getProduct().getName()%> Image" class="product_img">
				</a>
				<h3 class="text-title"><%=product.getProduct().getName()%></h3>
			</div>
			<div class="text-body">
				<span> <b>Price:</b> <%=product.getProduct().getPrice()%>
					(INR)
				</span>
			</div>
			<div class="text-body">
				<span> <b>Seller:</b> <%=product.getUser().getName()%>
				</span>
			</div>
			<div class="text-body">
				<span> <b>Location:</b> <%=product.getUser().getLocation()%>
				</span>
			</div>

		</div>
		<%
		}
		%>
	</div>
</body>
</html>