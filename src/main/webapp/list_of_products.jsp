<%@page import="in.fssa.vanha.model.User"%>
<%@page import="in.fssa.vanha.model.ListProductDTO"%>
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

.seller_img {
	width: 50px;
	height: 50px;
	border-radius: 50%;
	margin-right: 10px;
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
	margin-bottom: 5px;
}

.card:hover {
	border-color: var(--primary);
	background-color: rgb(233, 234, 255);
	box-shadow: 0 4px 18px 0 rgba(0, 0, 0, 0.25);
}

.info {
	display: flex;
	margin: 10px 0px 10px 0;
}

.line {
	width: 65%;
	border: 1px solid gray;
	max-height: 0.5px;
	margin: 10px 10px 0px;
}

.sellerInfo {
	display: flex;
}

.grid-container {
	display: grid;
	grid-template-columns: repeat(3, 300px);
	justify-content: space-evenly;
	row-gap: 40px;
	margin: 50px 0px;
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

	<%
	User user = (User) session.getAttribute("user");
	%>
	<%
	if (user == null) {
	%>
	<a href="home/user">
		<button class="button">Login</button>
	</a>
	<%
	} else {
	%>
	<a href="home/profile">
		<button class="button">Profile</button>
	</a>
	<%
	}
	%>


	<%
	Set<ListProductDTO> productList = (Set<ListProductDTO>) request.getAttribute("products");
	%>
	<div class="grid-container">
		<%
		for (ListProductDTO product : productList) {
		%>
		<div class="card">
			<div class="card-details">
				<a href="home/productdetail?productId=<%=product.getProductId()%>">
					<img src="<%=product.getAsset()%>"
					alt="<%=product.getProductName()%> Image" class="product_img">
				</a>
				<h3 class="text-title"><%=product.getProductName()%></h3>
			</div>
			<div class="text-body">
				<span> <b>Price:</b> <%=product.getPrice()%> (INR)
				</span>
			</div>
			<div class="info">
				<div>Seller Info</div>
				<div class="line"></div>
			</div>
			<div class="sellerInfo">
				<div>
					<img src="<%=product.getSellerImage()%>"
						alt="<%=product.getSellerName()%> Image" class="seller_img">
				</div>
				<div>
					<div class="text-body">
						<span> <b>Seller:</b> <%=product.getSellerName()%>
						</span>
					</div>
					<div class="text-body">
						<span> <b>Location:</b> <%=product.getSellerLocation()%>
						</span>
					</div>
				</div>
			</div>

		</div>
		<%
		}
		%>
	</div>
</body>
</html>