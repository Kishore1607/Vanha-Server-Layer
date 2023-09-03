<%@page import="in.fssa.vanha.model.ProductDTO"%>
<%@page import="java.util.Set"%>
<%@page import="in.fssa.vanha.model.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
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
</style>
<title>Insert title here</title>
</head>
<body>
	<%
	User user = (User) request.getAttribute("user");
	Set<ProductDTO> products = (Set<ProductDTO>) request.getAttribute("products");
	%>
	<div class="card">
		<div>
			<img src="<%=user.getImage()%>" alt="Image" class="profile-img"
				width="100px" height="100px">
		</div>
		<p class="card-title"><%=user.getName()%></p>
		<p class="card-body"><%=user.getEmail()%></p>
		<p class="card-body"><%=user.getNumber()%></p>
		<p class="card-body"><%=user.getLocation()%></p>
		<a href="./new">
			<button>Update</button>
		</a>
	</div>
	<a href="./../new">
		<button class="button">Add Product</button>
	</a>
	<%
	if (products != null) {
	%>
	<div class="grid-container">
		<%
		for (ProductDTO product : products) {
		%>
		<div class="card">
			<div class="card-details">
				<a
					href="./productdetail?productId=<%=product.getProduct().getProductId()%>">
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

		</div>
		<%
		}
		%>
	</div>
	<%
	}
	%>
</body>
</html>