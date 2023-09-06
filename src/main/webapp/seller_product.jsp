<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Set"%>
<%@page import="in.fssa.vanha.model.ProductDetailDTO"%>
<%@page import="in.fssa.vanha.model.BidDTO"%>
<%@page import="in.fssa.vanha.model.ListProductDTO"%>
<%@page import="in.fssa.vanha.model.BidHistory"%>
<%@page import="in.fssa.vanha.model.Assets"%>
<%@page import="in.fssa.vanha.service.ProductService"%>
<%@page import="in.fssa.vanha.model.Product"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
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
:root {
	--primary: #7d8e95;
	--secondary: #222f30;
	--tertiary: #ffd8af;
	--fourth: #f1b194;
	--fifth: #4c585e;
}

body {
	font-family: "Source Sans Pro", "Helvetica Neue", Arial, sans-serif;
}

.main {
	margin-top: 50px;
	display: flex;
	justify-content: space-evenly;
}

.main_left {
	width: 800px;
	display: grid;
	grid-template-columns: repeat(2, 450px);
	margin-top: 20px;
}

.grid-item {
	max-width: 75%;
	max-height: 80%;
	box-sizing: border-box;
	padding: 10px;
	text-align: center;
}

.grid-item img {
	max-width: 100%; /* Ensure images don't exceed their container */
}

.desc {
	color: var(--fifth);
}

#description {
	width: 500px;
	max-height: 150px;
	overflow: auto;
}

.rate {
	font-size: 18px;
	height: 40px;
	display: flex;
	padding-top: 15px;
	float: inline-start;
}

.duration {
	height: 40px;
	display: flex;
	padding-bottom: 15px;
	float: inline-start;
	border-width: 0px 0px 2px 0px;
	border-style: solid;
	border-color: rgb(160, 160, 160);
	margin-bottom: 10px;
}

#duration {
	padding-top: 2px;
	color: var(--primary);
}

.inr b {
	color: var(--primary);
}

.rate p, .duration p {
	font-size: 18px;
	padding: 4px 10px 0px 0px;
	color: var(--fifth);
}

.product_img {
	width: 500px;
	height: 300px;
	border-radius: 30px;
	background: #e0e0e0;
	box-shadow: 9px 9px 18px #d7d7d7, -9px -9px 18px #e9e9e9;
}

#prod_price {
	padding-right: 5px;
	color: var(--secondary);
}

#prod_date {
	padding: 2px 5px 0px 0px;
	color: var(--secondary);
}

.prod_name {
	color: var(--secondary);
}

.bid-area button {
	width: 130px;
}

.button1 i {
	padding-right: 10px;
}

.bid-amount {
	height: 28px;
	margin-right: 10px;
	outline: none;
}

input[type=number]::-webkit-inner-spin-button, input[type=number]::-webkit-outer-spin-button
	{
	-webkit-appearance: none;
	margin: 0;
}

.seller-profile {
	width: 360px;
	justify-content: space-between;
	display: flex;
	border: 2px solid rgb(204, 204, 204);
	margin-bottom: 30px;
	border-radius: 10px;
	transition: 0.5s ease-out;
}

.seller-profile:hover {
	background-color: var(--secondary);
	color: var(--tertiary);
}

.seller_img {
	width: 100px;
	height: 100px;
	border-radius: 50%;
	margin: 10px;
}

.seller-info {
	text-align: center;
	width: 360px;
	margin: 0px 10px 10px 0px;
	display: flex;
	justify-content: space-between;
}

.box {
	display: flex;
}

.seller-info h1 {
	color: var(--fifth);
}

#seller_name {
	width: 230px;
}

.btn {
	margin: 20px;
}

.button {
	padding: 10px 20px;
	border-radius: 5px;
	border: none;
	outline: none;
	-webkit-transition: .4s ease-in-out;
	transition: .4s ease-in-out;
	background-color: #252525;
	color: white;
}

.button:hover {
	background-color: rgb(93, 220, 93);
	color: white;
}

