<%@page import="in.fssa.vanha.model.BidHistory"%>
<%@page import="in.fssa.vanha.model.Assets"%>
<%@page import="in.fssa.vanha.model.ProductDTO"%>
<%@page import="in.fssa.vanha.service.ProductService"%>
<%@page import="in.fssa.vanha.model.Product"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Montserrat&display=swap"
	rel="stylesheet">
<style>
body {
	display: flex;
}

.profile-img {
	width: 55px;
	height: 55px;
	margin: 5px 5px 0px 0px;
	border-radius: 50%;
}

.card {
	padding: 20px;
	width: 330px;
	min-height: 370px;
	border-radius: 20px;
	background: #e8e8e8;
	box-shadow: 5px 5px 6px #dadada, -5px -5px 6px #f6f6f6;
	transition: 0.4s;
	font-family: 'Montserrat', sans-serif;
}

.card:hover {
	translate: 0 -10px;
}

.card-title {
	font-size: 18px;
	font-weight: 600;
	color: #2e54a7;
	margin: 15px 0 0 10px;
}

.product_img {
	width: 330px;
	height: 320px;
	border-radius: 10px;
}

.card-body {
	margin: 13px 0 0 10px;
	color: rgb(31, 31, 31);
	font-size: 15px;
}

.footer {
	border: 1px 0 0 0 solid gray;
	font-size: 13px;
	color: #636363;
}

.by-name {
	font-weight: 700;
}

.container {
	width: 300px;
	height: 320px;
	background-color: #343541;
	border-radius: 8px;
	display: flex;
	flex-direction: column;
}

.nav-bar {
	width: 100%;
	height: 40px;
	background-color: none;
	display: flex;
	align-items: center;
	justify-content: space-between;
}

.nav-bar a {
	color: white;
	white-space: nowrap;
	margin-left: 10px;
	user-select: none;
}

.line {
	width: 80%;
	padding: 10px 5px 10px 5px;
	margin-left: 20px;
	background-color: white;
	border-radius: 30px;
	background-color: white;
}

.bid {
	display: flex;
	flex-direction: column;
	align-items: center;
	max-width: 500px;
	user-select: none;
}

.bid-container {
	width: 80%;
	height: 80%;
}

.text {
	font-size: 0.8rem;
	text-align: left;
	width: 100%;
	color: black;
	font-weight: bold;
	margin-bottom: 3px;
}

.input {
	border-radius: 8px;
	padding: 7px 16px;
	box-shadow: rgba(77, 79, 82, 0.2) 0px 8px 24px;
	border: 2px solid rgb(184, 180, 180);
	font-size: 0.8rem;
	outline: none;
	transition: 0.5s ease;
}

.input:hover {
	box-shadow: rgba(0, 0, 0, 0.1) 0px 4px 12px;
	border-color: rgb(138, 175, 255);
}

.input:focus {
	border: 2px solid rgb(57, 120, 255);
	box-shadow: rgba(133, 130, 130, 0.1) 0px 4px 12px;
	transform: scale(0.99);
}

.input::placeholder {
	font-size: 13px;
	font-weight: 600;
}

.button {
	padding: 6px 15px;
	text-align: center;
	color: rgb(95, 87, 87);
	cursor: pointer;
	background-color: white;
	border-radius: 6px;
	border: none;
	transition: 0.5s;
	width: 100%;
	margin-top: 7px;
	font-size: 13px;
	box-shadow: rgba(104, 100, 100, 0.1) 0px 4px 6px -1px,
		rgba(49, 42, 42, 0.06) 0px 2px 4px -1px;
}

.button:hover {
	border-radius: 7px;
	background-color: rgb(243, 242, 242);
	border-color: rgb(236, 232, 232);
}
</style>
<meta charset="ISO-8859-1">
<title>Product detail</title>
</head>
<body>
	<%
	ProductDTO product = (ProductDTO) request.getAttribute("productDetails");
	%>
	<div class="card">
		<%
		for (Assets asset : product.getAssets()) {
		%>
		<div>
			<img src="<%=asset.getValue()%>" alt="Image" class="product_img">
		</div>
		<%
		}
		%>
		<p class="card-title"><%=product.getProduct().getName()%></p>
		<p class="card-body"><%=product.getProduct().getDescription()%></p>
		<p class="card-body">
			<span class="by-name">Price: </span><%=product.getProduct().getPrice()%>
		</p>
		<p class="card-body">
			<span class="by-name">Used: </span><%=product.getProduct().getUsedPeriod()%>
			<span><%=product.getProduct().getUsedDuration()%></span>
		</p>

		<div class="footer">
			<div>
				<img src="https://iili.io/HNNDXBR.jpg" alt="Image"
					class="profile-img">
			</div>
			<div>
				Sold by <span class="by-name"><%=product.getUser().getName()%></span>
			</div>
			<div>
				Location: <span class="date"><%=product.getUser().getLocation()%></span>
			</div>
		</div>
	</div>

	<div class="container">
		<div class="nav-bar">
			<a>Bids</a>
		</div>
		<div class="bid-container">
			<%
			for (BidHistory bid : product.getBids()) {
			%>
			<div class="line">
				<p>
					<%=bid.getBuyerName()%><span><%=bid.getBidAmount()%></span>
				</p>
			</div>
			<%
			}
			%>
		</div>

		<form class="bid" action="productdetail/bid" method="post">
			<input type="hidden" name="productId"
				value="<%=product.getProduct().getProductId()%>"> <input
				type="hidden" name="userEmail"
				value="<%=product.getUser().getEmail()%>"> <input
				placeholder="Quote your amount here" class="input" name="amount"
				type="number">
			<button class="button" type="submit">Bid</button>
		</form>
	</div>

</body>

</html>