.button1 {
	padding: 10px 20px;
	border-radius: 5px;
	margin-left: 20px;
	border: none;
	outline: none;
	-webkit-transition: .4s ease-in-out;
	transition: .4s ease-in-out;
	background-color: #252525;
	color: white;
}

.button1:hover {
	background-color: rgb(249, 82, 31);
	color: white;
}

.button3 {
	display: block;
	width: 60px;
	padding: 10px;
	background-color: #007bff;
	color: white;
	border: none;
	border-radius: 3px;
	cursor: pointer;
}

.button3:hover {
	background-color: #0056b3;
}

.container {
	width: 600px;
	height: 500px;
	overflow: hidden;
}

.nav-bar {
	background-color: #333;
	color: white;
	padding: 10px;
	text-align: center;
}

.nav-bar a {
	color: white;
	text-decoration: none;
	font-weight: bold;
}

.bid-container {
	max-height: 100%;
	overflow: auto;
}

.line {
	border-bottom: 1px solid #ccc;
	padding: 10px;
}

.line p {
	margin: 0;
	display: flex;
	justify-content: space-between;
	align-items: center;
}

.no {
	text-align: center
}

.buyer_img {
	max-width: 50px;
	max-height: 50px;
	border-radius: 50%;
	margin-left: 10px;
}
</style>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<a href="../profile">
		<button>Back</button>
	</a>
	<%
	ProductDetailDTO product = (ProductDetailDTO) request.getAttribute("productDetail");
	Set<Assets> assetsSet = product.getAssets();
	List<Assets> assetsList = new ArrayList<>(assetsSet);
	%>
	<main class="main">
		<section class="main_left">
			<%
			for (int i = 0; i < 4; i++) {
				Assets asset = (i < assetsList.size()) ? assetsList.get(i) : null;
			%>
			<div class="grid-item">
				<img
					src="<%=(asset != null) ? asset.getValue() : "https://iili.io/J9amRDJ.jpg"%>"
					alt="<%=product.getProductName()%> Image" class="product_img">
			</div>
			<%
			}
			%>
		</section>
		<section class="main_right">
			<div class="product-detail">
				<h2 class="prod_name"><%=product.getProductName()%></h2>
				<p class="desc">
					<b>Description :</b>
				</p>
				<p id="description"><%=product.getDescription()%>
				</p>
				<div class="rate">
					<p>
						<b>Price :</b>
					</p>
					<h3 id="prod_price"><%=product.getPrice()%></h3>
					<h3 class="inr">
						<b>(INR)</b>
					</h3>
				</div>
				<div class="duration">
					<p>
						<b>Used :</b>
					</p>
					<h3 id="prod_date"><%=product.getUsedPeriod()%></h3>
					<h3 id="duration"><%=product.getUsedDuration()%></h3>
				</div>
				<div class="btn">
					<a href="productdetail/edit?productId=<%=product.getProductId()%>">
						<button class="button">EDIT</button>
					</a> <a
						href="productdetail/delete?productId=<%=product.getProductId()%>">
						<button class="button1">DELETE</button>
					</a>
				</div>
				<div class="container">
					<div class="nav-bar">
						<a>Bids</a>
					</div>
					<div class="bid-container">
						<%
						boolean hasBids = false;
						for (BidDTO bid : product.getBids()) {
							if (bid == null) {
						%>
						<div class="line">
							<p>There is no bid.</p>
						</div>
						<%
						} else {
						hasBids = true;
						%>
						<div class="line">
							<p>
								<img src="<%=bid.getBuyerImage()%>"
									alt="<%=bid.getBuyerName()%> Image" class="buyer_img"><span><%=bid.getBuyerName()%></span><span><%=bid.getAmount()%></span>
								<span><button class="button3">Sell</button></span>
							</p>
						</div>
						<%
						}
						}

						if (!hasBids) {
						%>
						<div class="no">
							<p>There is no bid.</p>
						</div>
						<%
						}
						%>
					</div>
				</div>
			</div>
		</section>
	</main>

</body>
</html>